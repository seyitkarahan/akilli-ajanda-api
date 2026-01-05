package com.seyitkarahan.akilli_ajanda_api.service;

import com.seyitkarahan.akilli_ajanda_api.entity.User;
import com.seyitkarahan.akilli_ajanda_api.exception.UserNotFoundException;
import com.seyitkarahan.akilli_ajanda_api.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * O anki giriş yapmış kullanıcının cihaz token'ını günceller.
     * @param token Flutter uygulamasından gelen yeni cihaz token'ı.
     */
    public void updateCurrentUserDeviceToken(String token) {
        // Güvenlik konteksinden o anki kullanıcının email'ini al
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Kullanıcıyı veritabanında bul
        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + email));

        // Yeni token'ı ata ve kaydet
        currentUser.setDeviceToken(token);
        userRepository.save(currentUser);
        System.out.println("Cihaz token güncellendi: " + currentUser.getEmail());
    }
}