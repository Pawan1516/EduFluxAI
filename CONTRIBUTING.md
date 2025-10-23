# 🤝 Contributing to EduFluxAI

Thank you for your interest in contributing to EduFluxAI! This document provides guidelines and information for contributors.

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Setup](#development-setup)
- [Contributing Guidelines](#contributing-guidelines)
- [Pull Request Process](#pull-request-process)
- [Issue Reporting](#issue-reporting)
- [Coding Standards](#coding-standards)

## 📜 Code of Conduct

This project follows the [Contributor Covenant](https://www.contributor-covenant.org/) Code of Conduct. By participating, you agree to uphold this code.

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Node.js 18 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher
- Git

### Fork and Clone

1. Fork the repository on GitHub
2. Clone your fork locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/EduFluxAI.git
   cd EduFluxAI
   ```
3. Add the upstream repository:
   ```bash
   git remote add upstream https://github.com/ORIGINAL_OWNER/EduFluxAI.git
   ```

## 🛠️ Development Setup

### 1. Backend Setup

```bash
cd backend

# Install dependencies
mvn clean install

# Set up database
mysql -u root -p -e "CREATE DATABASE edufluxai;"

# Run the application
mvn spring-boot:run
```

### 2. Frontend Setup

```bash
cd frontend

# Install dependencies
npm install

# Start development server
npm run dev
```

### 3. Database Setup

```sql
-- Create database
CREATE DATABASE edufluxai;

-- The application will automatically create tables and insert sample data
```

## 📝 Contributing Guidelines

### Types of Contributions

- 🐛 **Bug fixes**
- ✨ **New features**
- 📚 **Documentation improvements**
- 🧪 **Tests**
- 🎨 **UI/UX improvements**
- 🔧 **Performance optimizations**

### Development Workflow

1. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make your changes**
   - Follow coding standards
   - Write tests for new functionality
   - Update documentation if needed

3. **Test your changes**
   ```bash
   # Backend tests
   cd backend && mvn test
   
   # Frontend tests
   cd frontend && npm test
   ```

4. **Commit your changes**
   ```bash
   git commit -m "feat: add new feature description"
   ```

5. **Push to your fork**
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a Pull Request**

## 🔄 Pull Request Process

### Before Submitting

- [ ] Code follows the project's coding standards
- [ ] Tests pass locally
- [ ] Documentation is updated
- [ ] No merge conflicts
- [ ] Commit messages are clear and descriptive

### PR Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Backend tests pass
- [ ] Frontend tests pass
- [ ] Manual testing completed

## Screenshots (if applicable)
Add screenshots for UI changes

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Documentation updated
- [ ] No new warnings introduced
```

## 🐛 Issue Reporting

### Before Creating an Issue

1. Check existing issues
2. Search for similar problems
3. Ensure you're using the latest version

### Issue Template

```markdown
**Describe the bug**
A clear description of what the bug is.

**To Reproduce**
Steps to reproduce the behavior:
1. Go to '...'
2. Click on '....'
3. See error

**Expected behavior**
What you expected to happen.

**Screenshots**
If applicable, add screenshots.

**Environment:**
- OS: [e.g. Windows 10]
- Java version: [e.g. 17]
- Node.js version: [e.g. 18]
- Browser: [e.g. Chrome 91]

**Additional context**
Any other context about the problem.
```

## 📏 Coding Standards

### Java (Backend)

- Follow Java naming conventions
- Use meaningful variable and method names
- Add Javadoc comments for public methods
- Keep methods small and focused
- Use proper exception handling

```java
/**
 * Retrieves a user by their ID.
 * 
 * @param id the user ID
 * @return the user if found
 * @throws UserNotFoundException if user doesn't exist
 */
public User findById(Long id) {
    // Implementation
}
```

### TypeScript/React (Frontend)

- Use TypeScript strict mode
- Follow React best practices
- Use functional components with hooks
- Implement proper error boundaries
- Use meaningful prop types

```typescript
interface UserProps {
  user: User;
  onUpdate: (user: User) => void;
}

const UserComponent: React.FC<UserProps> = ({ user, onUpdate }) => {
  // Implementation
};
```

### Git Commit Messages

Use conventional commits format:

```
type(scope): description

feat(auth): add JWT token validation
fix(api): resolve CORS configuration issue
docs(readme): update installation instructions
```

Types: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`

## 🧪 Testing Guidelines

### Backend Testing

- Write unit tests for service methods
- Integration tests for API endpoints
- Use `@SpringBootTest` for full context tests
- Mock external dependencies

```java
@Test
void shouldReturnUserWhenValidId() {
    // Given
    Long userId = 1L;
    User expectedUser = new User();
    
    // When
    User actualUser = userService.findById(userId);
    
    // Then
    assertThat(actualUser).isEqualTo(expectedUser);
}
```

### Frontend Testing

- Test component rendering
- Test user interactions
- Test API integration
- Use React Testing Library

```typescript
test('renders user dashboard', () => {
  render(<StudentDashboard user={mockUser} />);
  expect(screen.getByText('Welcome')).toBeInTheDocument();
});
```

## 📚 Documentation

### Code Documentation

- Add JSDoc comments for complex functions
- Document API endpoints
- Include examples in documentation
- Keep README updated

### API Documentation

Use OpenAPI/Swagger annotations:

```java
@Operation(summary = "Get user by ID")
@ApiResponse(responseCode = "200", description = "User found")
@ApiResponse(responseCode = "404", description = "User not found")
@GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
    // Implementation
}
```

## 🏷️ Release Process

1. Update version numbers
2. Update CHANGELOG.md
3. Create release notes
4. Tag the release
5. Deploy to production

## 📞 Getting Help

- 💬 **Discussions**: Use GitHub Discussions for questions
- 🐛 **Issues**: Report bugs using GitHub Issues
- 📧 **Email**: Contact maintainers for sensitive issues

## 🙏 Recognition

Contributors will be recognized in:
- README.md contributors section
- Release notes
- Project documentation

Thank you for contributing to EduFluxAI! 🎓
