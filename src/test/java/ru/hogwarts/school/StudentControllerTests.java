package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void createStudentTest() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Billy");
        student.setAge(17);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", student, String.class))
                .isNotNull();
    }

    @Test
    void getAllTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students", String.class))
                .isNotNull();
    }

    @Test
    void editStudentTest() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Piter");
        student.setAge(18);

        restTemplate.put("http://localhost:" + port + "/students", student, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/1",  String.class)).isNotEqualTo(student);
    }

    @Test
    void deleteStudentTest() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Piter");
        student.setAge(18);

        restTemplate.put("http://localhost:" + port + "/students", student, String.class);
        restTemplate.delete("http://localhost:" + port + "/students/1", student, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/1",  String.class)).isNull();
    }

    @Test
    void getStudentTest() throws  Exception {
        int id = 4;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/" + id,  String.class)).isNotNull();
    }

    @Test
    void findByAgeTest() throws Exception {
        long age = 17;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/filter/" + age,  String.class)).isNotNull();
    }

    @Test
    void getStudentByAgeBetweenTest() throws Exception {
        long age1 = 10;
        long age2 = 17;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/ageBetween/" + age1 + age2,  String.class))
                .isNotNull();
    }

    @Test
    void getFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(2L);
        faculty.setName("bla-bla");
        faculty.setColor("red");
        restTemplate.put("http://localhost:" + port + "/faculties", faculty, String.class);

        Student student = new Student();
        student.setId(1L);
        student.setName("Piter");
        student.setAge(18);
        student.setFaculty(faculty);
        restTemplate.put("http://localhost:" + port + "/students", student, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/faculty/1",  String.class))
                .isNotNull();

    }
}
