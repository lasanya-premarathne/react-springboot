export interface Configurations{
    eventId: number;
    eventName: string | undefined;
    totalTickets: number | undefined;
    ticketReleaseRate: number | undefined;
    customerRetrievalRate: number | undefined;
    maxTicketCapacity: number | undefined;
}

export interface ConfigurationValidationProps {
    formData: Configurations;
    errors: ConfigurationError;
    setErrors: (errors: ConfigurationError) => void;
}

export interface ConfigurationError {
    eventName: string;
    totalTickets: string;
    ticketReleaseRate: string;
    customerRetrievalRate: string;
    maxTicketCapacity: string;
}

export interface AddConfigurationResponse {
    data: ConfigurationError,
    message: string;
    timestamp: string;
    success: boolean;
}