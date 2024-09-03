import java.util.ArrayList;
import java.util.List;

public class Member {
    private int id;
    private String name;
    private List<Book> borrowedBooks;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Member [ID=").append(id).append(", Name=").append(name).append(", Borrowed Books=[");
        for (Book book : borrowedBooks) {
            sb.append(book.toStringBrief()).append(", ");
        }
        if (!borrowedBooks.isEmpty()) {
            sb.setLength(sb.length() - 2); // remove the trailing comma and space
        }
        sb.append("]]");
        return sb.toString();
    }
}
