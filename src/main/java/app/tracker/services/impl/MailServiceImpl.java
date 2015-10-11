package app.tracker.services.impl;

import app.tracker.daos.MailTemplateDao;
import app.tracker.dtos.ParcelDto;
import app.tracker.entities.MailTemplate;
import app.tracker.enums.MailTemplateEnum;
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
    public void sendMessage( final ParcelDto parcelDto, final MailTemplateEnum mailType ) throws MessagingException
    {
        MailTemplate mailTemplate = mailTemplateDao.findByType( mailType );
        Map< String, String > parameters = createParameters( mailType, parcelDto );
        MimeMessage message = createMessage( mailTemplate, parcelDto, parameters );

        sendMessage( message );
    }

    private Map< String, String > createParameters( final MailTemplateEnum template, final ParcelDto parcel )
    {
        Map< String, String > parameters = new HashMap<>();
        switch ( template )
        {
            case PARCEL_NEW:
                parameters.put( "parcelNumber", parcel.getNumber() );
                parameters.put( "parcelSender", parcel.getSender().getName() );
                parameters.put( "parcelRecipient", parcel.getRecipient().getName() );
                parameters.put( "parcelStatus", parcel.getStatus().toString() );
                break;
            case PARCEL_CANCELLED:
                break;
            case PARCEL_DELIVERED:
                break;
            case PARCEL_STATUS_CHANGED:
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

    private void sendMessage( final MimeMessage message )
    {
        mailSender.send( message );
    }
}
