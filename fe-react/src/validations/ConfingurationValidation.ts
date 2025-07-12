import { ConfigurationValidationProps } from "../models/configurationValidationProps.model";
import { ConfigurationError } from "../models/configurationError.model";

const ConfigurationValidation = ({ formData, errors, setErrors }: ConfigurationValidationProps): boolean => {
    let isValid = true;
    let newErrors:ConfigurationError = { ...errors };

    // Clear previous errors
    newErrors.eventName = '';
    newErrors.totalTickets = '';
    newErrors.ticketReleaseRate = '';
    newErrors.customerRetrievalRate = '';
    newErrors.maxTicketCapacity = '';

    // Event Name validation
    if (!formData.eventName) {
        newErrors.eventName = 'Event name is required';
        isValid = false;
    }

    // Total Tickets validation
    const totalTickets = Number(formData.totalTickets);
    if (!formData.totalTickets) {
        newErrors.totalTickets = 'Total number of tickets is required';
        isValid = false;
    } else if (totalTickets <= 0) {
        newErrors.totalTickets = 'Total number of tickets must be a positive number';
        isValid = false;
    }

    // Ticket Release Rate validation
    const ticketReleaseRate = Number(formData.ticketReleaseRate);
    if (!formData.ticketReleaseRate) {
        newErrors.ticketReleaseRate = 'Ticket release rate is required';
        isValid = false;
    } else if (ticketReleaseRate <= 0) {
        newErrors.ticketReleaseRate = 'Ticket release rate must be a positive number';
        isValid = false;
    } else if (ticketReleaseRate > totalTickets) {
        newErrors.ticketReleaseRate = 'Ticket release rate must be less than total tickets';
        isValid = false;
    }

    // Customer Retrieval Rate validation
    const customerRetrievalRate = Number(formData.customerRetrievalRate);
    if (!formData.customerRetrievalRate) {
        newErrors.customerRetrievalRate = 'Customer retrieval rate is required';
        isValid = false;
    } else if (customerRetrievalRate <= 0) {
        newErrors.customerRetrievalRate = 'Customer retrieval rate must be a positive number';
        isValid = false;
    } else if (customerRetrievalRate > totalTickets) {
        newErrors.customerRetrievalRate = 'Customer retrieval rate must be less than total tickets';
        isValid = false;
    }

    // Max Ticket Capacity validation
    const maxTicketCapacity = Number(formData.maxTicketCapacity);
    if (!formData.maxTicketCapacity) {
        newErrors.maxTicketCapacity = 'Max ticket capacity is required';
        isValid = false;
    } else if (maxTicketCapacity <= 0) {
        newErrors.maxTicketCapacity = 'Max ticket capacity must be a positive number';
        isValid = false;
    } else if (maxTicketCapacity > totalTickets) {
        newErrors.maxTicketCapacity = 'Max ticket capacity must be less than total tickets';
        isValid = false;
    }

    setErrors(newErrors);
    return isValid;
};

export default ConfigurationValidation;