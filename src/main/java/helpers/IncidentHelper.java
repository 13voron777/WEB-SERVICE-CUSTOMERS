package helpers;

import entity.Incident;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class IncidentHelper {
    private SessionFactory sessionFactory;

    public IncidentHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addIncident(Incident incident){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(incident);
        session.getTransaction().commit();
        session.close();
    }
}
