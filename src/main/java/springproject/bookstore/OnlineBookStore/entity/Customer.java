package springproject.bookstore.OnlineBookStore.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="first_name")
    @NotBlank(message="is required")
    @Pattern(regexp = "[a-zA-z]*",message="only alphabets are allowed")
    private String firstName;

    @Column(name="last_name")
    @NotBlank(message="is required")
    @Pattern(regexp = "[a-zA-z]*",message="only alphabets are allowed")
    private String lastName;



    @NotBlank(message="is required")
    @Column(name="email")
    @Pattern(regexp = "^(.+)@(.+)$",message="Enter valid email id")
    private String email;

    @Column(name="password")
    @NotBlank(message="is required")
    private String password;

    @ManyToMany(fetch=FetchType.LAZY,cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(name="book_customer",
            joinColumns = @JoinColumn(name="customer_id"),
            inverseJoinColumns = @JoinColumn(name="book_id"))
    private List<Book> books;


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Customer()
    {

    }

    public Customer(int id,String firstName, String lastName,String email,String password) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password=password;
    }
    public Customer(String firstName, String lastName,String email,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    // method to add Customer
    public void addBook(Book theBook)
    {
        if(books==null)
        {
            books = new ArrayList<>();
        }
        books.add(theBook);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public boolean isPresent(Book book){
        for (Book b : books){
            if (b.equals(book)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
