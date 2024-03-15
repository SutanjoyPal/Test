// 3. For a library management system design BookList, MemberList and Transaction.Transaction packages. Booklist
// package will have the support to store book information in the list like book id, title, total number of
// copies purchased, and number of copies currently available. One can add book in list (verifying
// uniqueness of book id), change the attribute values (particularly, increase/decrease copies purchased,
// available as and when required), display particular book information (for a book id) and also total list.
// MemberList package will provide the service for maintaining member information. MemberList.Member information
// includes member id (unique), name, date of birth and number of books currently issued to him. There is
// a limit on number of books one can have at a point of time (it is same for all members). Transaction.Transaction
// package maintains a list of transaction. A transaction entry in the list keeps member id, book id of the
// book being issued. Supports are to be provided to modify the entries. An entry with member id ‘xxxx’
// can be used for adding a new entry.
// Using the packages, develop a system that can do the following:
// i) Add new book in booklist ii) Add more copies for a book iii) Show all book details iv) Show details of a
// book v) Add member in the list vi) show all members vii) show details of a member viii) Issue a book (
// check book validity and availability, check member validity and eligibility to get a book, once passes
// through the validations add an entry into transaction list and update counts in corresponding booklist
// and memberlist entries) ix) book return book ( check the validity of corresponding issue with book id
// and member id and once passes through the validations update the transaction entry by marking
// member id as ‘xxxx’ and update counts in corresponding booklist and memberlist entries)
// Consider the list as arrays. While working with arrays it is to be ensured that use of indices out of the
// range is reported.

import BookList.Book;
import MemberList.Member;
import Transaction.Transaction;

import java.util.ArrayList;
import java.util.Scanner;

class Library {
    private ArrayList<Book> b; 
    private ArrayList<Member> m; 
    private ArrayList<Transaction> t; 
    private int numberOfBooks; 
    private int numberOfMembers; 
    private int numberOfTransactions; 

    Library() {
        this.numberOfBooks = 0; 
        this.numberOfMembers = 0; 
        this.numberOfTransactions = 0; 
        b = new ArrayList<Book>(); 
        m = new ArrayList<Member>();
        t = new ArrayList<Transaction>(); 
    }

