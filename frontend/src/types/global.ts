export interface IMovie {
  id?: number | string;
  title: string;
  coverImgUrl?: string;
  releaseDate?: string;
  adult?: boolean;
  overview?: string;
  _uniqueId?: string;
}

export interface IProfile {
  id: number;
  name: string;
  adult: boolean;
  genreList: EMovieGenre;
}

export enum EMovieGenre {
  DEFAULT = "all",
  ACTION = "action",
  ABENTEUR = "abenteur",
  ANIMATION = "animation",
  KOMODIE = "komodie",
  KRIMI = "krimi",
  DOCUMENTARFILM = "documentarfilm",
  DRAMA = "drama",
  FAMILIE = "familie",
  FANTASY = "fantasy",
  HISTORIES = "histories",
  HORROR = "horror",
  MUSIKMYSTERY = "musikmystery",
  LIEBESFILM = "liebesfilm",
  SCIENCEFICTION = "science fiction",
  TVFILM = "tv film",
  THRILLER = "thriller",
  KRIEGSFILM = "kriegsfilm",
  WESTERN = "western",
}

export enum ERequestStatus {
  OK,
  KO,
}
