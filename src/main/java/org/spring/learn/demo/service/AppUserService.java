package org.spring.learn.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.spring.learn.demo.dao.UserDao;
import org.spring.learn.demo.entity.AppUser;
import org.spring.learn.demo.entity.ConfirmationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {


    private final UserDao userDao;
    private final static String MESSAGE="email with user : %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
    
        return userDao.findByEmail(s).orElseThrow(()-> new UsernameNotFoundException(String.format(MESSAGE, s)));
    }

    public String signUp(AppUser appUser){
        
        Boolean isExists = userDao.findByEmail(appUser.getEmail())
                        .isPresent();
        if(isExists){
            throw new IllegalStateException("email already taken");
        }

        String passwordEncoded = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(passwordEncoded); 
        
        userDao.save(appUser);

        // confirmation token
        String token = UUID.randomUUID().toString();


        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //  send token email


        return token;

    }   

    public int enabledAppUser(String email){
        return userDao.enabledAppUser(email);
    }
    
}
