package app.tracker.dtos;

import app.tracker.enums.ParcelStatus;
import app.tracker.enums.ParcelType;

import java.util.Date;

public class ParcelDto
{
    private Long id;
    private SenderDto sender;
    private RecipientDto recipient;
    private AddressDto address;
    private String number;
    private ParcelType type;
    private ParcelStatus status;
    private Date sendDate;
    private Date receiveDate;
    private boolean isRegistered;
    private ParcelDetailDto detail;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public SenderDto getSender()
    {
        return sender;
    }

    public void setSender( SenderDto sender )
    {
        this.sender = sender;
    }

    public RecipientDto getRecipient()
    {
        return recipient;
    }

    public void setRecipient( RecipientDto recipient )
    {
        this.recipient = recipient;
    }

    public AddressDto getAddress()
    {
        return address;
    }

    public void setAddress( AddressDto address )
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

    public ParcelDetailDto getDetail()
    {
        return detail;
    }

    public void setDetail( ParcelDetailDto detail )
    {
        this.detail = detail;
    }
}
