package se.ifmo.ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.ifmo.ru.domain.dao.UserRepository;

import java.util.Collection;

import static java.util.Arrays.asList;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        se.ifmo.ru.domain.model.User person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return new User(username, passwordEncoder().encode(person.getPassword()), getGrantedAuthorities(username));
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
        Collection<? extends GrantedAuthority> authorities;
        if (username.equals("mariia")) {
            authorities = asList(() -> "ROLE_ADMIN", () -> "ROLE_OTHER", () -> "ROLE_BASIC");
        } else if (username.equals("username")) {
            authorities = asList(() -> "ROLE_OTHER");
        } else {
            authorities = asList(() -> "ROLE_BASIC");
        }
        return authorities;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
