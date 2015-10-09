package app.tracker.services.impl;

import app.tracker.components.ParcelNumberGenerator;
import app.tracker.daos.ParcelDao;
import app.tracker.dtos.ParcelDto;
import app.tracker.entities.Parcel;
import app.tracker.enums.MailTemplateEnum;
import app.tracker.migrators.ParcelMigrator;
import app.tracker.services.MailService;
import app.tracker.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParcelServiceImpl implements ParcelService
{
    @Autowired
    private ParcelDao parcelDao;
    @Autowired
    private ParcelMigrator parcelMigrator;
    @Autowired
    private ParcelNumberGenerator numberGenerator;
    @Autowired
    private MailService mailService;

    @Override
    public ParcelDto persistWithMail( final ParcelDto parcelDto ) throws MessagingException
    {
        ParcelDto created = persist( parcelDto );
        mailService.sendMessage( created, MailTemplateEnum.PARCEL_NEW );

        return created;
    }

    @Override
    @Transactional
    public ParcelDto persist( final ParcelDto parcelDto )
    {
        Parcel parcel = parcelMigrator.copyDto( parcelDto );
        parcel.setNumber( generateNumber( parcel ) );

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
        Parcel parcel = parcelDao.findByNumber( number );
        return parcelMigrator.copyEntity( parcel );
    }

    @Override
    @Transactional
    public List< ParcelDto > findAll()
    {
        List< Parcel > parcels = parcelDao.findAll();
        List< ParcelDto > dtos = new ArrayList<>();
        for ( final Parcel parcel : parcels )
        {
            ParcelDto dto = parcelMigrator.copyEntity( parcel );
            dtos.add( dto );
        }

        return dtos;
    }

    @Override
    @Transactional
    public ParcelDto update( final ParcelDto parcelDto )
    {
        Parcel parcel = parcelMigrator.copyDto( parcelDto );
        parcel.setId( parcel.getId() );

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
        return numberGenerator.generateNumber( parcel.getType() );
    }
}
