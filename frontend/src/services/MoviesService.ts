import axios from "axios";

export default class MoviesService {
    // readonly contentType: {"Content-Type": string}
    //
    // MoviesService() {
    //     this.contentType = { "Content-Type": "application/json" }
    // }

    public async getPopularMovies() {
        let movies

        await axios.get(import.meta.env.VITE_BACKEND_BASE_URL + import.meta.env.VITE_BEST_MOVIES_PATH)
            .then((response) => {
                movies = response.data;
            })
            .catch((error) => {
                console.log(error)
            })

        return movies
    }
}