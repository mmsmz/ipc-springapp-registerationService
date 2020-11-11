package com.Ipjpro.RegistrationService.ServiceImpl;

import java.util.List;

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
			if (checkEmail.isEmpty()) {
				List<Student> checkMobile = studentRepository.findByMobile(studentDto.getMobile());
				if (checkMobile.isEmpty()) {
					Student student = new Student();
					student.setUserName(studentDto.getUserName());
					student.setEmail(studentDto.getEmail());
					student.setMobile(studentDto.getMobile());
					student.setGender(studentDto.getGender());
					student.setPassword("asdf");
					studentRepository.save(student);
				} else {
					return "Invalid Mobile";
				}
			} else {
				return "Invalid Email";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "Sucessfully registered Student";
	}

}
