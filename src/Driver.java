/**
 * @author - Alex Cox
 * CIS175 - Spring 2023
 * 3/15/2023
 */

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controller.ListItemHelper;
import model.ReadingListBooks;

public class Driver {
	static Scanner in = new Scanner(System.in);
	static ListItemHelper lih = new ListItemHelper();

	// Function to add an item (book) to the reading list
	private static void addAnItem() {
		System.out.print("Enter a book: ");
		String store = in.nextLine();
		System.out.print("Enter the author: ");
		String item = in.nextLine();
		ReadingListBooks toAdd = new ReadingListBooks(store, item);
		lih.insertItem(toAdd);
	}

	// Function to delete an item (book) from the reading list
	private static void deleteAnItem() {
		// Prompts user to enter a book title to be deleted
		System.out.print("Enter the book title to delete: ");
		String bookTitle = in.nextLine();
		// Enter the book's author
		System.out.print("Enter the author to delete: ");
		String bookAuthor = in.nextLine();

		ReadingListBooks toDelete = new ReadingListBooks(bookTitle, bookAuthor);
		lih.deleteItem(toDelete);
	}

	// Function to edit an item (book) in the reading list
	private static void editAnItem() {
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Book Title");
		System.out.println("2 : Search by Author");
		 // Validate input
        int searchBy = -1;
        while (true) {
            try {
                searchBy = in.nextInt();
                in.nextLine();
                if (searchBy == 1 || searchBy == 2) {
                    break;
                } else {
                    System.out.println("Invalid selection. Please choose 1 or 2.");
                    System.out.println("1 : Search by Book Title");
                    System.out.println("2 : Search by Author");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                in.next();
            }
        }
        
		List<ReadingListBooks> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the book title: ");
			String bookTitle = in.nextLine();
			foundItems = lih.searchForItemByStore(bookTitle);
		} else {
			System.out.print("Enter the author: ");
			String bookAuthor = in.nextLine();
			foundItems = lih.searchForItemByItem(bookAuthor);
		}

		// Display search results and allow user to edit the selected item
		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (ReadingListBooks l : foundItems) {
				System.out.println("ID of book in database:" + l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			ReadingListBooks toEdit = lih.searchForItemById(idToEdit);
			System.out.println("Retrieved " + toEdit.getbookTitle() + " from " + toEdit.getbookAuthor());
			System.out.println("1 : Update Book Title");
			System.out.println("2 : Update Author");
			  // Validate input
	        int update = -1;
	        while (true) {
	            try {
	                update = in.nextInt();
	                in.nextLine();
	                if (update == 1 || update == 2) {
	                    break;
	                } else {
	                    System.out.println("Invalid selection. Please choose 1 or 2.");
	                    System.out.println("1 : Update Book Title");
	                    System.out.println("2 : Update Author");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input. Please enter a number (1 or 2).");
	                in.next();
	            }
	        }

			// Update book title or author based on user's choice
			if (update == 1) {
				System.out.print("New Book: ");
				String newBook = in.nextLine();
				toEdit.setbookTitle(newBook);
			} else if (update == 2) {
				System.out.print("New Author: ");
				String newAuthor = in.nextLine();
				toEdit.setbookAuthor(newAuthor);
			}

			lih.updateItem(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}

	// Main method
	public static void main(String[] args) {
		runMenu();
	}
	public static void runMenu() {
		boolean goAgain = true;
		//Display menu to prompt user for choice
		System.out.println("--- Reading list ---");
		while (goAgain) {
			
			
			
			System.out.println("  Options:");
			System.out.println("  1 -- Add a book");
			System.out.println("  2 -- Edit book entry");
			System.out.println("  3 -- Delete an book");
			System.out.println("  4 -- View the list");
			System.out.println("  5 -- Exit the program");
			System.out.print("  You selected: ");
			
            //  input validation
            int selection = -1;
            while (true) {
                try {
                    selection = in.nextInt();
                    in.nextLine();
        		    // Check if the input is within the valid range
                    if (selection >= 1 && selection <= 5) {
                        break;
                    } else { //if user enters number of range
                        System.out.println("  Invalid selection. Please choose a number between 1 and 5.");
                        System.out.print("  You selected: ");
                    }
                } catch (InputMismatchException e) { //if user inputs mismatching input(for example enters a letter instead of a number)
                    System.out.println("  Invalid input. Please enter a number between 1 and 5.");
                    System.out.print("  You selected: ");
                    in.next();
                }
            }
			
            //if structure for deciding what action to take based on user input
			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				lih.cleanUp();
				System.out.println("   SEE YA!   ");
				goAgain = false;
			}

		}

	}

	//Method for showing the list
	private static void viewTheList() {
		
		List<ReadingListBooks> allItems = lih.showAllItems();
		for(ReadingListBooks singleItem : allItems){
			System.out.println(singleItem.returnBookDetails()); 
			}
	}

}

	// Method to run the main
