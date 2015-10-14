package app.tracker.enums;

import app.tracker.dtos.ParcelDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public enum MailTemplateEnum
{
    HEADER
            {
                public Map< String, String > createMailParameters( final ParcelDto parcel )
                {
                    return parameters;
                }
            },
    FOOTER
            {
                public Map< String, String > createMailParameters( final ParcelDto parcel )
                {
                    return parameters;
                }
            },
    PARCEL_NEW
            {
                public Map< String, String > createMailParameters( final ParcelDto parcel )
                {
                    setBasicParameters( parcel, parameters );
                    return parameters;
                }
            },
    PARCEL_SEND
            {
                public Map< String, String > createMailParameters( final ParcelDto parcel )
                {
                    setBasicParameters( parcel, parameters );
                    parameters.put( "sendDate", parcel.getSendDate().toString() );
                    return parameters;
                }
            },
    PARCEL_IN_DELIVERY
            {
                public Map< String, String > createMailParameters( final ParcelDto parcel )
                {
                    setBasicParameters( parcel, parameters );
                    parameters.put( "sendDate", parcel.getSendDate().toString() );
                    return parameters;
                }
            },
    PARCEL_CANCELLED
            {
                public Map< String, String > createMailParameters( final ParcelDto parcel )
                {
                    setBasicParameters( parcel, parameters );
                    return parameters;
                }
            },
    PARCEL_DELIVERED
            {
                public Map< String, String > createMailParameters( final ParcelDto parcel )
                {
                    setBasicParameters( parcel, parameters );
                    parameters.put( "sendDate", parcel.getSendDate().toString() );
                    parameters.put( "receiveDate", parcel.getReceiveDate().toString() );
                    return parameters;
                }
            },
    PARCEL_RETURNED
            {
                public Map< String, String > createMailParameters( final ParcelDto parcel )
                {
                    setBasicParameters( parcel, parameters );
                    return parameters;
                }
            };

    Map< String, String > parameters = new HashMap<>();

    public abstract Map< String, String > createMailParameters( final ParcelDto parcel );

    void setBasicParameters( final ParcelDto parcel, final Map< String, String > parameters )
    {
        parameters.put( "parcelNumber", parcel.getNumber() );
        parameters.put( "parcelSender", parcel.getSender().getEmail() );
        parameters.put( "parcelRecipient", parcel.getRecipient().getName() );
        parameters.put( "parcelStatus", parcel.getStatus().toString() );
    }
}
