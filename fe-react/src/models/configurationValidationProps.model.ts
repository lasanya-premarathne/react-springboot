import { ConfigurationError } from "./configurationError.model";
import { Configurations } from "./configurations.model";

export interface ConfigurationValidationProps {
    formData: Configurations;
    errors: ConfigurationError;
    setErrors: (errors: ConfigurationError) => void;
}