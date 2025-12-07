# Tourcoing Movie Data Base

```bash
# Option 1
# a. run postgresql container (then activate the datasource)
docker run -it -d -p 5432:5432 --name tmdb-bdd -e POSTGRES_PASSWORD=some_password postgres:17-alpine
# b. then run backend (with maven or IntelliJ)
mvn spring-boot:run
# c. and frontend
npm run dev

# Option 2
# run all containers (bdd, backend, frontend)
docker compose --env-file ~/IdeaProjects/tmdb/backend/.env up --build --force-recreate
```

### back

- [TMDB](https://developer.themoviedb.org/docs/getting-started)
    - [rated movies](https://developer.themoviedb.org/reference/guest-session-rated-movies)
    - [get a movie img](https://developer.themoviedb.org/reference/movie-images)
    - [image basics](https://developer.themoviedb.org/docs/image-basics)
    - [movies genre](https://developer.themoviedb.org/reference/genre-movie-list)
    - [popular movies](https://developer.themoviedb.org/reference/movie-popular-list)
- [about cors](https://medium.com/@sallu-salman/cross-origin-resource-sharing-cors-in-spring-boot-applications-116163a88adc)

### front

- [vue](https://vuejs.org/)
- [vuetify](https://vuetifyjs.com/en/)
- [material design icons](https://pictogrammers.com/library/mdi/)
    - <https://vuetifyjs.com/en/features/icon-fonts/#mdi-icon-search>
- ui
    - [flaticon](https://www.flaticon.com/fr/)
    - [servicestack](https://docs.servicestack.net/vue/)
