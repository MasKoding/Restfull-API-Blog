package org.spring.learn.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.spring.learn.demo.dao.ConfirmationTokenDao;
import org.spring.learn.demo.entity.ConfirmationToken;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenDao confirmationTokenDao;
    
    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenDao.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenDao.findByToken(token);
    }

    public int setConfirmedAt(String token){
        return confirmationTokenDao.updateConfirmedAt(token, LocalDateTime.now());
    }
}
