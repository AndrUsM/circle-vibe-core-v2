package com.circle_vibe_core.app.user.dto;

import com.circle_vibe_core.app.user.enums.AccoutStatus;
import com.circle_vibe_core.app.user.enums.UserChatStatus;
import com.circle_vibe_core.app.user.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStatusesOutputDto {
    private long id;
    private UserChatStatus chatStatus;
    private UserType status;
    private AccoutStatus accountStatus = AccoutStatus.NOT_ACTIVE;
}
