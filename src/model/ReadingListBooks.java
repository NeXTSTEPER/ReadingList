
/**
 * @author - Alex Cox
 * CIS175 - Spring 2023
 * 2/22/2023
 */

package model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//Reading List item entity class

@Entity
@Table(name="items")
public class ReadingListBooks {
	@Id
	@GeneratedValue
	private int id;
	private String bookTitle;
	private String bookAuthor;

	
	//default no arg constructor

	public ReadingListBooks() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReadingListBooks(int id, String store, String item) {
		super();
		this.id = id;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
	}

	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getbookTitle() {
		return bookTitle;
	}

	public void setbookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getbookAuthor() {
		return bookAuthor;
	}

	public void setbookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	//Helper methods
	 public ReadingListBooks(String bookTitle, String bookAuthor){ 
		 this.bookTitle = bookTitle;
		 this.bookAuthor = bookAuthor; 
	 
	 }
	 
	 public String returnBookDetails( ) {
		 return this.bookTitle + " by " + this.bookAuthor;
	 }
	
}
