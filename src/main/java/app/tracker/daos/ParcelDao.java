package app.tracker.daos;

import app.tracker.entities.Parcel;

public interface ParcelDao extends GenericDao< Parcel, Long >
{
    Parcel findByNumber( final String number );
}
