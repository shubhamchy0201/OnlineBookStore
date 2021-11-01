package springproject.bookstore.OnlineBookStore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name="title")
    @NotBlank(message=" Enter Book title")
    @Pattern(regexp = "[a-zA-z]*",message="only alphabets are allowed")
    private String title;

    @Column(name="author")
    @NotBlank(message=" Enter Book author")
    @Pattern(regexp = "[a-zA-z]*",message="only alphabets are allowed")
    private String author;

    @Column(name="price")
    @NotNull(message=" Enter Book price")
    private int price;

    @ManyToMany(fetch=FetchType.LAZY,cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(name="book_customer",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="customer_id"))
    private List<Customer> customers;

    public  Book()
    {

    }

    public Book(int id, String title, String author, int price)
    {
        this.id=id;
        this.title=title;
        this.author=author;
        this.price=price;
    }
    public Book( String title, String author, int price)
    {
        this.title=title;
        this.author=author;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    // method to add Customer
    public void addCustomer(Customer theCustomer)
    {
        if(customers==null)
        {
            customers = new ArrayList<>();
        }
        customers.add(theCustomer);
    }


    @Override
    public String toString() {
        return
                "BookName= " + title;
    }

}
