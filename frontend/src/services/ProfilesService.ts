import axios from "axios";

export default class ProfilesService {
    private readonly CONTENT_TYPE_APPLICATION_JSON = { "Content-Type": "application/json" };

    public async getProfiles() {
        let profiles

        await axios.get(import.meta.env.VITE_BACKEND_BASE_URL + import.meta.env.VITE_PROFILES_PATH)
            .then((response) => {
                profiles = response.data;
            })
            .catch((error) => {
                console.log(error)
            })

        return profiles
    }

    public async addProfile(toSubmit){
        let status: string = 'OK' // to add feedback to user

        await axios.post(import.meta.env.VITE_BACKEND_BASE_URL + import.meta.env.VITE_PROFILES_PATH,
            toSubmit,
            {
                headers: this.CONTENT_TYPE_APPLICATION_JSON,
            })
            .then((response) => console.log(response))
            .catch((error) => {
                status = "KO"
                console.log(error)
            })

        return status;
    }
}