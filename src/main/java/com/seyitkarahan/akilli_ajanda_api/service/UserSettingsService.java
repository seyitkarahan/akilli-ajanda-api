package com.seyitkarahan.akilli_ajanda_api.service;

import com.seyitkarahan.akilli_ajanda_api.dto.request.UserSettingsRequest;
import com.seyitkarahan.akilli_ajanda_api.dto.response.UserSettingsResponse;
import com.seyitkarahan.akilli_ajanda_api.entity.User;
import com.seyitkarahan.akilli_ajanda_api.entity.UserSettings;
import com.seyitkarahan.akilli_ajanda_api.exception.UserAlreadyExistsException;
import com.seyitkarahan.akilli_ajanda_api.exception.UserNotFoundException;
import com.seyitkarahan.akilli_ajanda_api.exception.UserSettingsNotFoundException;
import com.seyitkarahan.akilli_ajanda_api.repository.UserRepository;
import com.seyitkarahan.akilli_ajanda_api.repository.UserSettingsRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserSettingsService {

    private final UserSettingsRepository userSettingsRepository;
    private final UserRepository userRepository;

    public UserSettingsService(UserSettingsRepository userSettingsRepository, UserRepository userRepository) {
        this.userSettingsRepository = userSettingsRepository;
        this.userRepository = userRepository;
    }

    public UserSettingsResponse getUserSettings() {
        User user = getCurrentUser();
        UserSettings settings = userSettingsRepository.findByUser(user)
                .orElseThrow(() -> new UserSettingsNotFoundException("User settings not found"));
        return mapToResponse(settings);
    }

    public UserSettingsResponse createSettings(UserSettingsRequest request) {
        User user = getCurrentUser();

        if (userSettingsRepository.findByUser(user).isPresent()) {
            throw new UserAlreadyExistsException("User settings already exist, use update instead");
        }

        UserSettings settings = UserSettings.builder()
                .user(user)
                .notificationPrefence(request.getNotificationPrefence())
                .timezone(request.getTimezone())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapToResponse(userSettingsRepository.save(settings));
    }

    public UserSettingsResponse updateSettings(UserSettingsRequest request) {
        User user = getCurrentUser();
        UserSettings settings = userSettingsRepository.findByUser(user)
                .orElseThrow(() -> new UserNotFoundException("User settings not found"));

        settings.setNotificationPrefence(request.getNotificationPrefence());
        settings.setTimezone(request.getTimezone());
        settings.setCreatedAt(java.time.LocalDateTime.now());
        settings.setUpdatedAt(java.time.LocalDateTime.now());

        return mapToResponse(userSettingsRepository.save(settings));
    }

    public void deleteSettings() {
        User user = getCurrentUser();
        UserSettings settings = userSettingsRepository.findByUser(user)
                .orElseThrow(() -> new UserSettingsNotFoundException("User settings not found"));
        userSettingsRepository.delete(settings);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Authenticated user not found"));
    }

    private UserSettingsResponse mapToResponse(UserSettings settings) {
        return UserSettingsResponse.builder()
                .id(settings.getId())
                .notificationPrefence(settings.getNotificationPrefence())
                .timezone(settings.getTimezone())
                .userId(settings.getUser().getId())
                .build();
    }
}
