# 🚀 Deployment Guide - EduFluxAI

## 📋 Prerequisites

Before deploying, ensure you have:
- Java 17 or higher
- Node.js 18 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher
- Git

## 🌐 GitHub Deployment Steps

### 1. Create GitHub Repository

1. Go to [GitHub.com](https://github.com) and sign in
2. Click the "+" icon in the top right corner
3. Select "New repository"
4. Fill in the details:
   - **Repository name**: `EduFluxAI`
   - **Description**: `Java Full Stack Educational Platform with AI-powered learning assistance`
   - **Visibility**: Public (or Private if preferred)
   - **Initialize**: Don't initialize with README (we already have one)
5. Click "Create repository"

### 2. Connect Local Repository to GitHub

```bash
# Add the remote origin (replace YOUR_USERNAME with your GitHub username)
git remote add origin https://github.com/YOUR_USERNAME/EduFluxAI.git

# Push to GitHub
git push -u origin master
```

### 3. Set Up Development Environment

```bash
# Clone the repository (for other developers)
git clone https://github.com/YOUR_USERNAME/EduFluxAI.git
cd EduFluxAI

# Install dependencies
npm run install:all

# Set up database
mysql -u root -p -e "CREATE DATABASE edufluxai;"
```

### 4. Environment Configuration

#### Backend Configuration
Update `backend/src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

#### Frontend Configuration
The frontend automatically connects to the backend API.

## 🐳 Docker Deployment (Optional)

### Create Dockerfile for Backend

```dockerfile
# backend/Dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/backend-1.0.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

### Create Dockerfile for Frontend

```dockerfile
# frontend/Dockerfile
FROM node:18-alpine
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 3000
CMD ["npm", "run", "preview"]
```

### Docker Compose

```yaml
# docker-compose.yml
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: edufluxai
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  backend:
    build: ./backend
    ports:
      - "8080:8080"
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/edufluxai
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: rootpassword

  frontend:
    build: ./frontend
    ports:
      - "3000:3000"
    depends_on:
      - backend

volumes:
  mysql_data:
```

## ☁️ Cloud Deployment Options

### 1. Heroku Deployment

#### Backend (Heroku)
1. Create a new Heroku app
2. Add MySQL addon (JawsDB or ClearDB)
3. Deploy backend using Heroku CLI

#### Frontend (Vercel/Netlify)
1. Connect your GitHub repository
2. Set build command: `cd frontend && npm run build`
3. Set output directory: `frontend/dist`

### 2. AWS Deployment

#### Backend (AWS Elastic Beanstalk)
1. Package the backend: `mvn clean package`
2. Upload the JAR file to Elastic Beanstalk
3. Configure RDS MySQL database

#### Frontend (AWS S3 + CloudFront)
1. Build the frontend: `cd frontend && npm run build`
2. Upload `dist` folder to S3 bucket
3. Configure CloudFront distribution

### 3. DigitalOcean Deployment

#### Using App Platform
1. Connect your GitHub repository
2. Configure build settings for both frontend and backend
3. Set up managed MySQL database

## 🔧 Production Configuration

### Backend Production Settings

```properties
# application-prod.properties
spring.profiles.active=prod
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
server.port=${PORT:8080}
```

### Frontend Production Settings

```javascript
// frontend/.env.production
VITE_API_BASE_URL=https://your-backend-domain.com/api
```

## 📊 Monitoring and Logging

### Application Monitoring
- Use tools like New Relic, DataDog, or AWS CloudWatch
- Set up health checks for both services
- Monitor database performance

### Logging Configuration
```properties
# Logging configuration
logging.level.com.EduFluxAI=INFO
logging.level.org.springframework.security=DEBUG
logging.file.name=logs/edufluxai.log
```

## 🔐 Security Considerations

### Production Security
1. Use environment variables for sensitive data
2. Enable HTTPS/SSL certificates
3. Configure CORS properly
4. Set up rate limiting
5. Use strong database passwords
6. Enable database encryption

### Environment Variables
```bash
# Backend
DATABASE_URL=jdbc:mysql://localhost:3306/edufluxai
DB_USERNAME=your_username
DB_PASSWORD=your_secure_password
JWT_SECRET=your_jwt_secret_key

# Frontend
VITE_API_BASE_URL=https://your-backend-domain.com/api
```

## 🚀 CI/CD Pipeline

### GitHub Actions Workflow

```yaml
# .github/workflows/deploy.yml
name: Deploy EduFluxAI

on:
  push:
    branches: [ master ]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'
    - name: Run backend tests
      run: cd backend && mvn test
    - name: Set up Node.js
      uses: actions/setup-node@v2
      with:
        node-version: '18'
    - name: Run frontend tests
      run: cd frontend && npm test

  deploy:
    needs: test
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Deploy to production
      run: |
        # Your deployment commands here
```

## 📈 Performance Optimization

### Backend Optimization
- Enable connection pooling
- Use caching (Redis)
- Optimize database queries
- Enable compression

### Frontend Optimization
- Code splitting
- Lazy loading
- Image optimization
- CDN usage

## 🆘 Troubleshooting

### Common Issues
1. **Database Connection**: Check MySQL service and credentials
2. **CORS Issues**: Verify CORS configuration
3. **Build Failures**: Check Java/Node.js versions
4. **Port Conflicts**: Ensure ports 3000 and 8080 are available

### Support
- Check logs in `backend/logs/`
- Use browser developer tools for frontend issues
- Monitor application health endpoints

## 📞 Support

For deployment issues:
- Create an issue in the GitHub repository
- Check the troubleshooting section
- Review the logs for error messages
