import { ApiError } from './ApiError';

export interface ApiResponse<T> {
  status: string;
  data: T;
  error: ApiError;
  timestamp: Date;
};
