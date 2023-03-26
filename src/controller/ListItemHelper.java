
/**
 * @author - Alex Cox
 * CIS175 - Spring 2023
 * 3/15/2023
 */


package controller;


import model.ReadingListBooks;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class ListItemHelper {
	// Create EntityManagerFactory for "ReadingList" persistence unit
	static EntityManagerFactory emfactory =
			Persistence.createEntityManagerFactory("ReadingList");	
	
	// Method to insert a new ReadingListBooks item into the database
	public void insertItem(ReadingListBooks rl) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(rl);
		em.getTransaction().commit();
		em.close();
	}
	
	// Method to fetch all ReadingListBooks items from the database
	public List<ReadingListBooks> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<ReadingListBooks> allItems = em.createQuery("SELECT i FROM ReadingListBooks i").getResultList();
		return allItems; 
		}
	
	// Method to delete a ReadingListBooks item from the database
	public void deleteItem(ReadingListBooks toDelete) {
		 
		EntityManager em = emfactory.createEntityManager(); 
		em.getTransaction().begin();
		
		TypedQuery<ReadingListBooks> typedQuery = em.createQuery("select rl from ReadingListBooks rl where rl.bookTitle = :selectedTitle and rl.bookAuthor = :selectedAuthor", ReadingListBooks.class);
		//Substitute parameter with actual data from the toDelete item 
		typedQuery.setParameter("selectedTitle", toDelete.getbookTitle()); typedQuery.setParameter("selectedAuthor", toDelete.getbookAuthor());
		//we only want one result 
		typedQuery.setMaxResults(1);
		//get the result and save it into a new list item 
		ReadingListBooks result = null;
		 boolean noResult = true;

		    while (noResult) {
		        try {
		            result = typedQuery.getSingleResult();
		            noResult = false; // Exit the loop when a result is found
		        } catch (NoResultException e) {
		            System.out.println("No result found for the given query.");
		            // Handle the exception, e.g., set the result to null or provide a default value
		            result = null;
		            break; // Exit the loop when there are no results
		        }
		    }

		    if (result != null) {
		        // Remove it
		        em.remove(result);
		        em.getTransaction().commit();
		    } else {
		        // Handle the case when there is no result to remove
		        em.getTransaction().rollback();
		    }

		    em.close();
		}
		

	// Method to search for a ReadingListBooks item by ID
	public ReadingListBooks searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		ReadingListBooks found = em.find(ReadingListBooks.class, idToEdit);
		em.close();
		return found;
	}
	// Method to update a ReadingListBooks item 
	public void updateItem(ReadingListBooks toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	// Method to search for a ReadingListBooks item by Title
	public List<ReadingListBooks> searchForItemByStore(String bookTitle) { // TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager(); em.getTransaction().begin();
		TypedQuery<ReadingListBooks> typedQuery = em.createQuery("select rl from ReadingListBooks rl where rl.bookTitle = :selectedTitle", ReadingListBooks.class); 
		typedQuery.setParameter("selectedTitle", bookTitle);
		List<ReadingListBooks> foundItems = typedQuery.getResultList(); em.close();
		return foundItems; 
	}

	// Method to search for a ReadingListBooks item by author
	public List<ReadingListBooks> searchForItemByItem(String bookAuthor) { // TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager(); em.getTransaction().begin();
		TypedQuery<ReadingListBooks> typedQuery = em.createQuery("select rl from ReadingListBooks rl where rl.bookAuthor = :selectedAuthor", ReadingListBooks.class); 
		typedQuery.setParameter("selectedAuthor", bookAuthor);
		List<ReadingListBooks> foundItems = typedQuery.getResultList(); em.close();
		return foundItems;
	}
	
	
	public void cleanUp() {
		emfactory.close();
	}
	
}
