package com.circle_vibe_core.app.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class UserLocation {
    @NotBlank
    @Pattern(regexp = "^[A-Z]{2,3}$")
    @Column(name = "country_code")
    private String countryCode;

    @NotBlank
    @Size(max  = 160)
    private String city;
}
