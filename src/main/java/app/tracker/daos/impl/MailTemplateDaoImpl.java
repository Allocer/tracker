package app.tracker.daos.impl;

import app.tracker.daos.MailTemplateDao;
import app.tracker.entities.MailTemplate;
import app.tracker.entities.MailTemplate_;
import app.tracker.enums.MailTemplateEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

@Repository
public class MailTemplateDaoImpl extends GenericDaoImpl< MailTemplate, Long > implements MailTemplateDao
{
    public MailTemplateDaoImpl()
    {
        super( MailTemplate.class );
    }

    @Override
    public MailTemplate findByType( final MailTemplateEnum mailType )
    {
        initializeCriteriaBuilder();
        Predicate predicate = criteriaBuilder.equal( rootEntry.get( MailTemplate_.mailType ), mailType );
        CriteriaQuery< MailTemplate > query = criteriaQuery.where( predicate );

        return entityManager.createQuery( query ).getSingleResult();
    }
}
