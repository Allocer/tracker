package app.tracker.migrators;

import app.tracker.dtos.ParcelDto;
import app.tracker.entities.Parcel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class ParcelMigrator extends AbstractMigrator< Parcel, ParcelDto >
{
    @Autowired
    private SenderMigrator senderMigrator;
    @Autowired
    private RecipientMigrator recipientMigrator;
    @Autowired
    private ParcelDetailMigrator parcelDetailMigrator;
    @Autowired
    private AddressMigrator addressMigrator;

    @Override
    public Parcel doCopyDto( final ParcelDto dto )
    {
        Parcel parcel = new Parcel();
        BeanUtils.copyProperties( dto, parcel );

        parcel.setSender( senderMigrator.copyDto( dto.getSender() ) );
        parcel.setRecipient( recipientMigrator.copyDto( dto.getRecipient() ) );
        parcel.setDetail( parcelDetailMigrator.copyDto( dto.getDetail() ) );
        parcel.setAddress( addressMigrator.copyDto( dto.getAddress() ) );

        return parcel;
    }

    @Override
    public ParcelDto doCopyEntity( final Parcel parcel )
    {
        ParcelDto parcelDto = new ParcelDto();
        BeanUtils.copyProperties( parcel, parcelDto );

        parcelDto.setSender( senderMigrator.copyEntity( parcel.getSender() ) );
        parcelDto.setRecipient( recipientMigrator.copyEntity( parcel.getRecipient() ) );
        parcelDto.setDetail( parcelDetailMigrator.copyEntity( parcel.getDetail() ) );
        parcelDto.setAddress( addressMigrator.copyEntity( parcel.getAddress() ) );

        return parcelDto;
    }
}
