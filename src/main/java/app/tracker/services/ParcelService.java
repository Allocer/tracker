package app.tracker.services;

import app.tracker.dtos.ParcelDto;

import javax.mail.MessagingException;
import java.util.List;

public interface ParcelService
{
    ParcelDto persist( final ParcelDto parcelDto );

    ParcelDto persistWithMail( final ParcelDto parcelDto ) throws MessagingException;

    ParcelDto find( final Long id );

    ParcelDto findByNumber( final String number );

    List< ParcelDto > findAll();

    ParcelDto update( final ParcelDto parcelDto );

    void delete( final Long id );
}
