import AxiosService from "./axios.service";
import BackendEndpoints from "../constants/BackendEndPoints";
import { AddConfigurationResponse, Configurations } from "../models/configurations.model";

export const addSysConfig = async (config: Configurations): Promise<AddConfigurationResponse> => {
    try {
        const apiResponse = await AxiosService.post<any>(
            BackendEndpoints.POST_SYSTEM_CONFIG,
            config
        );
        alert(apiResponse?.data?.message);
        return apiResponse.data;
    } catch (error) {
        alert("Error: " + error);
        console.error("Error updating config:", error);
        throw error;
    }
};
