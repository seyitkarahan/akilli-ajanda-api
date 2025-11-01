package com.seyitkarahan.akilli_ajanda_api.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSettingsRequest {
    private String notificationPrefence;
    private String timezone;
}
