package com.circle_vibe_core.app.user.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class UserAvatar {
    @Size(max = 200)
    private String url;

    @Size(max = 200)
    private String optimizedUrl;
}
