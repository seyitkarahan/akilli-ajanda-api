package com.seyitkarahan.akilli_ajanda_api.controller;

import com.seyitkarahan.akilli_ajanda_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users") // Kullanıcı işlemleri için genel adres
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Göndermek istediğiniz kodun doğru yerleştirilmiş hali
    @PostMapping("/update-device-token")
    public ResponseEntity<Void> updateDeviceToken(@RequestBody String token) {
        // Servisteki ilgili metodu çağır
        userService.updateCurrentUserDeviceToken(token);
        return ResponseEntity.ok().build();
    }
}