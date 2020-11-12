package com.Ipjpro.RegistrationService.ServiceImpl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.Ipjpro.RegistrationService.Dto.StudentDto;
import com.Ipjpro.RegistrationService.Entity.Student;
import com.Ipjpro.RegistrationService.Service.StudentService;
import com.lpjpro.RegistrationService.Repository.StudentRepository;

@Service
@EnableJpaRepositories("com.lpjpro.RegistrationService.Repository")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String Register(StudentDto studentDto) {

        try {
            List<Student> checkEmail = studentRepository.findByEmail(studentDto.getEmail());
//            List<Student> checkNicNr = studentRepository.findByNicNr(studentDto.getNicNr());
            if (checkEmail.isEmpty()) {
                    List<Student> checkMobile = studentRepository.findByMobile(studentDto.getMobile());
                    if (checkMobile.isEmpty()) {
                        Student student = new Student();
                        student.setFirstName(studentDto.getFirstName());
                        student.setLastName(studentDto.getLastName());
                        student.setNicNr(studentDto.getNicNr());
                        student.setEmail(studentDto.getEmail());
                        student.setPassword(studentDto.getPassword());
                        student.setMobile(studentDto.getMobile());
                        student.setGender(studentDto.getGender());
                        studentRepository.save(student);
                    } else {
                        return "Invalid Mobile";
                    }

            } else {
                return "Invalid Email";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "Sucessfully registered Student";
    }

}
