/**
 * @author - Alex Cox
 * CIS175 - Spring 2023
 * 3/15/2023
 */


package model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// Reading List item entity class
@Entity
@Table(name="items")
public class ReadingListBooks {
	@Id
	@GeneratedValue
	private int id;
	private String bookTitle;
	private String bookAuthor;

	// Default no-arg constructor
	public ReadingListBooks() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructor with arguments
	public ReadingListBooks(int id, String bookTitle, String bookAuthor) {
		super();
		this.id = id;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
	}

	// Getters and setters
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
	
	// Helper methods
	// Constructor with arguments for bookTitle and bookAuthor
	public ReadingListBooks(String bookTitle, String bookAuthor) { 
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor; 
	}
	 
	// Method to return book details as a string
	public String returnBookDetails() {
		return this.bookTitle + " by " + this.bookAuthor;
	}
	
}
