package app.tracker.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Address implements Serializable
{
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String street;

    @Column
    private String zipCode;

    @Column
    private String houseNumber;

    @Column
    private String city;

    @Column
    private String country;

    @ManyToOne
    @JoinColumn( name = "place_id" )
    private Place place;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Place getPlace()
    {
        return place;
    }

    public void setPlace( Place place )
    {
        this.place = place;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet( String street )
    {
        this.street = street;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode( String zipCode )
    {
        this.zipCode = zipCode;
    }

    public String getHouseNumber()
    {
        return houseNumber;
    }

    public void setHouseNumber( String houseNumber )
    {
        this.houseNumber = houseNumber;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

}
