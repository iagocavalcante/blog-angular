# Blog Angular

A modern blog CMS built with Angular 19 and Spring Boot.

## Tech Stack

- **Frontend:** Angular 19 (standalone components)
- **Backend:** Spring Boot 3.2 (Java 21)
- **Database:** H2 (in-memory)
- **Container:** Docker Compose

## Quick Start

### Using Docker Compose (Recommended)

```bash
# Clone the repository
git clone https://github.com/iagocavalcante/blog-angular.git
cd blog-angular

# Build and run everything
docker-compose up --build
```

### Access

- **Frontend:** http://localhost:4200
- **Backend API:** http://localhost:8080/api
- **H2 Console:** http://localhost:8080/h2-console

### Development

#### Frontend

```bash
cd blog-cms-angular
npm install
npm start
```

#### Backend

```bash
cd blog-cms
./mvnw spring-boot:run
```

## Project Structure

```
blog-angular/
├── blog-cms/              # Spring Boot backend
│   ├── src/main/java/    # Java source code
│   │   └── com/blog/    # Package
│   │       ├── controller/  # REST controllers
│   │       ├── entity/      # JPA entities
│   │       └── repository/  # Data repositories
│   └── src/main/resources/
│       └── application.properties
├── blog-cms-angular/     # Angular 19 frontend
│   └── src/app/
│       └── entities/     # Angular components & services
├── docker-compose.yml    # Docker orchestration
└── README.md
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/posts | List all posts |
| GET | /api/posts/:id | Get post by ID |
| POST | /api/posts | Create post |
| PUT | /api/posts/:id | Update post |
| DELETE | /api/posts/:id | Delete post |

## Entities

- **Post** - Blog posts with content, tags, and status
- **Comentario** - Comments linked to posts
- **Info** - Blog information/about page

## Migrated

- Upgraded from JHipster 6.7 (Angular 9) to Angular 19 + Spring Boot 3.2
- Migrated from NgModules to standalone components
- Modernized HTTP services with signals
- Added Docker Compose orchestration

## License

ISC
