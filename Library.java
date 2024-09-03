import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        users = new ArrayList<>();
        // Add a default admin user
        users.add(new User("admin", "admin123", true));
        users.add(new User("simran", "simran123", true));
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void lendBook(int bookId, int memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book != null && member != null && book.getAvailableQuantity() > 0) {
            book.lend();
            member.borrowBook(book);
            System.out.println("Book lent successfully.");
        } else {
            System.out.println("Book lending failed. Either book is not available or member not found.");
        }
    }

    public void returnBook(int bookId, int memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book != null && member != null) {
            book.returnBook();
            member.returnBook(book);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book return failed. Either book or member not found.");
        }
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void displayMembers() {
        for (Member member : members) {
            System.out.println(member);
        }
    }

    public void searchMemberById(int memberId) {
        Member member = findMemberById(memberId);
        if (member != null) {
            System.out.println("Member found: " + member);
        } else {
            System.out.println("Member with ID " + memberId + " not found.");
        }
    }

    public void registerUser(String username, String password, boolean isAdmin) {
        users.add(new User(username, password, isAdmin));
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    private Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // User login
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = library.loginUser(username, password);
        if (user == null) {
            System.out.println("Invalid credentials.");
            return;
        } else {
            System.out.println("Welcome, " + user.getUsername());
        }

        boolean isAdmin = user.isAdmin();
        while (true) {
            System.out.println("\n1. Add Book (Admin Only)");
            System.out.println("2. Register Member");
            System.out.println("3. Lend Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Display Members");
            System.out.println("7. Search Member details by ID");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    if (isAdmin) {
                        System.out.print("Enter book ID: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        System.out.print("Enter book title: ");
                        String bookTitle = scanner.nextLine();
                        System.out.print("Enter book author: ");
                        String bookAuthor = scanner.nextLine();
                        System.out.print("Enter book quantity: ");
                        int bookQuantity = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        library.addBook(new Book(bookId, bookTitle, bookAuthor, bookQuantity));
                        System.out.println("Book added successfully.");
                    } else {
                        System.out.println("Permission denied. Only admins can add books.");
                    }
                    break;
                case 2:
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();
                    library.registerMember(new Member(memberId, memberName));
                    System.out.println("Member registered successfully.");
                    break;
                case 3:
                    System.out.print("Enter book ID to lend: ");
                    int bookIdToLend = scanner.nextInt();
                    System.out.print("Enter member ID: ");
                    int memberIdToLend = scanner.nextInt();
                    library.lendBook(bookIdToLend, memberIdToLend);
                    break;
                case 4:
                    System.out.print("Enter book ID to return: ");
                    int bookIdToReturn = scanner.nextInt();
                    System.out.print("Enter member ID: ");
                    int memberIdToReturn = scanner.nextInt();
                    library.returnBook(bookIdToReturn, memberIdToReturn);
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    library.displayMembers();
                    break;
                case 7:
                    System.out.print("Enter member ID to search: ");
                    int memberIdToSearch = scanner.nextInt();
                    library.searchMemberById(memberIdToSearch);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}
