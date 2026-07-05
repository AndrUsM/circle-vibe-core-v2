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
public class UserTokens {
    @NotBlank
    @Column(unique = true, name = "private_key")
    String privateKey;

    @NotBlank
    @Email
    @Column(unique = true, name = "private_token")
    String privateToken;
}
