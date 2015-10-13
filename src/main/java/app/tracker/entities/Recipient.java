package app.tracker.entities;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Recipient implements Serializable
{
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @Email
    @NotNull
    private String email;

    @OneToOne
    @PrimaryKeyJoinColumn
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public Parcel getParcel()
    {
        return parcel;
    }

    public void setParcel( Parcel parcel )
    {
        this.parcel = parcel;
    }
}
