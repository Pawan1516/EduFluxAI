import { User, Course, Resource, DSAProblem, LeaderboardUser, Payment } from '../types';

const API_BASE_URL = 'http://localhost:8080/api';

interface ApiResponse<T> {
  success: boolean;
  message: string;
  data: T;
}

interface LoginRequest {
  email: string;
  password: string;
}

interface RegisterRequest {
  name: string;
  email: string;
  password: string;
}

interface LoginResponse {
  token: string;
  user: User;
}

// Helper function to get auth headers
const getAuthHeaders = () => {
  const token = localStorage.getItem('authToken');
  return {
    'Content-Type': 'application/json',
    ...(token && { 'Authorization': `Bearer ${token}` })
  };
};

// Helper function to handle API responses
const handleApiResponse = async <T>(response: Response): Promise<T> => {
  const data: ApiResponse<T> = await response.json();
  if (!response.ok || !data.success) {
    throw new Error(data.message || 'API request failed');
  }
  return data.data;
};

// User and Auth Management
export const requestOtp = async (name: string, email: string, password: string): Promise<{ success: boolean, message: string }> => {
  try {
    const response = await fetch(`${API_BASE_URL}/users/register`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name, email, password })
    });
    
    await handleApiResponse(response);
    return { success: true, message: 'Registration successful. Please check your email for verification.' };
  } catch (error) {
    return { success: false, message: error instanceof Error ? error.message : 'Registration failed' };
  }
};

export const verifyOtpAndRegister = async (email: string, otp: string): Promise<{ success: boolean, message: string, user?: User }> => {
  // For now, we'll simulate OTP verification
  // In a real implementation, you'd have a separate endpoint for OTP verification
  try {
    const response = await fetch(`${API_BASE_URL}/users/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password: 'temp' }) // This would be handled differently in real implementation
    });
    
    const loginResponse: LoginResponse = await handleApiResponse(response);
    localStorage.setItem('authToken', loginResponse.token);
    return { success: true, message: 'Account verified successfully!', user: loginResponse.user };
  } catch (error) {
    return { success: false, message: error instanceof Error ? error.message : 'OTP verification failed' };
  }
};

export const loginUser = async (email: string, password: string): Promise<{ success: boolean, message: string, user?: User }> => {
  try {
    const response = await fetch(`${API_BASE_URL}/users/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    });
    
    const loginResponse: LoginResponse = await handleApiResponse(response);
    localStorage.setItem('authToken', loginResponse.token);
    return { success: true, message: 'Login successful!', user: loginResponse.user };
  } catch (error) {
    return { success: false, message: error instanceof Error ? error.message : 'Login failed' };
  }
};

export const getUserById = async (userId: string): Promise<User | undefined> => {
  try {
    const response = await fetch(`${API_BASE_URL}/users/${userId}`, {
      headers: getAuthHeaders()
    });
    
    return await handleApiResponse<User>(response);
  } catch (error) {
    console.error('Error fetching user:', error);
    return undefined;
  }
};

export const updateUser = async (updatedUser: User): Promise<{success: boolean, user?: User}> => {
  try {
    const response = await fetch(`${API_BASE_URL}/users/${updatedUser.id}`, {
      method: 'PUT',
      headers: getAuthHeaders(),
      body: JSON.stringify(updatedUser)
    });
    
    const user = await handleApiResponse<User>(response);
    return { success: true, user };
  } catch (error) {
    return { success: false };
  }
};

// Content Management
export const getCourses = async (filters: { searchTerm?: string, tag?: string, price?: 'all' | 'paid' | 'free' } = {}): Promise<Course[]> => {
  try {
    const params = new URLSearchParams();
    if (filters.searchTerm) params.append('searchTerm', filters.searchTerm);
    if (filters.tag) params.append('tag', filters.tag);
    if (filters.price) params.append('price', filters.price);
    
    const response = await fetch(`${API_BASE_URL}/courses?${params}`, {
      headers: getAuthHeaders()
    });
    
    return await handleApiResponse<Course[]>(response);
  } catch (error) {
    console.error('Error fetching courses:', error);
    return [];
  }
};

export const getCourseById = async (id: string): Promise<Course | undefined> => {
  try {
    const response = await fetch(`${API_BASE_URL}/courses/${id}`, {
      headers: getAuthHeaders()
    });
    
    return await handleApiResponse<Course>(response);
  } catch (error) {
    console.error('Error fetching course:', error);
    return undefined;
  }
};

export const getCourseTags = async (): Promise<string[]> => {
  try {
    const response = await fetch(`${API_BASE_URL}/courses/tags`, {
      headers: getAuthHeaders()
    });
    
    return await handleApiResponse<string[]>(response);
  } catch (error) {
    console.error('Error fetching course tags:', error);
    return [];
  }
};

export const addCourse = async (newCourseData: Omit<Course, 'id' | 'imageUrl' | 'modules' | 'downloads'>): Promise<Course> => {
  try {
    const response = await fetch(`${API_BASE_URL}/courses`, {
      method: 'POST',
      headers: getAuthHeaders(),
      body: JSON.stringify(newCourseData)
    });
    
    return await handleApiResponse<Course>(response);
  } catch (error) {
    throw new Error('Failed to add course');
  }
};

// Mock data for resources and DSA problems (these would be separate endpoints in a real implementation)
export const getResources = async (): Promise<Resource[]> => {
  // This would be a real API call in a full implementation
  return [];
};

export const getDsaProblems = async (): Promise<{ category: string, problems: DSAProblem[] }[]> => {
  // This would be a real API call in a full implementation
  return [];
};

export const getLeaderboard = async (): Promise<LeaderboardUser[]> => {
  // This would be a real API call in a full implementation
  return [];
};

export const getTransactionsForUser = async (userId: string): Promise<Payment[]> => {
  // This would be a real API call in a full implementation
  return [];
};

export const processPayment = async (userId: string, courseId: string, amount: number, transactionId: string): Promise<Payment> => {
  // This would be a real API call in a full implementation
  return {
    transactionId,
    userId,
    courseId,
    amount,
    timestamp: new Date().toISOString(),
  };
};