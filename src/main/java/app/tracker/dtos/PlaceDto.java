package app.tracker.dtos;

import java.util.List;

public class PlaceDto
{
    private Long id;
    private String name;
    private String email;
    private List< AddressDto > addresses;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public List< AddressDto > getAddresses()
    {
        return addresses;
    }

    public void setAddresses( List< AddressDto > addresses )
    {
        this.addresses = addresses;
    }
}
