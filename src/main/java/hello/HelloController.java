package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import hello.Customer;
import hello.CustomerRepository;

@RestController
public class HelloController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/customers")
    public String customers() {
        StringBuilder output = new StringBuilder();

        output.append("<table><tr><th>Firstname</th><th>Lastname</th></tr>");

        for (Customer c : customerRepository.findAll()) {
            output.append("<tr><td>"+c.getFirstName()+"</td><td>"+c.getLastName()+"</td></tr>");
        }

        output.append("</table>");

        return output.toString();
    }

}
