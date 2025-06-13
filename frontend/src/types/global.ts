export enum MovieGenre {
    DEFAULT = "all", ACTION = "action", ABENTEUR = "abenteur", ANIMATION = "animation", KOMODIE = "komodie", KRIMI = "krimi",
    DOCUMENTARFILM = "documentarfilm", DRAMA = "drama", FAMILIE = "familie", FANTASY = "fantasy", HISTORIES = "histories",
    HORROR = "horror", MUSIKMYSTERY = "musikmystery", LIEBESFILM = "liebesfilm", SCIENCEFICTION = "science fiction", TVFILM = "tv film",
    THRILLER = "thriller", KRIEGSFILM = "kriegsfilm", WESTERN = "western"
}

export const getMovieGenres = () => {
    let genres = []
    Object.values(MovieGenre).map(val => genres.push({id: val, name: val.toUpperCase()}))
    return genres;
}

export const stringToWeatherType = (str: string) => {
    let g;

    switch (str) {
        case "all":
            g = MovieGenre.DEFAULT;
            break;
            // ...
        default:
            g = null;
    }

    return g;
};