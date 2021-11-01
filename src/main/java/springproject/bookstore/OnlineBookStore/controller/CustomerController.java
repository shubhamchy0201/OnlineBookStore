package springproject.bookstore.OnlineBookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springproject.bookstore.OnlineBookStore.entity.Book;
import springproject.bookstore.OnlineBookStore.entity.Customer;
import springproject.bookstore.OnlineBookStore.service.BookService;
import springproject.bookstore.OnlineBookStore.service.CustomerService;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @GetMapping("/home")
    public String showMyLoginPage(@RequestParam("customerId") int id,Model theModel) {
        Customer customer=customerService.findById(id);
        theModel.addAttribute(customer);

        return "customer-home";

    }

    @PostMapping("/save")
    public String saveCustomer(@Valid @ModelAttribute("customer")  Customer theCustomer,
            BindingResult bindingResult, Model theModel) {



        if(customerService.customerExists(theCustomer.getEmail()))
        {
           // System.out.println("Already present");
            bindingResult.addError(new FieldError("customer","email","Email address already exists"));
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(theCustomer.getPassword());
        theCustomer.setPassword(encodedPassword);
        theModel.addAttribute("customer",theCustomer);
            // save the employee
            customerService.save(theCustomer);

            // use a redirect to prevent duplicate submissions
            return "redirect:/success-page";

    }
    @PostMapping("/saveUpdate")
    public String saveCustomerUpdate(@Valid @ModelAttribute("customer")  Customer theCustomer,BindingResult bindingResult,
                               Model theModel) {

        if (bindingResult.hasErrors()) {
            return "customer/customerUpdateForm";
        }

        theModel.addAttribute("customer",theCustomer);
        // save the employee
        customerService.save(theCustomer);

        // use a redirect to prevent duplicate submissions
        return "customer/success-update";

    }

    @GetMapping("/listBook")
    public String listBook(@RequestParam("customerId") int id,Model theModel) {

        // get Book from db
        List<Book> theBooks = bookService.findAll();
        Customer theCustomer=customerService.findById(id);
        List<Book> bookInCustomer=theCustomer.getBooks();
        List<Book> availableBooks=new ArrayList<>(theBooks);
        for(Book book:bookInCustomer)
        {
           // System.out.println((theBooks.contains(book)));
            if ((theBooks.contains(book)))
            {
             //   System.out.println("in if statement");
                availableBooks.remove(book);
            }

           // System.out.println("In loop statement");
        }
        // add to the spring model
        theModel.addAttribute("books", availableBooks);
        theModel.addAttribute("customer", theCustomer);


        return "customer/list-books";
    }


    @GetMapping("/showFormForUpdateCustomer")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model theModel){
        Customer customer = customerService.findById(id);
        theModel.addAttribute("customer",customer);
        return "customer/customerUpdateForm";
    }
    @GetMapping("/viewBook")
    public String showBook(@RequestParam("customerId") int cid,@RequestParam("bookId") int id, Model theModel){
        Book book = bookService.findById(id);
        Customer customer=customerService.findById(cid);
        theModel.addAttribute("book",book);
        theModel.addAttribute("customer",customer);
        return "customer/viewBook";
    }


    @GetMapping("/success-update")
    public String showMySuccessPage(@RequestParam("customerId") int id, Model theModel) {
        Customer customer = customerService.findById(id);
        theModel.addAttribute("customer",customer);
        return "customer/success-update";

    }

    @GetMapping("/deleteFromList")
    public String deleteBook(@RequestParam("customerId") int cid,@RequestParam("bookId") int id){
        Book book = bookService.findById(id);
        Customer customer=customerService.findById(cid);

       customer.removeBook(book);
       customerService.save(customer);


        return "redirect:/customer/myBooks?customerId="+cid;
    }

    @GetMapping("/addBook")
    public String addToList(@RequestParam("customerId") int cid,@RequestParam("bookId") int id, Model theModel){
        Book book = bookService.findById(id);
        Customer customer=customerService.findById(cid);

        book.addCustomer(customer);
        bookService.save(book);

        theModel.addAttribute("book",book);
        theModel.addAttribute("customer",customer);
        return "customer/mybooks";
    }

    @GetMapping("/myBooks")
    public String myBooks(@RequestParam("customerId") int cid,Model theModel){
        Customer customer=customerService.findById(cid);

        theModel.addAttribute("customer",customer);
        return "customer/mybooks";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

}
