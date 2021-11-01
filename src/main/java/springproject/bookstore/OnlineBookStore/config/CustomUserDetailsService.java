package springproject.bookstore.OnlineBookStore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import springproject.bookstore.OnlineBookStore.entity.Customer;
import springproject.bookstore.OnlineBookStore.service.CustomerService;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer theCustomer = customerService.findByEmail(s);
        if (theCustomer == null) {
            throw new UsernameNotFoundException("Customer not found");
        }
        return new CustomUserDetails(theCustomer);

    }
}
