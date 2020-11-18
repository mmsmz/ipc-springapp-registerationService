package com.Ipjpro.RegistrationService.ServiceImpl;

import java.time.Instant;
import java.util.List;

import com.Ipjpro.RegistrationService.Controller.HomeController;
import com.Ipjpro.RegistrationService.Util.HomeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Ipjpro.RegistrationService.Dto.StudentDto;
import com.Ipjpro.RegistrationService.Entity.StudentEntity;
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
    PasswordEncoder passwordEncoder;

    @Autowired
    StudentRepository studentRepository;

//   public StudentEntity saveStudent(final StudentDto studentDto) {
//         StudentEntity studentModel = register(studentDto);
//       return studentRepository.save(studentModel);
//    }

    @Override
    public String register(StudentDto studentDto) {

        try {
            List<StudentEntity> checkEmail = studentRepository.findByEmail(studentDto.getEmail());
            if (checkEmail.isEmpty()) {
                    List<StudentEntity> checkNicNr = studentRepository.findByNicNr(studentDto.getNicNr());
                    if (checkNicNr.isEmpty()) {
                        StudentEntity student = new StudentEntity();
                        student.setFirstName(studentDto.getFirstName());
                        student.setLastName(studentDto.getLastName());
                        student.setNicNr(studentDto.getNicNr());
                        student.setEmail(studentDto.getEmail());
                        student.setMobile(studentDto.getMobile());
                        student.setDate(Instant.now());
                        student.setUserType(HomeConstant.TYPE_STUDENT);
                        student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
                        student.setLoginstatus(HomeConstant.activated);
                        studentRepository.save(student);
                        return HomeConstant.SUCCESSFULLY_REGISTERED;
                    } else {
                        logger.info(HomeConstant.NICNR_ALREADY_EXISTING);
                        return HomeConstant.NICNR_ALREADY_EXISTING;
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
