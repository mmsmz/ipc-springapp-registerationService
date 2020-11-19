package com.ipc.registrationservice.service;

import com.ipc.registrationservice.dto.StudentDto;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

	 String register(StudentDto studentDto);

}
