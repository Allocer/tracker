package app.tracker.services.impl;

import app.tracker.daos.MailTemplateDao;
import app.tracker.dtos.ParcelDto;
import app.tracker.entities.MailTemplate;
import app.tracker.enums.MailTemplateEnum;
import app.tracker.enums.ParcelStatus;
import app.tracker.services.MailService;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService
{
    @Autowired
    private MailTemplateDao mailTemplateDao;
    @Autowired
    private JavaMailSender mailSender;

    private static final String MESSAGE_CONTENT_TYPE = "text/html; charset=UTF-8";

    @Override
    public void sendMessage( final ParcelDto parcelDto ) throws MessagingException
    {
        MailTemplate mailTemplate = findMailTemplate( parcelDto.getStatus() );
        Map< String, String > parameters = createParameters( mailTemplate, parcelDto );
        MimeMessage message = createMessage( mailTemplate, parcelDto, parameters );

        sendMessage( message );
    }

    private MailTemplate findMailTemplate( final ParcelStatus status )
    {
        switch ( status )
        {
            case NEW:
                return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_NEW );
            case SEND:
                return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_SEND );
            case IN_DELIVERY:
                return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_IN_DELIVERY );
            case DELIVERED:
                return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_DELIVERED );
            case CANCELED:
                return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_CANCELLED );
            case RETURNED:
                return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_RETURNED );
            default:
                return mailTemplateDao.findByType( MailTemplateEnum.PARCEL_NEW );
        }
    }

    private Map< String, String > createParameters( final MailTemplate mailTemplate, final ParcelDto parcel )
    {
        Map< String, String > parameters = new HashMap<>();
        switch ( mailTemplate.getMailType() )
        {
            case PARCEL_NEW:
                setBasicParameters( parcel, parameters );
                break;
            case PARCEL_SEND:
                setBasicParameters( parcel, parameters );
                parameters.put( "sendDate", parcel.getSendDate().toString() );
                break;
            case PARCEL_IN_DELIVERY:
                setBasicParameters( parcel, parameters );
                parameters.put( "sendDate", parcel.getSendDate().toString() );
                break;
            case PARCEL_DELIVERED:
                setBasicParameters( parcel, parameters );
                parameters.put( "sendDate", parcel.getSendDate().toString() );
                parameters.put( "receiveDate", parcel.getReceiveDate().toString() );
                break;
            case PARCEL_CANCELLED:
                setBasicParameters( parcel, parameters );
                break;
            case PARCEL_RETURNED:
                setBasicParameters( parcel, parameters );
                break;
        }

        return parameters;
    }

    private MimeMessage createMessage( final MailTemplate mailTemplate, final ParcelDto parcelDto, final Map< String, String > parameters ) throws MessagingException
    {
        String mailContent = createMailContent( mailTemplate );

        MimeMessage message = mailSender.createMimeMessage();
        message.setSubject( mailTemplate.getSubject() );

        MimeMessageHelper helper = new MimeMessageHelper( message, true );
        helper.setTo( parcelDto.getRecipient().getEmail() );

        VelocityContext context = new VelocityContext();
        if ( parameters != null && !parameters.isEmpty() )
        {
            for ( final Map.Entry< String, String > entry : parameters.entrySet() )
            {
                context.put( entry.getKey(), entry.getValue() );
            }
        }
        StringWriter writer = new StringWriter();
        Velocity.evaluate( context, writer, "Parcel state changes", mailContent );
        message.setContent( writer.toString(), MESSAGE_CONTENT_TYPE );

        return message;
    }

    private String createMailContent( final MailTemplate mailTemplate )
    {
        StringBuilder builder = new StringBuilder();

        String mailBody = mailTemplate.getContent();
        String mailHeader = mailTemplateDao.findByType( MailTemplateEnum.HEADER ).getContent();
        String mailFooter = mailTemplateDao.findByType( MailTemplateEnum.FOOTER ).getContent();

        builder.append( mailHeader );
        builder.append( mailBody );
        builder.append( mailFooter );

        return builder.toString();
    }

    void setBasicParameters( final ParcelDto parcel, final Map< String, String > parameters )
    {
        parameters.put( "parcelNumber", parcel.getNumber() );
        parameters.put( "parcelSender", parcel.getSender().getEmail() );
        parameters.put( "parcelRecipient", parcel.getRecipient().getName() );
        parameters.put( "parcelStatus", parcel.getStatus().toString() );
    }

    private void sendMessage( final MimeMessage message )
    {
        mailSender.send( message );
    }
}
