package app.tracker.services;

import app.tracker.dtos.ParcelDto;
import app.tracker.enums.MailTemplateEnum;

import javax.mail.MessagingException;

public interface MailService
{
    void sendMessage( final ParcelDto parcelDto, final MailTemplateEnum mailType ) throws MessagingException;
}
