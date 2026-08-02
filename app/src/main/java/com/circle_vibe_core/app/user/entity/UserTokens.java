package com.circle_vibe_core.app.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class UserTokens {
    @Column(unique = true, name = "private_key")
    String privateKey;

    @Column(unique = true, name = "private_token")
    String privateToken;
}
