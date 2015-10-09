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
    private PlaceMigrator placeMigrator;
    @Autowired
    private ParcelDetailMigrator parcelDetailMigrator;

    @Override
    public Parcel doCopyDto( final ParcelDto dto )
    {
        Parcel parcel = new Parcel();
        BeanUtils.copyProperties( dto, parcel );

        parcel.setSender( placeMigrator.copyDto( dto.getSender() ) );
        parcel.setRecipient( placeMigrator.copyDto( dto.getRecipient() ) );
        parcel.setDetail( parcelDetailMigrator.copyDto( dto.getDetail() ) );

        return parcel;
    }

    @Override
    public ParcelDto doCopyEntity( final Parcel parcel )
    {
        ParcelDto parcelDto = new ParcelDto();
        BeanUtils.copyProperties( parcel, parcelDto );

        parcelDto.setSender( placeMigrator.copyEntity( parcel.getSender() ) );
        parcelDto.setRecipient( placeMigrator.copyEntity( parcel.getRecipient() ) );
        parcelDto.setDetail( parcelDetailMigrator.copyEntity( parcel.getDetail() ) );

        return parcelDto;
    }
}
