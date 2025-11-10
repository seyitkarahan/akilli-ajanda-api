package com.seyitkarahan.akilli_ajanda_api.service;

import com.seyitkarahan.akilli_ajanda_api.dto.request.NotificationRequest;
import com.seyitkarahan.akilli_ajanda_api.dto.response.NotificationResponse;
import com.seyitkarahan.akilli_ajanda_api.entity.Notification;
import com.seyitkarahan.akilli_ajanda_api.entity.Task;
import com.seyitkarahan.akilli_ajanda_api.entity.User;
import com.seyitkarahan.akilli_ajanda_api.exception.NotificationNotFoundException;
import com.seyitkarahan.akilli_ajanda_api.exception.TaskNotFoundException;
import com.seyitkarahan.akilli_ajanda_api.exception.UnauthorizedActionException;
import com.seyitkarahan.akilli_ajanda_api.exception.UserNotFoundException;
import com.seyitkarahan.akilli_ajanda_api.repository.NotificationRepository;
import com.seyitkarahan.akilli_ajanda_api.repository.TaskRepository;
import com.seyitkarahan.akilli_ajanda_api.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository,
                               TaskRepository taskRepository,
                               UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<NotificationResponse> getAllNotifications() {
        User user = getCurrentUser();
        return notificationRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public NotificationResponse getNotificationById(Long id) {
        User user = getCurrentUser();
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found"));
        if (!notification.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedActionException("You are not allowed to access this notification");
        }
        return mapToResponse(notification);
    }

    public NotificationResponse createNotification(NotificationRequest request) {
        User user = getCurrentUser();
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedActionException("You cannot create a notification for this task");
        }

        Notification notification = Notification.builder()
                .notifyAt(request.getNotifyAt())
                .task(task)
                .user(user)
                .isSent(false)
                .build();

        return mapToResponse(notificationRepository.save(notification));
    }

    public NotificationResponse updateNotification(Long id, NotificationRequest request) {
        User user = getCurrentUser();
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found"));

        if (!notification.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedActionException("You are not allowed to update this notification");
        }

        notification.setNotifyAt(request.getNotifyAt());
        if (request.getTaskId() != null) {
            Task task = taskRepository.findById(request.getTaskId())
                    .orElseThrow(() -> new TaskNotFoundException("Task not found"));
            if (!task.getUser().getId().equals(user.getId())) {
                throw new UnauthorizedActionException("You cannot assign this task");
            }
            notification.setTask(task);
        }

        return mapToResponse(notificationRepository.save(notification));
    }

    public void deleteNotification(Long id) {
        User user = getCurrentUser();
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found"));

        if (!notification.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedActionException("You are not allowed to delete this notification");
        }

        notificationRepository.delete(notification);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Authenticated user not found"));
    }

    private NotificationResponse mapToResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .notifyAt(notification.getNotifyAt())
                .isSent(notification.isSent())
                .taskId(notification.getTask().getId())
                .build();
    }
}
