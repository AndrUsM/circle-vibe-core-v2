package com.circle_vibe_core.app.config;

public final class EncryptionConstants {
	private EncryptionConstants() {
	}

	public static final String ALGORITHM = "AES/GCM/NoPadding";

	public static final int IV_LENGTH = 12;
	public static final int TAG_LENGTH = 128;
	public static final int KEY_LENGTH = 32;
}
