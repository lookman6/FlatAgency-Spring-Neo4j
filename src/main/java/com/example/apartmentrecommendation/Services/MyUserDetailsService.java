package com.example.apartmentrecommendation.Services;

import com.example.apartmentrecommendation.Beans.Person;
import com.example.apartmentrecommendation.Beans.UserMain;
import com.example.apartmentrecommendation.DAO.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Person user = userRepository.findByUserName(username);
        if(user==null) {
            throw new UsernameNotFoundException(username + " not found!");
        }

        return new UserMain(user);
    }
}
