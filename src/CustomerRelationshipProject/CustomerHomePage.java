package CustomerRelationshipProject;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.BeansException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class CustomerHomePage {
	
	
	public static  BeanClass returnObject() {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext beanScopes = new ClassPathXmlApplicationContext("beanScope.xml");
		BeanClass beanScopeSample =  beanScopes.getBean("mainBean",BeanClass.class);
		
		return beanScopeSample;
	}
	

	@RequestMapping("/")
	public String getHomePage(Model theModel) {
		
		
//		org.hibernate.SessionFactory factory = new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
//	
		org.hibernate.Session session = returnObject().getSession();
		
			
			
			try {
				
				//will begin transaction now
				session.beginTransaction();
				
				@SuppressWarnings("unchecked")
				List<Customer> getListOfAllCustomerData = session.createQuery("from Customer").getResultList();
				
				for(Customer read : getListOfAllCustomerData) {
					System.out.println("read : "+read);
				}
				
				
				//adding attributes to be displayed in the home page.
				theModel.addAttribute("customers",getListOfAllCustomerData);
			
				
				//committed the transaction.
				session.getTransaction().commit();
				
			}catch (BeansException e) {
			// TODO Auto-generated catch block
				System.out.println();
				System.out.println();
				System.out.println(e.getMessage());
		}finally {
			session.close();
		}
		return "welcome";
	}


	//this method will trim the space received from the form input.
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor); 
	}
	
	
	@RequestMapping("editCustomerDetails") 
	public String goingToEditCustomerDetailsPage(Model model,
			HttpServletRequest httpServletRequest,
			HttpServletResponse response) {
		Customer customer = new Customer();
		
		//creating our modelAttribute object customer object here.
		model.addAttribute("customerObject", customer);
		//this will get all the submitted attributes in the customerObject.
		//which we shall then show it to our console.
		return "saveCustomer";
	}
	
	
	//this will persist our data.
	@RequestMapping("fetchedDetails")
	public String getCustomerDetailsIntoHomePage(@Valid @ModelAttribute("customerObject") Customer customer,
			BindingResult theBindingResult,
			Model model,
			@RequestParam("id") int customerId) {
		//RequestParam will take the variable/attribute of the dao object/entity as its variable.
		//if you are looking for the table id, then the value of the request param should be "id".
		
		
		CustomerHomePage addingCustomerObject = new CustomerHomePage();
		
		//this will persist data only if the previous email id is unique to that of the new entry.
		//after adding the customer i need to retrieve the data persisted.
		//thereafter it will go to the welcome page with the data added/persisted over to the 
		//object which will then be used to retrieve one by one using c for loop in the jsp page.
		
		
		
		//bindingResult error
		System.out.println("What is the error : "+theBindingResult);
		
		System.out.println("last name is : |"+customer.getLastName()+"|");
		
		
		org.hibernate.SessionFactory factory = new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
		
		org.hibernate.Session session = factory.getCurrentSession();
		
		
		if(theBindingResult.hasErrors()) {
			//if error is returned it will remain in the same page that is the "saveCustomer" page.
			//clicking the submit button it will keep the user on the same page.
			return "saveCustomer";
		}else {
			
			//put your confirm form details here.
			try {
				
				session.beginTransaction();
				
				//checking the id here
				System.out.println("id retrieved is : "+customerId);
				System.out.println();
	
				//in event of add customer the returned customer id is 0
				if(customerId==0) {
					
					//it will add the customer data into the db.
					System.out.println("Adding customer data!!!");
					addingCustomerObject.addCustomerData(customer);
					System.out.println("Added customer data!!!");
					
					
					//this will get all the list of customer from the db and read it.
					@SuppressWarnings("unchecked")
					List<Customer> getListOfAllCustomerData = session.
					createQuery("from Customer").getResultList();
					//from customer, you need to specify the class name not the table name.
					
					model.addAttribute("customers", getListOfAllCustomerData);	
					
					//this will read all the customer from the db.
					//readingListOfObject(getListOfAllCustomerData);

					
				}//in event of update the returned customer id is >0
				else {
					//else it will update the existing data in the db.
					//let's try now
					System.out.println();
					System.out.println("this will return list of updated objects");
					model.addAttribute("customers",addingCustomerObject.updateCustomerData(customer));
					System.out.println();
					System.out.println();
				}
				System.out.println();
				session.getTransaction().commit();
				
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}finally {
				session.close();
			}
			
			//if all the validation are success it will be redirected to the "welcome" page.
			return "welcome";
		}
		
	}
	
	
	//Method to persist data into the db.
	public void addCustomerData(Customer customer) {
		//checking if it fetches the data.
		System.out.println("customer data : "+customer);
		//yes it does.
		
		//now creating session to persist the data.
		org.hibernate.SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
		
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			//about to save "customer" object in the db
			
			session.save(customer);
			//saved "customer" object in the db.
			session.getTransaction().commit();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			session.close();
		}
		
	}
	
	
	
	
	//this is the url created in the save page using c:url.
	@GetMapping("showFormUpdate")
	public String updateFormData(@RequestParam("tableId") int tableId,
			Model theModel) {
		
		org.hibernate.SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
		
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			//now we will get the customer object based on the email_Id
			
			//be mindful session.get is used extensively only and only with the integer as its 
			//second argument.
			Customer myCustomerObject = session.get(Customer.class,tableId);
			
			
			//loggers -> used instead of sop.
			
			theModel.addAttribute("customerObject", myCustomerObject);
			
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			session.close();
		}
		
		return "saveCustomer";
	}
	
	
	
	//method for updating data in the db and also in the ui.
	/**
	 * 
	 * @param ourCustomerDataToUpdate -> this is used to grab the updated object from the save page
	 * from the fetchDetail request mapping.
	 * which shall then update the data and then all the list of data shall be retrieved using 
	 * createQuery which we shall then assign it to the list of customer objects.
	 * Which shall rather be used in the model object to be reflected in the welcome page.
	 * @return which shall then return us the list of object from the db after the data has been updated.
	 */
	public List<Customer> updateCustomerData(@ModelAttribute("customerObject") Customer ourCustomerDataToUpdate) {
		
		org.hibernate.SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
		
		org.hibernate.Session session = sessionFactory.getCurrentSession();
		
		List<Customer> updateList = new ArrayList<>();
		
		try {
			session.beginTransaction();
			
			
			//about to update data.
			System.out.println("About to update data!!!");
			session.update(ourCustomerDataToUpdate);
			System.out.println("data updated!!!");
			//updated data
			
			//now its time to retrieve the data from the db as list
			@SuppressWarnings("unchecked")
			List<Customer> getListOfAllUpdatedCustomerData = session.
			createQuery("from Customer").getResultList();
			
			
			System.out.println();
			System.out.println("reading list of updated customers");
			for(Customer read : getListOfAllUpdatedCustomerData) {
				System.out.println(read);
				updateList.add(read);
			}
			System.out.println();
			System.out.println();
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			session.close();
		}
		
		return updateList;
	}
	
	
	//this method will clear all the table entry.
	@RequestMapping("clearCustomerData")
	public String clearingData() {
		
		org.hibernate.SessionFactory factory = new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
		
		org.hibernate.Session session = factory.getCurrentSession();
		
		
		try {
			
			//will begin transaction now
			session.beginTransaction();
			

			int deleteId = session.createQuery("delete from Customer").executeUpdate();
			
			System.out.println(deleteId);
			
			
			//committed the transaction.
			session.getTransaction().commit();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			session.close();
		}
		
		
		return "welcome";
	}
	
	
	//this method is used for deleting specific object from the delete using the customer id.
	@SuppressWarnings("unchecked")
	@GetMapping("deleteFormUpdate")
	public String deleteFormUpdate(@RequestParam("tableId") int idToDelete,Model theModel) {
		System.out.println("id to delete : "+idToDelete);
		
		org.hibernate.SessionFactory factory = new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
		org.hibernate.Session session = factory.getCurrentSession();
		
		List<Customer> objectAfterDeletion = new ArrayList<>();
	
		try {
			session.beginTransaction();
			
			//delete customer based on the customer id received using RequestParam
			Query<Customer> theQuery = session.createQuery("delete from Customer where id=:customerId");
			theQuery.setParameter("customerId",idToDelete);
			theQuery.executeUpdate();
			
			
			System.out.println();
			System.out.println();
			List<Customer> getListOfAllUpdatedCustomerData = session.
			createQuery("from Customer").getResultList();
			for(Customer customer : getListOfAllUpdatedCustomerData) {
				System.out.println(customer);
				objectAfterDeletion.add(customer);
			}
			
			System.out.println();
			System.out.println();
			
			theModel.addAttribute("customers",objectAfterDeletion);
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			session.close();
		}
		return "welcome";
	}
	
	
} 