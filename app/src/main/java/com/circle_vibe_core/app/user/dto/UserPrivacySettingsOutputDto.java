package com.circle_vibe_core.app.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPrivacySettingsOutputDto {
	private Long id;
	private Boolean isHiddenContactInfo;
	private Boolean isAllowedToSearch;
}
