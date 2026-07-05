package com.circle_vibe_core.app.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class UserContacts {
    @NotBlank
    @Column(unique = true)
    String phone;

    @NotBlank
    @Email
    @Column(unique = true)
    String email;
}
