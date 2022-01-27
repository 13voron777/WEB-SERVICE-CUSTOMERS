package helpers;

import entity.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ServiceHelper {
    private SessionFactory sessionFactory;

    public ServiceHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addService(Service service){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(service);
        session.getTransaction().commit();
        session.close();
    }
}
