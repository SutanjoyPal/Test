package MemberList;

public class Member {
    private int memberID;
    private String memberName;
    private String DOB;
    private static int maxBook=10;
    private int numberOfBooksIssued;

    public Member() {
    }

    public Member(int memberID, String memeberName, String DOB) {
        this.memberID = memberID;
        this.memberName = memeberName;
        this.DOB = DOB;
        this.numberOfBooksIssued = 0;
    }

    public int getMemberID() {
        return memberID;
    }

    public int getMaxBook() {
        return maxBook;
    }

    public int getIssueNumber() {
        return numberOfBooksIssued;
    }

    public String issueBook() {
        numberOfBooksIssued += 1;
        return ("Issue successful!");
    }

    public String returnBook() {
        if (numberOfBooksIssued == 0) {
            return ("No books purchased. Purchase a book first!");
        }
        numberOfBooksIssued -= 1;
        return ("Return Successful");
    }

    public void display() {
        System.out.println("Member ID: " + memberID);
        System.out.println("Name: " + memberName);
        System.out.println("Date of Birth: " + DOB);
        System.out.println("Number of books currently issued: " + numberOfBooksIssued);
    }
}

