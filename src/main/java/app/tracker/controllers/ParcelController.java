package app.tracker.controllers;

import app.tracker.dtos.ParcelDto;
import app.tracker.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping( "/api/parcels" )
public class ParcelController extends AbstractController
{
    @Autowired
    private ParcelService parcelService;

    @RequestMapping( method = RequestMethod.POST, consumes = JSON_UTF8, produces = JSON_UTF8 )
    public ParcelDto addParcel( @RequestBody @Validated final ParcelDto parcelDto ) throws MessagingException
    {
        return parcelService.persistWithMail( parcelDto );
    }

}
