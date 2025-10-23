# 🎓 EduFluxAI - Java Full Stack Educational Platform

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-19.2.0-blue.svg)](https://reactjs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.8.2-blue.svg)](https://www.typescriptlang.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A comprehensive educational platform built with Java Spring Boot backend and React TypeScript frontend, featuring AI-powered learning assistance, course management, and student progress tracking.

## 🏗️ Project Structure

```
EduFluxAI/
├── backend/                          # Spring Boot Backend
│   ├── src/main/java/com/EduFluxAI/backend/
│   │   ├── controller/               # REST Controllers
│   │   ├── entity/                  # JPA Entities
│   │   ├── repository/              # Data Repositories
│   │   ├── service/                 # Business Logic
│   │   ├── dto/                     # Data Transfer Objects
│   │   ├── config/                  # Configuration Classes
│   │   └── util/                    # Utility Classes
│   ├── src/main/resources/
│   │   ├── application.properties   # Application Configuration
│   │   └── data.sql                 # Sample Data
│   └── pom.xml                      # Maven Configuration
├── frontend/                        # React TypeScript Frontend
│   ├── components/                  # React Components
│   ├── services/                    # API Services
│   ├── utils/                       # Utility Functions
│   ├── types.ts                     # TypeScript Types
│   ├── constants.ts                 # Application Constants
│   ├── App.tsx                      # Main App Component
│   ├── index.tsx                    # Entry Point
│   ├── index.html                   # HTML Template
│   ├── vite.config.ts               # Vite Configuration
│   ├── tsconfig.json                # TypeScript Configuration
│   └── package.json                 # Frontend Dependencies
├── package.json                     # Root Project Scripts
└── README.md                        # This File
```

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Node.js 18 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### 1. Database Setup
```sql
CREATE DATABASE edufluxai;
```

### 2. Install Dependencies
```bash
# Install all dependencies (frontend + backend)
npm run install:all

# Or install separately:
npm run install:frontend  # Frontend dependencies
npm run install:backend  # Backend dependencies
```

### 3. Run the Application

#### Option A: Run Both Services
```bash
# Terminal 1 - Backend
npm run dev:backend

# Terminal 2 - Frontend  
npm run dev:frontend
```

#### Option B: Run Separately
```bash
# Backend only
cd backend
mvn spring-boot:run

# Frontend only
cd frontend
npm run dev
```

## 🌐 Access Points

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **Database**: MySQL on localhost:3306

## 🔧 Development Commands

### Root Level Commands
```bash
npm run install:all      # Install all dependencies
npm run dev:frontend     # Start frontend dev server
npm run dev:backend      # Start backend server
npm run build:frontend   # Build frontend for production
npm run build:backend    # Build backend JAR
```

### Backend Commands
```bash
cd backend
mvn clean compile        # Clean and compile
mvn test                # Run tests
mvn spring-boot:run    # Start development server
mvn clean package       # Create JAR file
```

### Frontend Commands
```bash
cd frontend
npm install             # Install dependencies
npm run dev            # Start development server
npm run build          # Build for production
npm run preview        # Preview production build
```

## 🗄️ Database Schema

### Users Table
- `id` (Primary Key)
- `name` (VARCHAR)
- `email` (VARCHAR, Unique)
- `password` (VARCHAR, Encrypted)
- `role` (ENUM: ADMIN, STUDENT)
- `is_verified` (BOOLEAN)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

### Courses Table
- `id` (Primary Key)
- `title` (VARCHAR)
- `description` (TEXT)
- `price` (DECIMAL)
- `instructor` (VARCHAR)
- `image_url` (VARCHAR)
- `type` (ENUM: FREE, PAID)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

### Payments Table
- `id` (Primary Key)
- `transaction_id` (VARCHAR, Unique)
- `user_id` (Foreign Key)
- `course_id` (Foreign Key)
- `amount` (DECIMAL)
- `timestamp` (TIMESTAMP)
- `status` (ENUM: PENDING, COMPLETED, FAILED, REFUNDED)

## 🔐 Security Features

- JWT-based authentication
- Password encryption with BCrypt
- CORS configuration for frontend-backend communication
- Input validation and sanitization
- Role-based access control

## 📊 API Endpoints

### Authentication
- `POST /api/users/register` - User registration
- `POST /api/users/login` - User login

### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Courses
- `GET /api/courses` - Get all courses (with filters)
- `GET /api/courses/{id}` - Get course by ID
- `POST /api/courses` - Create course
- `PUT /api/courses/{id}` - Update course
- `DELETE /api/courses/{id}` - Delete course
- `GET /api/courses/tags` - Get all course tags

## 🎯 Features

### For Students
- Course browsing and enrollment
- User profile management
- Progress tracking
- AI-powered learning assistant
- DSA problem solving
- Career guidance quiz
- Resume builder

### For Administrators
- Course management
- User management
- Analytics dashboard
- Content creation tools

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 3.2.0
- **Database**: MySQL 8.0
- **Security**: Spring Security with JWT
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven

### Frontend
- **Framework**: React 19.2.0 with TypeScript
- **Build Tool**: Vite
- **Styling**: Tailwind CSS
- **State Management**: React Hooks

## 📝 Environment Configuration

### Backend Configuration
Edit `backend/src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Frontend Configuration
The frontend automatically proxies API requests to the backend.

## 🚀 Deployment

### Backend Deployment
```bash
cd backend
mvn clean package
java -jar target/backend-1.0.0.jar
```

### Frontend Deployment
```bash
cd frontend
npm run build
# Deploy the 'dist' folder to your web server
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 🆘 Support

For support and questions, please open an issue in the GitHub repository.