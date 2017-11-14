package hello;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);

            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

            CustomerRepository repository = (CustomerRepository) ctx.getBean("customerRepository");
            repository.save(new Customer("Adriano", "Guttadauria"));
            repository.save(new Customer("Bart", "Simpson"));
            repository.save(new Customer("Jon", "Snow"));
            repository.save(new Customer("Peter", "Parker"));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Snow'):");
            log.info("--------------------------------------------");
            for (Customer jon : repository.findByLastName("Snow")) {
                    log.info(jon.toString());
            }
            log.info("");
        };
    }
}
