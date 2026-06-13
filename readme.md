# 🎬 Tourcoing Movie Database

Application web permettant de consulter et explorer les données de films via l'API TMDB.

## Tech Stack

| Backend     | Frontend         | Database   |
| ----------- | ---------------- | ---------- |
| Spring Boot | Vue.js + Vuetify | PostgreSQL |

---

## Getting Started

### Option 1 — Local Development

#### 1. Start PostgreSQL

```bash
docker run -d \
  --name tmdb-db \
  -p 5432:5432 \
  -e POSTGRES_PASSWORD=some_password \
  postgres:17-alpine
```

#### 2. Start Backend

```bash
mvn spring-boot:run
```

#### 3. Start Frontend

```bash
npm install
npm run dev
```

---

### Option 2 — Docker Compose

Start the full stack (database, backend, frontend):

```bash
docker compose \
  --env-file ~/IdeaProjects/tmdb/backend/.env \
  up --build
```

Stop and clean containers:

```bash
docker compose \
  --env-file ~/IdeaProjects/tmdb/backend/.env \
  down --volumes --rmi all --remove-orphans
```

---

## Security & Dependencies

Check dependency vulnerabilities:

```bash
mvn dependency-check:check
```

Inspect dependency tree:

```bash
mvn dependency:tree
```

---

### Backend

#### TMDB API

* [Getting Started](https://developer.themoviedb.org/docs/getting-started)
* [Popular Movies](https://developer.themoviedb.org/reference/movie-popular-list)
* [Movie Genres](https://developer.themoviedb.org/reference/genre-movie-list)
* [Movie Images](https://developer.themoviedb.org/reference/movie-images)
* [Image Basics](https://developer.themoviedb.org/docs/image-basics)
* [Rated Movies](https://developer.themoviedb.org/reference/guest-session-rated-movies)

#### Spring

* [CORS](https://medium.com/@sallu-salman/cross-origin-resource-sharing-cors-in-spring-boot-applications-116163a88adc)

### Frontend

* [Vue.js](https://vuejs.org/)
* [Vuetify](https://vuetifyjs.com/)
* Material Design Icons
  * [Pictogrammers](https://pictogrammers.com/library/mdi/)
  * [VuetifyJS](https://vuetifyjs.com/en/features/icon-fonts/#mdi-icon-search)

#### UI Inspiration

* [Flaticon](https://www.flaticon.com/)
* [Service Stack Documentation](https://docs.servicestack.net/vue/)

### DevOps & Docker

* [Slim Docker Images for Java](https://piotrminkowski.com/2023/11/07/slim-docker-images-for-java/)
* [Dockerized Spring Boot Optimization](https://medium.com/@bolot.89/10-proven-strategies-to-optimize-your-dockerized-spring-boot-application-f9420280d6c0)

### Security

* [OWASP Dependency Check](https://dependency-check.github.io/DependencyCheck/dependency-check-maven/index.html)

### Deployment

* [Neon](https://neon.com/)
* [Fly](https://fly.io/)
* [Vercel](https://vercel.com/)

```bash
sudo chmod +x setenv.sh
./setenv.sh
```
