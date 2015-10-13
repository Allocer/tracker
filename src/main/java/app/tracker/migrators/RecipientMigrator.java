package app.tracker.migrators;

import app.tracker.dtos.RecipientDto;
import app.tracker.entities.Recipient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public final class RecipientMigrator extends AbstractMigrator< Recipient, RecipientDto >
{
    @Override
    public Recipient doCopyDto( final RecipientDto dto )
    {
        Recipient recipient = new Recipient();
        BeanUtils.copyProperties( dto, recipient );

        return recipient;
    }

    @Override
    public RecipientDto doCopyEntity( final Recipient place )
    {
        RecipientDto recipientDto = new RecipientDto();
        BeanUtils.copyProperties( place, recipientDto );

        return recipientDto;
    }
}

