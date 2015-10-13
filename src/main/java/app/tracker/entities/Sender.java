package app.tracker.entities;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Sender implements Serializable
{
    @Id
    @GeneratedValue
    @Column
    private Long id;

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
