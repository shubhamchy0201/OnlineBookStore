package springproject.bookstore.OnlineBookStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springproject.bookstore.OnlineBookStore.entity.Book;
import springproject.bookstore.OnlineBookStore.entity.Customer;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // add a method to sort by last name
    public List<Book> findAllByOrderByTitleAsc();
    // add a method to search by title and author
    public List<Book> findByTitleContainsAndAuthorContainsAllIgnoreCase(
            String theTitle, String theAuthor);
}
