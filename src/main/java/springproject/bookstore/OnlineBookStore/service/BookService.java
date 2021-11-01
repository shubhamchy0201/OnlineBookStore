package springproject.bookstore.OnlineBookStore.service;

import org.springframework.data.domain.Page;
import springproject.bookstore.OnlineBookStore.entity.Book;
import springproject.bookstore.OnlineBookStore.entity.Customer;

import java.util.List;

public interface BookService {
    public List<Book> findAll();
    public Book findById(int id);
    public void save(Book book);
    public void deleteById(int id);
    Page<Book> findPaginated(int pageNo, int pageSize);
   // public List<Book> searchBy(String theTitle, String theAuthor);
}
