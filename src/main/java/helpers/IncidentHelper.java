package helpers;

import entity.Incident;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<Incident> getAllIncidents(){
        Session session = sessionFactory.openSession();
        CriteriaQuery<Incident> criteriaQuery = session.getCriteriaBuilder().createQuery(Incident.class);
        criteriaQuery.from(Incident.class);
        return session.createQuery(criteriaQuery).getResultList();
    }

    public List<Incident> getAllActiveIncidents(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Incident> criteriaQuery = cb.createQuery(Incident.class);
        Root<Incident> root = criteriaQuery.from(Incident.class);
        criteriaQuery.select(root)
                .where(cb.equal(root.get("isActive"), true));
        return session.createQuery(criteriaQuery).getResultList();
    }

    public void closeIncident(int id){
        Session session = sessionFactory.openSession();
        Incident incident = session.get(Incident.class, id);
        incident.setActive(false);
        session.beginTransaction();
        session.save(incident);
        session.getTransaction().commit();
        session.close();
    }
}
