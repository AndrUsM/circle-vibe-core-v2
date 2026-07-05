package com.circle_vibe_core.app.user.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_privacy_settings")
public class UserPrivacySettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault(value = "true")
    @Column(name = "is_hidden_contact_info")
    private Boolean isHiddenContactInfo;

    @ColumnDefault(value = "false")
    @Column(name = "is_allowed_to_search")
    private Boolean isAllowedToSearch;
}
