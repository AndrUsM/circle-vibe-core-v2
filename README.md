Java: 26

Run configuration:
    ```{
      "type": "java",
      "name": "Launch API",
      "request": "launch",
      "mainClass": "com.circle_vibe_core.app.AppApplication",
      "vmArgs": "-Duser.timezone=Europe/Kyiv",
      "env": {
        "DATABASE_URL": "jdbc:postgresql://localhost:8000/circle_vibe_core_v2",
        "DATABASE_USER": "circle-vibe-admin",
        "DATABASE_PASSWORD": "password"
      }
    }```


DB Connection string: postgresql://circle-vibe-admin:password@localhost:8000/circle_vibe_core_v2

Swagger: http://localhost:9000/api/swagger-ui/index.html

src/main/java/com/circlevibecore
├── shared
│   ├── config
│   ├── exception
│   ├── security
│   └── util
│
├── user
│   ├── controller
│   │   └── UserController.java
│   ├── service
│   │   ├── UserService.java
│   │   └── UserServiceImpl.java
│   ├── repository
│   │   └── UserRepository.java
│   ├── entity
│   │   └── User.java
│   ├── dto
│   │   ├── CreateUserRequest.java
│   │   ├── UpdateUserRequest.java
│   │   └── UserResponse.java
│   ├── mapper
│   │   └── UserMapper.java
│   └── exception
│       └── UserNotFoundException.java
│
├── room
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── mapper
│   └── exception
│
└── CircleVibeCoreApplication.java




## Relations:

Controller
    ↓
Service
    ↓
Repository
    ↓
Database

Controller
    ↕
DTO

Service
    ↕
Mapper
    ↕
DTO
    ↕


Feature-Based Package
user
├── controller      ← Presentation layer
├── service         ← Business layer
├── repository      ← Data access layer
├── entity          ← Persistence model
├── dto             ← Data transfer objects
└── mapper          ← Transformation layer