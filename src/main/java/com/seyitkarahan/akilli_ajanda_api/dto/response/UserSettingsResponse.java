package com.seyitkarahan.akilli_ajanda_api.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSettingsResponse {
    private Long id;
    private String notificationPrefence;
    private String timezone;
    private Long userId;
}
