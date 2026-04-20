-- Create Database
CREATE DATABASE college;
USE college;

-- Student Table
CREATE TABLE student (
    student_id VARCHAR(10) PRIMARY KEY,
    student_name VARCHAR(50),
    class VARCHAR(20)
);

-- Teacher Table
CREATE TABLE teacher (
    teacher_id VARCHAR(10) PRIMARY KEY,
    teacher_name VARCHAR(50),
    subject VARCHAR(50)
);

-- Marks Table
CREATE TABLE marks (
    student_id VARCHAR(10),
    subject VARCHAR(50),
    marks INT,
    PRIMARY KEY (student_id, subject),
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);

-- Insert Student Data
INSERT INTO student VALUES
('S101', 'Krithi', 'CSE-A'),
('S102', 'Saku', 'CSE-B'),
('S103', 'Sureka', 'IT-A'),
('S104', 'Kavi', 'IT-B');

-- Insert Teacher Data
INSERT INTO teacher VALUES
('T101', 'Kamali', 'IOT'),
('T102', 'Lali', 'CN'),
('T103', 'Sureka', 'SoftSkill');

-- Insert Marks Data
INSERT INTO marks VALUES
('S101', 'IOT', 85),
('S101', 'CN', 78),
('S102', 'IOT', 90),
('S103', 'SoftSkill', 88),
('S104', 'IOT', 76);