package com.spring.springSecurity;

import com.spring.springSecurity.model.Role;
import com.spring.springSecurity.model.UserDetails;
import com.spring.springSecurity.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new UserDetails(null, "krishna", "krish", "1233", new ArrayList<>()));
            userService.saveUser(new UserDetails(null, "ravi", "ravi123", "1233", new ArrayList<>()));
            userService.saveUser(new UserDetails(null, "gopi", "gopi", "1233", new ArrayList<>()));
            userService.saveUser(new UserDetails(null, "mahesh", "mahesh", "1233", new ArrayList<>()));
            userService.addRoleToUser("krish", "ROLE_ADMIN");
            userService.addRoleToUser("gopi", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("ravi123", "ROLE_USER");
            userService.addRoleToUser("mahesh", "ROLE_MANAGER");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
