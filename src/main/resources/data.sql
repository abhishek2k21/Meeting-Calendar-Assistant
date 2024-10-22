INSERT INTO employees (name, email, meeting_duration)
VALUES 
('Alice', 'alice@example.com', 30),
('Bob', 'bob@example.com', 60),
('Charlie', 'charlie@example.com', 45);

INSERT INTO meetings (employee_id, meeting_time, duration)
VALUES 
(1, '2024-10-21 10:00:00', 30),
(1, '2024-10-21 11:00:00', 30),
(2, '2024-10-21 12:00:00', 60);
