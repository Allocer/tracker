package app.tracker.enums;


import java.io.Serializable;

public enum ParcelStatus implements Serializable
{
    NEW( "Przyjeta w oddziale" ),
    SEND( "Wyslana" ),
    CANCELED( "Anulowana" ),
    RETURNED( "Zwrocona" ),
    DELIVERY( "W doreczeniu" ),
    SORTING_PLANT( "W sortowni" );

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
