package app.tracker.enums;


import app.tracker.daos.MailTemplateDao;
import app.tracker.entities.MailTemplate;

import java.io.Serializable;

public enum ParcelStatus implements Serializable
{
    NEW( "Przyjęta w oddziale" )
            {
                public MailTemplate findMailTemplate(final MailTemplateDao mailTemplateDao)
                {
                    return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_NEW );
                }
            },
    SEND( "Wysłana" )
            {
                public MailTemplate findMailTemplate(final MailTemplateDao mailTemplateDao)
                {
                    return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_SEND );
                }
            },
    IN_DELIVERY( "W doręczeniu" )
            {
                public MailTemplate findMailTemplate(final MailTemplateDao mailTemplateDao)
                {
                    return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_IN_DELIVERY );
                }
            },
    DELIVERED( "Odebrana" )
            {
                public MailTemplate findMailTemplate(final MailTemplateDao mailTemplateDao)
                {
                    return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_DELIVERED );
                }
            },
    CANCELED( "Anulowana" )
            {
                public MailTemplate findMailTemplate(final MailTemplateDao mailTemplateDao)
                {
                    return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_CANCELLED );
                }
            },
    RETURNED( "Zwrócona" )
            {
                public MailTemplate findMailTemplate(final MailTemplateDao mailTemplateDao)
                {
                    return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_RETURNED );
                }
            };

    public abstract MailTemplate findMailTemplate( final MailTemplateDao mailTemplateDao );

    private String name;

    ParcelStatus( final String name )
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
