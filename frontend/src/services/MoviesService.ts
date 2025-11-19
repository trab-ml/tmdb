import axios from "axios"
import type {IMovie} from "../types/global.ts"

export default class MoviesService {
    public async getMovies(bestMovies: boolean): Promise<IMovie[]> {
        let movies: IMovie[] = []

        let url: string
        if (bestMovies) {
            url = import.meta.env.VITE_BACKEND_BASE_URL + import.meta.env.VITE_BEST_MOVIES_PATH
        } else {
            url = import.meta.env.VITE_BACKEND_BASE_URL + import.meta.env.VITE_TOP_RATED_MOVIES_PATH
        }

        await axios.get(url)
            .then((response) => {
                movies = response.data ?? []
            })
            .catch((error) => {
                console.log(error)
            })

        return movies
    }

    public async searchByProfile(profileId: string): Promise<IMovie[]> {
        let movies: IMovie[] = []

        await axios.get(import.meta.env.VITE_BACKEND_BASE_URL + import.meta.env.VITE_BEST_MOVIES_BY_PROFILE_PATH,
            {
                params: {
                    id: profileId
                }
            })
            .then((response) => {
                movies = response.data ?? [];
            })
            .catch((error) => {
                console.log(error)
            })

        return movies
    }
}