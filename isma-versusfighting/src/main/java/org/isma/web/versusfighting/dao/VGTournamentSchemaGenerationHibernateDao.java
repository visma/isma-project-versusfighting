package org.isma.web.versusfighting.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.isma.web.versusfighting.model.Player;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import java.util.List;

//TODO faire une version propre, la c'est vraiment pas possible
public class VGTournamentSchemaGenerationHibernateDao implements VGTournamentSchemaGenerationDao{
    private SessionFactory sessionFactory;

    public VGTournamentSchemaGenerationHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void generateSchema() throws Exception {
        if (!existData()){
            initialPopulateSchema();
        }
    }

    //TODO crade !!!
    private boolean existData() {
        try {
            String req = "from Player";
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery(req);
            List<Player> list = (List<Player>) query.list();
            transaction.commit();
            return !list.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    //TODO crade!!!
    private void initialPopulateSchema() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-hibernate.xml"});
        Configuration conf = ((LocalSessionFactoryBean) context.getBean("&sessionFactoryVGTournament")).getConfiguration();
        SchemaExport schemaExport = new SchemaExport(conf);
        schemaExport.execute(true, true, false, true);
    }
}
