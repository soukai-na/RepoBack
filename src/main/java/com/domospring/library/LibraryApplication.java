package com.domospring.library;


import com.domospring.library.dao.UserRepository;
import com.domospring.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class LibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }
}



@Component
class DemoCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Done :)");

        User admin=new User();
        admin.setNom_user("mouhamad");
        admin.setPrenom_user("mouhamad");
        admin.setUsername("admin");
        admin.setPassword("password");

        userRepository.save(admin);
    }
}










