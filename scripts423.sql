SELECT student.name, student.age, faculty.name
FROM student
LEFT JOIN faculty ON student.faculty_id = faculty.id ORDER BY  faculty.name;

SELECT student.name, student.age, a.file_path
from student
INNER JOIN avatar a on student.id = a.student_id;