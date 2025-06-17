package com.multiple_databases;

import com.multiple_databases.entity.client.Client;
import com.multiple_databases.entity.customer.Customer;
import com.multiple_databases.repo.client.ClientRepo;
import com.multiple_databases.repo.customer.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class MultipleDatabasesApplication {
    private final ClientRepo clientRepo;
    private final CustomerRepo customerRepo;

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatabasesApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

            System.out.println("------------------------------------");
            val client = new Client("Vasia", "Vasev", "USA", "333-33-33");
            clientRepo.save(client);
            clientRepo.findAll().forEach(System.out::println);

            System.out.println("------------------------------------");

            val customer = new Customer("Petia", "Petev", "China", "555-55-55");
            customerRepo.save(customer);
            customerRepo.findAll().forEach(System.out::println);
            System.out.println("------------------------------------");
        };
    }
}
