package app.tracker.entities;

import app.tracker.enums.ParcelStatus;
import app.tracker.enums.ParcelType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Parcel implements Serializable
{
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @OneToOne( mappedBy = "parcel", cascade = CascadeType.ALL )
    private ParcelDetail detail;

    @OneToOne( mappedBy = "parcel", cascade = CascadeType.ALL )
    private Sender sender;

    @OneToOne( mappedBy = "parcel", cascade = CascadeType.ALL )
    private Recipient recipient;

    @OneToOne( mappedBy = "parcel", cascade = CascadeType.ALL )
    private Address address;

    @Column( length = 26 )
    @NotNull
    private String number;

    @Column
    @Enumerated( EnumType.STRING )
    private ParcelType type;

    @Column
    @NotNull
    @Enumerated( EnumType.STRING )
    private ParcelStatus status;

    @Column
    @Temporal( TemporalType.TIMESTAMP )
    private Date sendDate;

    @Column
    @Temporal( TemporalType.TIMESTAMP )
    private Date receiveDate;

    @Column
    private boolean isRegistered;


    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Sender getSender()
    {
        return sender;
    }

    public void setSender( Sender sender )
    {
        this.sender = sender;
    }

    public Recipient getRecipient()
    {
        return recipient;
    }

    public void setRecipient( Recipient recipient )
    {
        this.recipient = recipient;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress( Address address )
    {
        this.address = address;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber( String number )
    {
        this.number = number;
    }

    public ParcelType getType()
    {
        return type;
    }

    public void setType( ParcelType type )
    {
        this.type = type;
    }

    public ParcelStatus getStatus()
    {
        return status;
    }

    public void setStatus( ParcelStatus status )
    {
        this.status = status;
    }

    public Date getSendDate()
    {
        return sendDate;
    }

    public void setSendDate( Date sendDate )
    {
        this.sendDate = sendDate;
    }

    public Date getReceiveDate()
    {
        return receiveDate;
    }

    public void setReceiveDate( Date receiveDate )
    {
        this.receiveDate = receiveDate;
    }

    public boolean isRegistered()
    {
        return isRegistered;
    }

    public void setIsRegistered( boolean isRegistered )
    {
        this.isRegistered = isRegistered;
    }

    public ParcelDetail getDetail()
    {
        return detail;
    }

    public void setDetail( ParcelDetail detail )
    {
        this.detail = detail;
    }
}
