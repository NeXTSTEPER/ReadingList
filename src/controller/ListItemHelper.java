
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
		ReadingListBooks result = typedQuery.getSingleResult();
		//remove it 
		em.remove(result); 
		em.getTransaction().commit(); 
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