    public void addBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Book ID: "); 
        int bookID = sc.nextInt(); 
        for (int i = 0; i < numberOfBooks; i++) {
            Book tempBook = b.get(i); 
            if (tempBook.getBookID() == bookID) {
                System.out.println("Duplicate Book!"); 
                return; 
            }
        }
        sc.nextLine();
        System.out.println("Enter Book Title: "); 
        String title = sc.nextLine();
        System.out.println("Enter the number: "); 
        int totalCopiesAvailable = sc.nextInt(); 
        Book newBook = new Book(bookID, title, totalCopiesAvailable); 
        b.add(newBook); 
        System.out.println("Book added successfully!"); 
        numberOfBooks += 1; 
        
    }

    public void addCopy() {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter Book ID: "); 
        int bookID = sc.nextInt(); 
        for (int i = 0; i < numberOfBooks; i++) {
            Book tempBook = b.get(i); 
            if (tempBook.getBookID() == bookID) {
                System.out.println("Enter number of copies to add: "); 
                System.out.println(tempBook.addCopy(sc.nextInt()));
                return;
            }
        }

        System.out.println("Book not found!");
    }

    public void showAllBookDetails() {
        for (int i = 0; i < numberOfBooks; i++) {
            Book tempBook = b.get(i); 
            tempBook.display(); 
        }
    }

    public void showBook() {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter book ID: "); 
        int bookID = sc.nextInt(); 
        for (int i = 0; i < numberOfBooks; i++) {
            Book tempBook = b.get(i); 
            if (tempBook.getBookID() == bookID) {
                tempBook.display();

                return; 
            }
        }
        System.out.println("Book not found!"); 
    }

    public void addMember() {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter Member ID: "); 
        int memberID = sc.nextInt(); 
        for (int i = 0; i < numberOfMembers; i++) {
            Member tempMember = m.get(i); 
            if (tempMember.getMemberID() == memberID) {
                System.out.println("Duplicate Member ID found!");
                return;
            }
        }
        sc.nextLine();
        System.out.println("Enter member name: "); 
        String memberName = sc.nextLine(); 
        System.out.println("Enter member Date of Birth: "); 
        String DOB = sc.nextLine();
        Member newMember = new Member(memberID, memberName, DOB); 
        m.add(newMember); 
        System.out.println("Member added successfully"); 
        numberOfMembers += 1; 
    }

    public void showAllMemberDetails() {
        for (int i = 0; i < numberOfMembers; i++) {
            Member tempMember = m.get(i); 
            tempMember.display();
        }
    }

    public void showMember() {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter the ID of the Member: "); 
        int memberID = sc.nextInt(); 
        for (int i = 0; i < numberOfMembers; i++) {
            Member tempMember = m.get(i); 
            if (tempMember.getMemberID() == memberID) {
                tempMember.display(); 
                return; 
            }
        }
        System.out.println("Member not found!"); 
    }

    public void issueBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the required Book ID: ");
        int bookID = sc.nextInt();
        int availableBook = 0; 
        int bookIndex = -1; 

        for (int i = 0; i < numberOfBooks; i++) {
            Book tempBook = b.get(i); 
            if (tempBook.getBookID() == bookID) {
                if (tempBook.getCopiesAvailable() > 0) {
                    bookIndex = i; 
                    availableBook = 1; 
                    break;
                } else {
                    System.out.println("No copies available at the moment!"); 
                    return;
                }
            }
        }

        if (availableBook == 0) {
            System.out.println("Book not found!");
            return;
        }

        System.out.println("Enter the Member ID");
        int memberID = sc.nextInt(); 
        int availableMember = 0;
        int memberIndex = -1; 

        for (int i = 0; i < numberOfMembers; i++) {
            Member tempMember = m.get(i); 
            if (tempMember.getMemberID() == memberID) {
                if (tempMember.getIssueNumber() < tempMember.getMaxBook()) {
                    availableMember = 1;
                    memberIndex = i;
                    break;
                } else {
                    System.out.println("Max number of books issued! Please return a book first!");
                    return;
                }
            }
        }

        if (availableMember == 0) {
            System.out.println("Member not found!");
            return; 
        }

        Transaction tempTransaction = new Transaction(memberID, bookID); 
        t.add(tempTransaction); 
        numberOfTransactions += 1; 

        System.out.println(b.get(bookIndex).purchaseBook() + "\n" + m.get(memberIndex).issueBook()); 
    }

    public void returnBook() {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter the Book ID: "); 
        int bookID = sc.nextInt(); 
        System.out.println("Enter the Member ID: ");
        int memberID = sc.nextInt(); 

        int transactionIndex = -1;

        for (int i = 0; i < numberOfTransactions; i++) {
            Transaction tempTransaction = t.get(i);
            if (tempTransaction.getBookID() == bookID && tempTransaction.getMemberID() == memberID) {
                transactionIndex = i; // Storing the index of the transaction
                break;
            }
        }

        if (transactionIndex == -1) {
            System.out.println("No such transaction found! Please enter valid transaction details"); // Displaying an
                                                                                                     // error message if
                                                                                                     // the transaction
                                                                                                     // is not found

            return; // Exiting the method
        }

        t.get(transactionIndex).changeTransactionMemberID(-1); // Changing the transaction member ID to -1
        for (int i = 0; i < numberOfBooks; i++) {
            Book tempBook = b.get(i); // Getting the book at index i from the book ArrayList
            if (tempBook.getBookID() == bookID) {
                System.out.println(tempBook.addCopy(1)); // Adding a copy of the book
                break;
            }
        }

        for (int i = 0; i < numberOfMembers; i++) {
            Member tempMember = m.get(i); // Getting the member at index i from the member ArrayList
            if (tempMember.getMemberID() == memberID) {
                System.out.println(tempMember.returnBook()); // Returning the book
                break;
            }
        }
    }

}

public class three {
    public static void main(String args[]) {
        Library l = new Library(); // Creating a new Library object
        Scanner sc = new Scanner(System.in); // Creating a new Scanner object to read input
        while (true) {
            System.out.println("************************"); // Displaying the menu options
            System.out.println("1. Add new book in booklist");
            System.out.println("2. Add more copies for a book");
            System.out.println("3. Show all book details");
            System.out.println("4. Show details of a book");
            System.out.println("5. Add member in the list");
            System.out.println("6. Show all members");
            System.out.println("7. Show details of a member");
            System.out.println("8. Issue a book");
            System.out.println("9. Return book");
            System.out.println("************************");
            System.out.print("Enter your choice: "); // Prompting the user to enter their choice
            int n = sc.nextInt(); // Reading the user's choice

            switch (n) { // Switching based on the user's choice
                case 1:
                    l.addBook(); // Calling the addBook() method of the Library object
                    break;
                case 2:
                    l.addCopy(); // Calling the addCopy() method of the Library object
                    break;
                case 3:
                    l.showAllBookDetails(); // Calling the showAllBookDetails() method of the Library object
                    break;
                case 4:
                    l.showBook(); // Calling the showBook() method of the Library object
                    break;
                case 5:
                    l.addMember(); // Calling the addMember() method of the Library object
                    break;
                case 6:
                    l.showAllMemberDetails(); // Calling the showAllMemberDetails() method of the Library object
                    break;
                case 7:
                    l.showMember(); // Calling the showMember() method of the Library object
                    break;
                case 8:
                    l.issueBook(); // Calling the issueBook() method of the Library object
                    break;
                case 9:
                    l.returnBook(); // Calling the returnBook() method of the Library object
                    break;
                default:
                    return; // Exiting the program if an invalid choice is entered
            }
        }
    }
}
