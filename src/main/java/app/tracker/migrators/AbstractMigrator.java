package app.tracker.migrators;

import javax.persistence.EntityNotFoundException;

public abstract class AbstractMigrator< T, V >
{
    public V copyEntity( T entity )
    {
        if ( entity == null )
        {
            throw new EntityNotFoundException( "Cannot migrate from entity into dto. Entity does not exist." );
        }

        return doCopyEntity( entity );
    }

    public T copyDto( V dto )
    {
        if ( dto == null )
        {
            throw new NullPointerException( "Cannot migrate from dto into entity. Dto is null." );
        }

        return doCopyDto( dto );
    }

    public abstract T doCopyDto( final V dto );

    public abstract V doCopyEntity( final T entity );
}
