-- Insert sample users
INSERT INTO users (name, email, password, role, is_verified, created_at, updated_at) VALUES
('Admin User', 'admin@edufluxai.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyVqL5J5V8LZ8K8K8K8K8K8K8K8K', 'ADMIN', true, NOW(), NOW()),
('John Student', 'john@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyVqL5J5V8LZ8K8K8K8K8K8K8K8K', 'STUDENT', true, NOW(), NOW()),
('Jane Student', 'jane@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyVqL5J5V8LZ8K8K8K8K8K8K8K8K', 'STUDENT', true, NOW(), NOW());

-- Insert sample courses
INSERT INTO courses (title, description, price, instructor, image_url, type, created_at, updated_at) VALUES
('Complete Web Development Bootcamp', 'Learn HTML, CSS, JavaScript, React, Node.js and more!', 99.99, 'Dr. Sarah Johnson', 'https://picsum.photos/seed/webdev/600/400', 'PAID', NOW(), NOW()),
('Python for Beginners', 'Start your programming journey with Python', 0.00, 'Prof. Mike Chen', 'https://picsum.photos/seed/python/600/400', 'FREE', NOW(), NOW()),
('Advanced Data Structures', 'Master algorithms and data structures', 149.99, 'Dr. Alex Rodriguez', 'https://picsum.photos/seed/dsa/600/400', 'PAID', NOW(), NOW()),
('Machine Learning Fundamentals', 'Introduction to ML concepts and applications', 199.99, 'Dr. Emily Watson', 'https://picsum.photos/seed/ml/600/400', 'PAID', NOW(), NOW()),
('JavaScript ES6+ Features', 'Modern JavaScript development', 0.00, 'Sarah Kim', 'https://picsum.photos/seed/js/600/400', 'FREE', NOW(), NOW());

-- Insert course tags
INSERT INTO course_tags (course_id, tag) VALUES
(1, 'Web Development'),
(1, 'JavaScript'),
(1, 'React'),
(1, 'Node.js'),
(2, 'Python'),
(2, 'Programming'),
(2, 'Beginner'),
(3, 'Algorithms'),
(3, 'Data Structures'),
(3, 'Advanced'),
(4, 'Machine Learning'),
(4, 'AI'),
(4, 'Data Science'),
(5, 'JavaScript'),
(5, 'ES6'),
(5, 'Modern Web');

-- Insert sample payments
INSERT INTO payments (transaction_id, user_id, course_id, amount, timestamp, status) VALUES
('tx_001', 2, 1, 99.99, NOW(), 'COMPLETED'),
('tx_002', 2, 3, 149.99, NOW(), 'COMPLETED'),
('tx_003', 3, 4, 199.99, NOW(), 'COMPLETED');
