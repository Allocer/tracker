package app.tracker.migrators;

import app.tracker.dtos.AddressDto;
import app.tracker.dtos.PlaceDto;
import app.tracker.entities.Address;
import app.tracker.entities.Place;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class PlaceMigrator extends AbstractMigrator< Place, PlaceDto >
{
    @Autowired
    private AddressMigrator addressMigrator;

    @Override
    public Place doCopyDto( final PlaceDto dto )
    {
        Place place = new Place();
        BeanUtils.copyProperties( dto, place );

        List< Address > addresses = new ArrayList<>();
        for ( final AddressDto addressDto : dto.getAddresses() )
        {
            addresses.add( addressMigrator.copyDto( addressDto ) );
        }
        place.setAddresses( addresses );

        return place;
    }

    @Override
    public PlaceDto doCopyEntity( final Place place )
    {
        PlaceDto placeDto = new PlaceDto();
        BeanUtils.copyProperties( place, placeDto );

        List< AddressDto > addressDtos = new ArrayList<>();
        for ( final Address address : place.getAddresses() )
        {
            addressDtos.add( addressMigrator.copyEntity( address ) );
        }
        placeDto.setAddresses( addressDtos );

        return placeDto;
    }
}
