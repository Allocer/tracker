package app.tracker.migrators;

import app.tracker.dtos.AddressDto;
import app.tracker.entities.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public final class AddressMigrator extends AbstractMigrator< Address, AddressDto >
{
    @Override
    public Address doCopyDto( AddressDto dto )
    {
        Address address = new Address();
        BeanUtils.copyProperties( dto, address );

        return address;
    }

    @Override
    public AddressDto doCopyEntity( Address address )
    {
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties( address, addressDto );

        return addressDto;
    }
}
