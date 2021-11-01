package springproject.bookstore.OnlineBookStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springproject.bookstore.OnlineBookStore.entity.Book;
import springproject.bookstore.OnlineBookStore.entity.Customer;
import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // add a method to sort by last name
    public List<Customer> findAllByOrderByLastNameAsc();

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    Customer findByEmail(String email);

  // add a method to search by first name and last name

    public List<Customer> findByFirstNameContainsAndLastNameContainsAllIgnoreCase(
            String theFirstName, String theLastName);

}
