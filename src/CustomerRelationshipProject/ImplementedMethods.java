package CustomerRelationshipProject;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;


public class ImplementedMethods implements CustomerRelationshipMethods {

	@Override
	public Session getSession() {
		// TODO Auto-generated method stub
		org.hibernate.SessionFactory factory = new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
		
		org.hibernate.Session session = factory.getCurrentSession();
		
		return session;
	}
	

}
