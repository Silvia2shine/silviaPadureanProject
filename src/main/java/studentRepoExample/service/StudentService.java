package studentRepoExample.service;

import com.siit.studentRepoExample.model.Student;
import com.siit.studentRepoExample.model.dto.CreateStudentDto;
import com.siit.studentRepoExample.repository.JpaStudentRepository;

import java.util.regex.Pattern;


public class StudentService {
    private  JpaStudentRepository jpaStudentRepository = new JpaStudentRepository();

    public StudentService() {
    }


    public boolean addStudent(CreateStudentDto createStudentDto) {

        if (!validateStudentData(createStudentDto)) {
            return false;
        }

        Student student = new Student();

        student.setName(createStudentDto.getName());
        student.setEmail(createStudentDto.getEmail());

        jpaStudentRepository.addStudent(student);
        return true;
    }

    public boolean validateStudentData(CreateStudentDto studentDto) {
        String emailValidationPattern = "^(.+)@(.+)$";
        boolean emailIsOK = studentDto.getEmail() != null && Pattern.compile(emailValidationPattern)
                .matcher(studentDto.getEmail())
                .matches();
        boolean nameIsOk = studentDto.getName() != null && studentDto.getName().length() < 50;

        return emailIsOK && nameIsOk;
    }


}
