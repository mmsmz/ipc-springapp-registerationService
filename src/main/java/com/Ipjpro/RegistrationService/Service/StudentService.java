package com.Ipjpro.RegistrationService.Service;

import org.springframework.stereotype.Service;

import com.Ipjpro.RegistrationService.Dto.StudentDto;

@Service
public interface StudentService {

	 String register(StudentDto studentDto);

}
