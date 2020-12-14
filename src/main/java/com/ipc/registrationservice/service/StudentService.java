package com.ipc.registrationservice.service;

import com.ipc.registrationservice.dto.StudentDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface StudentService {

	 String register(StudentDto studentDto);

	 boolean checkotpurl(String userId, Integer otpPinNumber);

}
