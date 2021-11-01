package springproject.bookstore.OnlineBookStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springproject.bookstore.OnlineBookStore.dao.CustomerRepository;
import springproject.bookstore.OnlineBookStore.entity.Book;
import springproject.bookstore.OnlineBookStore.entity.Customer;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Customer findById(int id) {
        Optional<Customer> result = customerRepository.findById(id);
        Customer customer = null;
        if (result.isPresent()){
            customer = result.get();
        }
        else{
            throw new RuntimeException("customer not found with id - "+id);
        }
        return customer;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public boolean customerExists(String email){
        if (findByEmail(email) != null){
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> searchBy(String theFirstName, String theLastName) {
        return customerRepository.
                findByFirstNameContainsAndLastNameContainsAllIgnoreCase(
                        theFirstName, theLastName);
    }
    @Override
    public Page<Customer> findPaginated(int pageNo, int pageSize) {


        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.customerRepository.findAll(pageable);

}
}
