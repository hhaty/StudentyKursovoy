package com.example.studentsgroups.Configuration;


import com.example.studentsgroups.DataModels.UserModel;
import com.example.studentsgroups.DataRepositories.UserModelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public UserDetailsService userDetailsService(UserModelRepository userRepo) {
        return username -> {
            UserModel user = userRepo.findByUsername(username);
            if(user != null) return user;
            throw new UsernameNotFoundException("User not found!");
        };
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/Speciality/**", "/Qualification/**", "/FormEducation/**", "/Group/**").authenticated()
                .requestMatchers("/Login", "/Registration").anonymous()
                .requestMatchers("/", "/css/**", "/img/**").permitAll())
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/Login"))
                .build();
    }
}
