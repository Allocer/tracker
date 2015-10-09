package app.tracker.daos.impl;


import app.tracker.daos.ParcelDao;
import app.tracker.entities.Parcel;
import app.tracker.entities.Parcel_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

@Repository
public class ParcelDaoImpl extends GenericDaoImpl< Parcel, Long > implements ParcelDao
{
    public ParcelDaoImpl()
    {
        super( Parcel.class );
    }

    @Override
    public Parcel findByNumber( final String number )
    {
        initializeCriteriaBuilder();
        Predicate predicate = criteriaBuilder.equal( rootEntry.get( Parcel_.number ), number );
        CriteriaQuery< Parcel > query = criteriaQuery.where( predicate );

        return entityManager.createQuery( query ).getSingleResult();
    }
}
