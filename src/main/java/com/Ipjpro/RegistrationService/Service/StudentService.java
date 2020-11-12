package com.Ipjpro.RegistrationService.Service;

import org.springframework.stereotype.Service;

import com.Ipjpro.RegistrationService.Dto.StudentDto;

import java.util.List;

@Service
public interface StudentService {

	public String register(StudentDto studentDto);
}
