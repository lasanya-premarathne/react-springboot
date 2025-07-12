import axios, { AxiosRequestConfig, AxiosResponse } from "axios";
// import store from "../redux-store";
// import { loadingSpinnerAction } from "../redux-store/loading-spinner-slice";

const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_BACKEND_BASE_URL || "http://localhost:8080",
  timeout: 15000,
  headers: {
    "Content-Type": "application/json",
  },
});

// Add request interceptor to show loader
// axiosInstance.interceptors.request.use(
//     (config: AxiosRequestConfig) => {
//         store.dispatch(loadingSpinnerAction.startApiCall());
//         return config;
//     },
//     (error) => {
//         store.dispatch(loadingSpinnerAction.endApiCall());
//         return Promise.reject(error);
//     }
// );

// Add response interceptor to hide loader
// axiosInstance.interceptors.response.use(
//     (response: AxiosResponse) => {
//         store.dispatch(loadingSpinnerAction.endApiCall());
//         return response;
//     },
//     (error) => {
//         store.dispatch(loadingSpinnerAction.endApiCall());
//         return Promise.reject(error);
//     }
// );

const AxiosService = {
    get<T>(url: string, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
        return axiosInstance.get<T>(url, config);
    },

    post<T>(url: string, data?: any, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
        return axiosInstance.post<T>(url, data, config);
    },

    put<T>(url: string, data?: any, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
        return axiosInstance.put<T>(url, data, config);
    },

    delete<T>(url: string, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
        return axiosInstance.delete<T>(url, config);
    },
};

export default AxiosService;
