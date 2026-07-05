package com.circle_vibe_core.app.user.entity;

import com.circle_vibe_core.app.user.enums.AccoutStatus;
import com.circle_vibe_core.app.user.enums.UserChatStatus;
import com.circle_vibe_core.app.user.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_statuses")
public class UserStatuses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "chat_status")
    private UserChatStatus chatStatus = UserChatStatus.OFFLINE;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType status = UserType.PRIVATE;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccoutStatus accountStatus = AccoutStatus.NOT_ACTIVE;
}
