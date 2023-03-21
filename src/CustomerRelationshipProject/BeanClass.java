package CustomerRelationshipProject;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class BeanClass {

	
	@Autowired
	CustomerRelationshipMethods customerRelationshipMethods;
	
	
	public BeanClass(CustomerRelationshipMethods customerRelationshipMethods) {
		this.customerRelationshipMethods = customerRelationshipMethods;
	}
	
	
	public Session getSession()
	{
		return this.customerRelationshipMethods.getSession();
	}
	
	
	
	
	
}
