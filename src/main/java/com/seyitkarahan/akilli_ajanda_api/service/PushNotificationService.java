package com.seyitkarahan.akilli_ajanda_api.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.seyitkarahan.akilli_ajanda_api.entity.User;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {

    /**
     * Belirtilen kullanıcıya anlık bildirim gönderir.
     * @param user Bildirimi alacak kullanıcı.
     * @param title Bildirimin başlığı.
     * @param body Bildirimin içeriği.
     * @throws FirebaseMessagingException Bildirim gönderilirken bir hata oluşursa.
     */
    public void sendNotificationToUser(User user, String title, String body) throws FirebaseMessagingException {
        // Kullanıcının cihaz token'ı veritabanında kayıtlı olmalıdır.
        String deviceToken = user.getDeviceToken();

        if (deviceToken == null || deviceToken.isEmpty()) {
            System.err.println("Kullanıcı ID " + user.getId() + " için cihaz token'ı bulunamadı. Bildirim gönderilemiyor.");
            return;
        }

        // Firebase Mesajını oluştur
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .setToken(deviceToken) // Bu token'a sahip cihaza gönder
                .build();

        // Mesajı gönder
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Başarıyla mesaj gönderildi: " + response);
    }
}