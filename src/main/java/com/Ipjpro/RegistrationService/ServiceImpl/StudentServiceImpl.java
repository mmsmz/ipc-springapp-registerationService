package com.Ipjpro.RegistrationService.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.Ipjpro.RegistrationService.Dto.StudentDto;
import com.Ipjpro.RegistrationService.Entity.Student;
import com.Ipjpro.RegistrationService.Service.StudentService;
import com.lpjpro.RegistrationService.Repository.StudentRepository;

public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public Integer Register(StudentDto studentDto) {

		Student student = new Student();
		student.setUserName(studentDto.getUserName());
		student.setEmail(studentDto.getEmail());
		student.setMobile(studentDto.getMobile());
		student.setGender(studentDto.getGender());
		studentRepository.save(student);
		return 1;
	}

}
