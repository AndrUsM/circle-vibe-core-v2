
package com.circle_vibe_core.app.message.dto;

import java.util.List;

import com.circle_vibe_core.app.message.enums.MessageStatus;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateMessageInputDto {

    @Nullable
    public MessageStatus status;

	@NotNull 
	public String content;

    @Nullable
    @PositiveOrZero
    public Long uploadingProgress;

    @Nullable
    @Valid
    public MessageReferenceMetaInputDto referenceMeta;

    @Nullable
    @Valid
    public MessageVisibilityRulesInputDto visibilityRules;

    @NotNull
    public List<Long> fileIds;
}
