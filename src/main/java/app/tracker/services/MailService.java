package app.tracker.services;

import app.tracker.dtos.ParcelDto;

import javax.mail.MessagingException;

public interface MailService
{
    void sendMessage( final ParcelDto parcelDto ) throws MessagingException;
}
