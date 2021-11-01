package springproject.bookstore.OnlineBookStore.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import springproject.bookstore.OnlineBookStore.entity.Customer;


@Controller
public class HomeController {


    @InitBinder
    public void initBinder(WebDataBinder dataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    // create a mapping for home page

    @GetMapping("/")
    public String showHome() {

        return "home";
    }

    @GetMapping("/login")
    public String showMyLoginPage() {

        return "loginform";

    }

    @GetMapping("/showRegistrationPage")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());

        return "register";
    }


    @GetMapping("/success-page")
    public String showMySuccessPage() {

        return "success-page";

    }

    // add request mapping for /access-denied

    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";

    }
}
