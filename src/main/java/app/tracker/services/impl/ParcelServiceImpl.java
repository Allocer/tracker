package app.tracker.services.impl;

import app.tracker.components.ParcelNumberGenerator;
import app.tracker.daos.ParcelDao;
import app.tracker.dtos.ParcelDto;
import app.tracker.entities.Parcel;
import app.tracker.enums.ParcelStatus;
import app.tracker.migrators.ParcelMigrator;
import app.tracker.services.MailService;
import app.tracker.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ParcelServiceImpl implements ParcelService
{
    @Autowired
    private ParcelDao parcelDao;
    @Autowired
    private ParcelMigrator parcelMigrator;
    @Autowired
    private MailService mailService;

    @Override
    public ParcelDto persistWithMail( final ParcelDto parcelDto ) throws MessagingException
    {
        ParcelDto created = persist( parcelDto );
        mailService.sendMessage( created );

        return created;
    }

    @Override
    public ParcelDto updateStatusWithMail( final ParcelDto parcelDto ) throws MessagingException
    {
        ParcelDto updated = update( parcelDto );
        mailService.sendMessage( updated );

        return updated;
    }

    @Override
    @Transactional
    public ParcelDto persist( final ParcelDto parcelDto )
    {
        Parcel parcel = parcelMigrator.copyDto( parcelDto );
        parcel.setNumber( generateNumber( parcel ) );
        parcel.setStatus( ParcelStatus.NEW );

        return parcelMigrator.copyEntity( parcelDao.persist( parcel ) );
    }

    @Override
    @Transactional
    public ParcelDto find( final Long id )
    {
        Parcel parcel = parcelDao.find( id );
        return parcelMigrator.copyEntity( parcel );
    }

    @Override
    @Transactional
    public ParcelDto findByNumber( final String number )
    {
        if ( number == null || number.isEmpty() )
        {
            throw new IllegalArgumentException( "Nie podano numeru przesyłki." );
        }
        Parcel parcel = parcelDao.findByNumber( number );

        return parcelMigrator.copyEntity( parcel );
    }

    @Override
    @Transactional
    public List< ParcelDto > findAll()
    {
        List< Parcel > parcels = parcelDao.findAll();
        List< ParcelDto > transformers = new ArrayList<>();
        for ( final Parcel parcel : parcels )
        {
            ParcelDto dto = parcelMigrator.copyEntity( parcel );
            transformers.add( dto );
        }

        return transformers;
    }

    @Override
    @Transactional
    public ParcelDto update( final ParcelDto parcelDto )
    {
        Parcel parcel = parcelDao.findByNumber( parcelDto.getNumber() );
        if ( ParcelStatus.CANCELED.equals( parcel.getStatus() ) || ParcelStatus.RETURNED.equals( parcel.getStatus() ) )
        {
            throw new IllegalArgumentException( "Przesyłka anulowana lub zwrócona. Brak możliwości zmiany statusu." );
        }
        updateParcelStatus( parcel, parcelDto );
        setParcelDate( parcel );

        return parcelMigrator.copyEntity( parcelDao.update( parcel ) );
    }

    @Override
    @Transactional
    public void delete( final Long id )
    {
        Parcel parcel = parcelDao.find( id );
        parcelDao.delete( parcel );
    }

    private String generateNumber( final Parcel parcel )
    {
        return ParcelNumberGenerator.generateNumber( parcel.getType() );
    }

    private void updateParcelStatus( final Parcel parcel, final ParcelDto parcelDto )
    {
        if ( !ParcelStatus.CANCELED.equals( parcelDto.getStatus() ) && !ParcelStatus.RETURNED.equals( parcelDto.getStatus() ) )
        {
            updateParcelStatus( parcel );
        }
        else
        {
            parcel.setStatus( parcelDto.getStatus() );
        }
    }

    private void updateParcelStatus( final Parcel parcel )
    {
        List< ParcelStatus > statuses = new ArrayList<>( Arrays.asList( ParcelStatus.values() ) );
        statuses.remove( ParcelStatus.CANCELED );
        statuses.remove( ParcelStatus.RETURNED );

        int nextStatus = statuses.indexOf( parcel.getStatus() ) + 1;
        if ( nextStatus >= statuses.size() )
        {
            throw new IndexOutOfBoundsException( "Przesyłka została dostarczona. Nie można zmienić jej statusu." );
        }
        parcel.setStatus( statuses.get( nextStatus ) );
    }

    private void setParcelDate( final Parcel parcel )
    {
        if ( ParcelStatus.SEND.equals( parcel.getStatus() ) )
        {
            parcel.setSendDate( new Date() );
        }
        if ( ParcelStatus.DELIVERED.equals( parcel.getStatus() ) )
        {
            parcel.setReceiveDate( new Date() );
        }
    }
}
