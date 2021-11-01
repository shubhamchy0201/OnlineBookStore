package springproject.bookstore.OnlineBookStore.service;

import org.springframework.data.domain.Page;
import springproject.bookstore.OnlineBookStore.entity.Book;
import springproject.bookstore.OnlineBookStore.entity.Customer;
import java.util.List;

public interface CustomerService {
    public List<Customer> findAll();
    public Customer findById(int id);
    public void save(Customer customer);
    public void deleteById(int id);
    Customer findByEmail(String email);
    public boolean customerExists(String email);
    public List<Customer> searchBy(String theFirstName, String theLastName);
    Page<Customer> findPaginated(int pageNo, int pageSize);


}
