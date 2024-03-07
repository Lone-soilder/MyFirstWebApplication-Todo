package com.in28minutes.springboot.myFirstWebApp.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    //LDAP or Database
    //in memory

    //we will create a user details object and we will build a user details manager class and return it back.
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        //below is the user datails object made by User builder

        String username = "in28minutes";
        String password = "dummy";

        UserDetails userDetails = createNewUser(username, password);
        return new InMemoryUserDetailsManager(userDetails);
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String , String>  passwordEncoder =
                input -> passwordEncoder().encode(input);

        UserDetails userDetails=User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
          auth -> auth.anyRequest().authenticated()
        );
        http.formLogin(Customizer.withDefaults());

        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}

