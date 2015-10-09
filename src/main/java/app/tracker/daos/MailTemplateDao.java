package app.tracker.daos;

import app.tracker.entities.MailTemplate;
import app.tracker.enums.MailTemplateEnum;

public interface MailTemplateDao extends GenericDao< MailTemplate, Long >
{
    MailTemplate findByType( final MailTemplateEnum name );
}
