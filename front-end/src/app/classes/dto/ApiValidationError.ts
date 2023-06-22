import { ApiSubError } from './ApiSubError';

export interface ApiValidationError extends ApiSubError {
  object: string;
  field?: string;
  rejectedValue?: any;
  message: string;
};
