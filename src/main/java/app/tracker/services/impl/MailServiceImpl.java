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

        Map< String, String > parameters = new HashMap<>();
        parameters.put( "username", "Gniewko" );
        MimeMessage message = createMessage( mailTemplate, parcelDto, parameters );

        sendMessage( message );
    }

    private MimeMessage createMessage( final MailTemplate mailTemplate, final ParcelDto parcelDto, final Map< String, String > parametrs ) throws MessagingException
    {
        String mailBody = mailTemplate.getContent();

        MimeMessage message = mailSender.createMimeMessage();
        message.setSubject( mailTemplate.getSubject() );

        MimeMessageHelper helper = new MimeMessageHelper( message );
        helper.setTo( parcelDto.getRecipient().getEmail() );

        VelocityContext context = new VelocityContext();
        for ( final Map.Entry< String, String > entry : parametrs.entrySet() )
        {
            context.put( entry.getKey(), entry.getValue() );
        }
        StringWriter writer = new StringWriter();
        Velocity.evaluate( context, writer, "Email", mailBody );
        message.setContent( writer.toString(), MESSAGE_CONTENT_TYPE );

        return message;
    }

    private void sendMessage( final MimeMessage message )
    {
        mailSender.send( message );
    }

    private Map< String, String > setParameters( final String key, final String value )
    {
        Map< String, String > parameters = new HashMap<>();
        parameters.put( key, value );

        return parameters;
    }
}
