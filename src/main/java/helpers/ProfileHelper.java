package helpers;

import entity.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProfileHelper {
    private SessionFactory sessionFactory;

    public ProfileHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addProfile(Profile profile){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(profile);
        session.getTransaction().commit();
        session.close();
    }
}
