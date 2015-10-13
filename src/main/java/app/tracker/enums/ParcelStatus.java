package app.tracker.enums;


import java.io.Serializable;

public enum ParcelStatus implements Serializable
{
    NEW( "Przyjęta w oddziale" ),
    SEND( "Wysłana" ),
    IN_DELIVERY( "W doręczeniu" ),
    DELIVERED( "Odebrana" ),
    CANCELED( "Anulowana" ),
    RETURNED( "Zwrócona" );

    private String name;

    ParcelStatus( final String name )
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
