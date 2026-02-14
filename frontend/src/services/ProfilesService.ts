import axios from "axios";
import type { IProfile } from "../types/global.ts";
import { ERequestStatus } from "../types/global.ts";
import type { TSubmitPayload } from "../components/pages/ProfilePage.vue";

export default class ProfilesService {
  private readonly CONTENT_TYPE_APPLICATION_JSON = {
    "Content-Type": "application/json",
  };

  public async getProfiles(): Promise<IProfile[]> {
    let profiles: IProfile[] = [];

    await axios
      .get(
        import.meta.env.VITE_BACKEND_BASE_URL +
          import.meta.env.VITE_PROFILES_PATH,
      )
      .then((response) => {
        profiles = response.data;
      })
      .catch((error) => {
        console.log(error);
      });

    return profiles;
  }

  public async addProfile(toSubmit: TSubmitPayload): Promise<ERequestStatus> {
    let status: ERequestStatus = ERequestStatus.KO;

    await axios
      .post(
        import.meta.env.VITE_BACKEND_BASE_URL +
          import.meta.env.VITE_PROFILES_PATH,
        toSubmit,
        {
          headers: this.CONTENT_TYPE_APPLICATION_JSON,
        },
      )
      .then((_response) => {
        status = ERequestStatus.OK;
      })
      .catch((_error) => {
        console.log(_error);
        status = ERequestStatus.KO;
      });

    return status;
  }
}
