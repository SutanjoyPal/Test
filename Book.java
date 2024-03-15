package BookList;

public class Book {
    private int bookID; 
    private String title; 
    private int totalCopiesPurchased; 
    private int copiesAvailable; 

    public Book() {}

    public Book(int bookID, String title, int copiesAvailable) {
        this.bookID = bookID;
        this.title = title; 
        this.totalCopiesPurchased = 0; 
        this.copiesAvailable = copiesAvailable;
    }

    public int getBookID() {
        return bookID; 
    }

    public int getCopiesAvailable() {
        return copiesAvailable; 
    }

    public String purchaseBook() {
        totalCopiesPurchased += 1; 
        copiesAvailable -= 1; 
        return "Purchased Successfully!";
    }

    public String addCopy(int n) {
        copiesAvailable += n; 
        return ("Copies added successfully! Total copies available for this BookID is: " + copiesAvailable); 
    }

    public void changeBookID(int bookID) {
        this.bookID = bookID; 
    }

    public void changeBookTitle(String title) {
        this.title = title; 
    }

    public void display() {
        System.out.println("Book ID: " + bookID); 
        System.out.println("Title: " + title); 
        System.out.println("Total number of copies purchased: " + totalCopiesPurchased); 
        System.out.println("Copies available: " + copiesAvailable); 
    }
}