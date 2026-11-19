package com.circle_vibe_core.app.realtime.enums;

public enum RealtimeEventType {
	// Chat
	MESSAGE_CREATED,
	MESSAGE_UPDATED,
	MESSAGE_DELETED,

	// Conversations
	CONVERSATION_CREATED,
	CONVERSATION_UPDATED,
	CONVERSATION_DELETED,

	// Users
	USER_ONLINE,
	USER_OFFLINE,

	// Notifications
	NOTIFICATION_CREATED,

	// General
	SYSTEM_EVENT
}
