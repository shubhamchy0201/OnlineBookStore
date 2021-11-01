package springproject.bookstore.OnlineBookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springproject.bookstore.OnlineBookStore.entity.Book;
import springproject.bookstore.OnlineBookStore.entity.Customer;
import springproject.bookstore.OnlineBookStore.service.BookService;
import springproject.bookstore.OnlineBookStore.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @GetMapping("/home")
    public String showMyHomePage() {

        return "admin-home";

    }

    @GetMapping("/list")
    public String listBook(Model theModel) {

        // get Book from db
        List<Book> theBooks = bookService.findAll();

        // add to the spring model
        theModel.addAttribute("books", theBooks);

        return "book/list-books";
    }
    @GetMapping("/customer-list")
    public String listCustomer(Model theModel) {

        // get Book from db
        List<Customer> theCustomer = customerService.findAll();

        // add to the spring model
        theModel.addAttribute("customers", theCustomer);

        return "customer/list-customer";
    }
    @GetMapping("/viewList")
    public String listCustomerBook(@RequestParam("customerId") int id,Model theModel) {

        // get Book from db
        Customer theCustomer = customerService.findById(id);

        // add to the spring model
        theModel.addAttribute("customer", theCustomer);

        return "book/bookList";
    }


    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") Book theBook, BindingResult bindingResult, Model theModel){

        if (bindingResult.hasErrors()) {
            return "book/add-book";
        }

    theModel.addAttribute("book",theBook);

            bookService.save(theBook);
            return  "redirect:/admin/list";


    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Book theBook = new Book();

        theModel.addAttribute("book", theBook);

        return "book/add-book";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId,
                                    Model theModel) {

        // get the book from the service
        Book theBook = bookService.findById(theId);
        theModel.addAttribute("book", theBook);

        // send over to our form
        return "book/add-book";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookId") int id){
        bookService.deleteById(id);
        return "redirect:/admin/list";
    }


    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("customerId") int id){
        customerService.deleteById(id);
        return "redirect:/admin/customer-list";
    }

    @PostMapping("/searchCustomer")
    public String searchCustomer(@RequestParam("firstName") String theFirstName,
                                 @RequestParam("lastName") String theLastName,
                                 Model theModel) {

        // check names, if both are empty then just give list of all employees
       // System.out.println(theFirstName);
        if (theFirstName.trim().isEmpty() || theLastName.trim().isEmpty()) {
          //  System.out.println("form error");
            return "redirect:/admin/customer-list";
        }
        else {
            // else, search by first name and last name
            List<Customer> theCustomer =
                    customerService.searchBy(theFirstName, theLastName);

            // add to the spring model
            theModel.addAttribute("customers", theCustomer);

            // send to list-employees
            return "customer/list-customer";
        }

    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

}
