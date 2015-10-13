package app.tracker.migrators;

import app.tracker.dtos.SenderDto;
import app.tracker.entities.Sender;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public final class SenderMigrator extends AbstractMigrator< Sender, SenderDto >
{
    @Override
    public Sender doCopyDto( final SenderDto dto )
    {
        Sender sender = new Sender();
        BeanUtils.copyProperties( dto, sender );

        return sender;
    }

    @Override
    public SenderDto doCopyEntity( final Sender sender )
    {
        SenderDto senderDto = new SenderDto();
        BeanUtils.copyProperties( sender, senderDto );

        return senderDto;
    }
}
