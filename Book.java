public class Book {
    private int id;
    private String title;
    private String author;
    private int quantity;
    private int lentCount;

    public Book(int id, String title, String author, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.lentCount = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLentCount() {
        return lentCount;
    }

    public void lend() {
        if (lentCount < quantity) {
            lentCount++;
        }
    }

    public void returnBook() {
        if (lentCount > 0) {
            lentCount--;
        }
    }

    public int getAvailableQuantity() {
        return quantity - lentCount;
    }

    @Override
    public String toString() {
        return "Book [ID=" + id + ", Title=" + title + ", Author=" + author + ", Available=" + getAvailableQuantity() + ", Total=" + quantity + "]";
    }

    public String toStringBrief() {
        return "Book [ID=" + id + ", Title=" + title + ", Author=" + author + "]";
    }
}
