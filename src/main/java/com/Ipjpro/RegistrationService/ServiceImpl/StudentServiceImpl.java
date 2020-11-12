package com.Ipjpro.RegistrationService.ServiceImpl;

import java.util.List;

import com.Ipjpro.RegistrationService.Controller.HomeController;
import com.Ipjpro.RegistrationService.Util.HomeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * The Logger
     */
    final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String register(StudentDto studentDto) {

        try {
            List<Student> checkEmail = studentRepository.findByEmail(studentDto.getEmail());
            if (checkEmail.isEmpty()) {
                List<Student> checkMobile = studentRepository.findByMobile(studentDto.getMobile());
                if (checkMobile.isEmpty()) {
                    List<Student> checkNicNr = studentRepository.findByNicNr(studentDto.getNicNr());
                    if (checkNicNr.isEmpty()) {
                        Student student = new Student();
                        student.setFirstName(studentDto.getFirstName());
                        student.setLastName(studentDto.getLastName());
                        student.setNicNr(studentDto.getNicNr());
                        student.setEmail(studentDto.getEmail());
                        student.setPassword(studentDto.getPassword());
                        student.setMobile(studentDto.getMobile());
                        student.setGender(studentDto.getGender());
                        studentRepository.save(student);
                        return HomeConstant.SUCCESSFULLY_REGISTERED;
                    } else {
                        logger.info(HomeConstant.NICNR_ALREADY_EXISTING);
                        return HomeConstant.NICNR_ALREADY_EXISTING;
                    }
                } else {
                    logger.info(HomeConstant.MOBILE_ALREADY_EXISTING);
                    return HomeConstant.MOBILE_ALREADY_EXISTING;
                }

            } else {
                logger.info(HomeConstant.EMAIL_ALREADY_EXISTING);
                return HomeConstant.EMAIL_ALREADY_EXISTING;
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
            return e.getMessage();
        }
    }

}
