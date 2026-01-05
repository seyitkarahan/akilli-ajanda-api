package com.seyitkarahan.akilli_ajanda_api.service;

import com.seyitkarahan.akilli_ajanda_api.entity.EventNotification;
import com.seyitkarahan.akilli_ajanda_api.repository.EventNotificationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationSchedulerService {

    private final EventNotificationRepository eventNotificationRepository;
    private final PushNotificationService pushNotificationService; // YENİ: Servisi enjekte et

    public NotificationSchedulerService(EventNotificationRepository eventNotificationRepository,
                                        PushNotificationService pushNotificationService) {
        this.eventNotificationRepository = eventNotificationRepository;
        this.pushNotificationService = pushNotificationService;
    }

    @Scheduled(fixedRate = 60000)
    public void processAndSendNotifications() {
        System.out.println("Zamanlanmış görev çalıştı: Bildirimler kontrol ediliyor...");

        List<EventNotification> notificationsToSend =
                eventNotificationRepository.findByIsSentFalseAndNotifyAtBefore(LocalDateTime.now());

        if (notificationsToSend.isEmpty()) {
            return;
        }

        System.out.println(notificationsToSend.size() + " adet bildirim gönderilecek.");

        for (EventNotification notification : notificationsToSend) {
            try {
                // 1. Anlık bildirimi gönder (Yorum satırı kaldırıldı ve gerçek kod eklendi)
                pushNotificationService.sendNotificationToUser(
                        notification.getUser(),
                        "Etkinlik Hatırlatma",
                        notification.getEvent().getTitle() // Bildirim içeriği olarak etkinlik başlığını gönder
                );

                // 2. Bildirimin durumunu güncelle
                notification.setSent(true);
                eventNotificationRepository.save(notification);

                System.out.println("Bildirim ID: " + notification.getId() + " gönderildi ve durum güncellendi.");

            } catch (Exception e) {
                System.err.println("Bildirim ID: " + notification.getId() + " gönderilirken hata oluştu: " + e.getMessage());
            }
        }
    }
}