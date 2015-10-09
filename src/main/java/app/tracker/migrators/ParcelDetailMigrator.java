package app.tracker.migrators;

import app.tracker.dtos.ParcelDetailDto;
import app.tracker.entities.ParcelDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public final class ParcelDetailMigrator extends AbstractMigrator< ParcelDetail, ParcelDetailDto >
{
    @Override
    public ParcelDetail doCopyDto( final ParcelDetailDto dto )
    {
        ParcelDetail parcelDetail = new ParcelDetail();
        BeanUtils.copyProperties( dto, parcelDetail );

        return parcelDetail;
    }

    @Override
    public ParcelDetailDto doCopyEntity( final ParcelDetail parcelDetail )
    {
        ParcelDetailDto parcelDetailDto = new ParcelDetailDto();
        BeanUtils.copyProperties( parcelDetail, parcelDetailDto );

        return parcelDetailDto;
    }
}
