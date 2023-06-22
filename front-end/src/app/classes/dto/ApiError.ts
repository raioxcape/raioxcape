import { ApiSubError } from './ApiSubError';

export interface ApiError {
  status: string;
  message: string;
  debugMessage: string;
  subErrors: ApiSubError[];
  timestamp: Date;
};
