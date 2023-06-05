package com.gideon.spring_security_1.services;

import com.gideon.spring_security_1.entity.User;
import com.gideon.spring_security_1.repositories.UserRepository;
import com.gideon.spring_security_1.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findUserByUsername(username);
            return user
                    .map(SecurityUser::new)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
