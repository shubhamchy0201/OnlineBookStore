package springproject.bookstore.OnlineBookStore.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import springproject.bookstore.OnlineBookStore.entity.Customer;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {


    private Customer customer;

    public CustomUserDetails(Customer customer) {
        this.customer=customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    public int getId()
    {
        return this.customer.getId();
    }

    public Customer getCustomer()
    {
        return this.customer;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }



    @Override
    public boolean isEnabled() {
        return true;
    }
}
