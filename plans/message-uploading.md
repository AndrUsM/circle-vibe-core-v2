Exactly — for **multiple large files in one message**, I would make the upload process independent for each file, then attach all completed files to one message.

### Best flow

```text
Client
  │
  │ Create message draft
  ▼
Message #500
status = DRAFT
  │
  ├── Upload file A
  │      ├── chunk 1
  │      ├── chunk 2
  │      └── chunk N
  │
  ├── Upload file B
  │      ├── chunk 1
  │      ├── chunk 2
  │      └── chunk N
  │
  └── Upload file C
         ├── chunk 1
         ├── chunk 2
         └── chunk N

              ↓

        all uploads complete

              ↓

       Message #500 = SENT
```

The important concept is:

> **One message → many upload sessions → many files → many attachments.**

### Database model

```text
Message
────────────
id
chat_id
sender_id
text
status
created_at
```

```text
File
────────────
id
storage_key
file_name
size
mime_type
status
```

```text
MessageAttachment
─────────────────
id
message_id
file_id
sort_order
```

So:

```text
Message #500
    │
    ├── Attachment → File #101 → 2 GB
    ├── Attachment → File #102 → 800 MB
    └── Attachment → File #103 → 5 GB
```

### API design

I'd use something like:

```text
POST /messages/drafts
```

Response:

```json
{
  "messageId": 500
}
```

Then for each file:

```text
POST /uploads
```

```json
{
  "messageId": 500,
  "fileName": "video.mp4",
  "size": 5368709120
}
```

Response:

```json
{
  "uploadId": "abc123"
}
```

Then chunks:

```text
PUT /uploads/abc123/chunks/0
PUT /uploads/abc123/chunks/1
PUT /uploads/abc123/chunks/2
...
```

You can upload **different files concurrently**:

```text
                 ┌── File A ── chunks ──┐
Message #500 ────┼── File B ── chunks ──┼──► Storage
                 └── File C ── chunks ──┘
```

When each file finishes:

```text
POST /uploads/{uploadId}/complete
```

Then when the user presses **Send**:

```text
POST /messages/500/send
```

The server checks:

```text
Are all files uploaded?
       │
    ┌──┴──┐
   YES     NO
    │       │
   SENT    reject / keep draft
```

### Why I prefer this over `multipart/form-data`

Don't do:

```text
POST /messages
  text
  file1 = 5 GB
  file2 = 2 GB
  file3 = 8 GB
```

Instead:

```text
Create message
       ↓
Upload file 1 ──┐
Upload file 2 ──┼── independently/resumably
Upload file 3 ──┘
       ↓
Complete message
```

That gives you **resume**, **parallel uploads**, **per-file progress**, and the ability to retry only the failed file/chunk.

For your Spring project, I'd also keep `Message`, `File`, and `MessageAttachment` as **separate entities**, rather than putting file information directly inside `Message`.

