package app.tracker.entities;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
public class Place implements Serializable
{
    @Id
    @GeneratedValue
    @Column( name = "place_id" )
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @Email
    @NotNull
    private String email;

    @OneToMany( mappedBy = "place", cascade = CascadeType.ALL )
    private List< Address > addresses;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Parcel parcelSender;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Parcel parcelRecipient;

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

    public Parcel getParcelSender()
    {
        return parcelSender;
    }

    public void setParcelSender( Parcel parcelSender )
    {
        this.parcelSender = parcelSender;
    }

    public Parcel getParcelRecipient()
    {
        return parcelRecipient;
    }

    public void setParcelRecipient( Parcel parcelRecipient )
    {
        this.parcelRecipient = parcelRecipient;
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
