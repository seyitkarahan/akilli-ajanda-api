package com.seyitkarahan.akilli_ajanda_api.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {

    private LocalDateTime notifyAt;
    private Long taskId;
}
