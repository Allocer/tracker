package app.tracker.daos.impl;

import app.tracker.daos.GenericDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public abstract class GenericDaoImpl< T, PK extends Serializable > implements GenericDao< T, PK >
{
    @PersistenceContext
    protected EntityManager entityManager;

    protected CriteriaBuilder criteriaBuilder;
    protected CriteriaQuery< T > criteriaQuery;
    protected Root< T > rootEntry;

    private Class< T > type;

    public GenericDaoImpl()
    {
    }

    public GenericDaoImpl( Class< T > type )
    {
        this.type = type;
    }

    public T persist( T o )
    {
        entityManager.persist( o );
        return entityManager.merge( o );
    }

    public T find( PK id )
    {
        return ( T ) entityManager.find( type, id );
    }

    public T update( T o )
    {
        return entityManager.merge( o );
    }

    public void delete( T o )
    {
        entityManager.remove( entityManager.contains( o ) ? o : entityManager.merge( o ) );
    }

    public List< T > findAll()
    {
        initializeCriteriaBuilder();
        return findByCriteria();
    }

    protected void initializeCriteriaBuilder()
    {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery( type );
        rootEntry = criteriaQuery.from( type );
    }

    protected List< T > findByCriteria()
    {
        CriteriaQuery< T > all = criteriaQuery.select( rootEntry );
        TypedQuery< T > allQuery = entityManager.createQuery( all );

        return allQuery.getResultList();
    }
}