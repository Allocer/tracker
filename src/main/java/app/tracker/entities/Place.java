package app.tracker.entities;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Place implements Serializable
{
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    @Email
    private String email;

    @OneToMany( mappedBy = "place", cascade = CascadeType.ALL )
    private List< Address > addresses;

    @OneToOne
    private Parcel parcel;

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

    public List< Address > getAddresses()
    {
        return addresses;
    }

    public void setAddresses( List< Address > addresses )
    {
        this.addresses = addresses;
    }

    public Parcel getParcel()
    {
        return parcel;
    }

    public void setParcel( Parcel parcel )
    {
        this.parcel = parcel;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }
}
