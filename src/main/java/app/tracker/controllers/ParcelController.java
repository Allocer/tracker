package app.tracker.controllers;

import app.tracker.dtos.ParcelDto;
import app.tracker.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping( "/api/parcels" )
public class ParcelController extends AbstractController
{
    @Autowired
    private ParcelService parcelService;

    @RequestMapping( method = RequestMethod.POST )
    public ParcelDto addParcel( @RequestBody final ParcelDto parcelDto ) throws MessagingException
    {
        return parcelService.persistWithMail( parcelDto );
    }

    @RequestMapping( value = "/{number}", method = RequestMethod.GET )
    public ParcelDto findParcelByNumber( @PathVariable( "number" ) final String parcelNumber )
    {
        return parcelService.findByNumber( parcelNumber );
    }

    @RequestMapping( method = RequestMethod.PATCH )
    public ParcelDto patchParcelStatus( @RequestBody final ParcelDto parcelDto ) throws MessagingException
    {
        return parcelService.updateStatusWithMail( parcelDto );
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteParcel( @PathVariable( "id" ) final Long id )
    {
        parcelService.delete( id );
    }
}
