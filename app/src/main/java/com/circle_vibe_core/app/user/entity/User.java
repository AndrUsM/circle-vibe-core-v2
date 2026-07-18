package com.circle_vibe_core.app.user.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true)
    private String passwordHash;

    @NotBlank
    private String name;
    
    @Nullable
    private LocalDate birthDate;

    private List<Long> blockedUserIds = new ArrayList<>();

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "privacy_settings_id")
    private UserPrivacySettings privacySettings;
    
    @NotNull
    @Embedded
    @Valid
    private UserLocation location;

    @NotNull
    @Embedded
    @Valid 
    private UserContacts contacts;

    @NotNull
    @Embedded
    @Valid
    UserTokens tokens;
    
    @Embedded
    @Valid
    private UserAvatar avatar;

	@Embedded
	@Valid
	private UserRoles userRoles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "last_login_location_id")
    private UserSessionMeta lastLoginLocation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_statuses_id")
    private UserStatuses userStatuses;
}
