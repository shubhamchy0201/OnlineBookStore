package springproject.bookstore.OnlineBookStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springproject.bookstore.OnlineBookStore.dao.BookRepository;
import springproject.bookstore.OnlineBookStore.entity.Book;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Book> findAll() {
        return bookRepository.findAllByOrderByTitleAsc();
    }

    @Override
    public Book findById(int id) {
        Optional<Book> result = bookRepository.findById(id);
        Book book = null;
        if (result.isPresent()){
            book = result.get();
        }
        else{
            throw new RuntimeException("Book not found with id - "+id);
        }
        return book;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findPaginated(int pageNo, int pageSize) {


            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            return this.bookRepository.findAll(pageable);

    }

   /* @Override
    public List<Book> searchBy(String theTitle, String theAuthor) {
        return bookRepository.
                findByTitleContainsAndAuthorContainsAllIgnoreCase(
                        theTitle, theAuthor);
    }*/

}
