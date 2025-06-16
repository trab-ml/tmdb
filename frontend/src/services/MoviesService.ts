import axios from "axios";

export default class MoviesService {
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

    public async searchByProfile(profileId) {
        let profiles

        await axios.get(import.meta.env.VITE_BACKEND_BASE_URL + import.meta.env.VITE_BEST_MOVIES_BY_PROFILE_PATH,
            {
                params: {
                    id: profileId
                }
            })
            .then((response) => {
                profiles = response.data;
            })
            .catch((error) => {
                console.log(error)
            })

        return profiles
    }
}