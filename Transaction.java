package Transaction;

public class Transaction {
    private int memberID;
    private int bookID;

    public Transaction() {
    }

    public Transaction(int memberID, int bookID) {
        this.memberID = memberID;
        this.bookID = bookID;
    }

    public int getMemberID() {
        return memberID;
    }

    public int getBookID() {
        return bookID;
    }

    public void changeTransactionMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void changeTransactionBookID(int bookID) {
        this.bookID = bookID;
    }
}

