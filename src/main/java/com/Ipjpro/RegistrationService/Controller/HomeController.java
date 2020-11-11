package com.Ipjpro.RegistrationService.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.Ipjpro.RegistrationService.Dto.StudentDto;
import com.Ipjpro.RegistrationService.Service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class HomeController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("/")
    String home() {
        return "<h1>Welcome!!!</h1>";
    }
	
	@PostMapping("/register")
    public String register(@RequestBody StudentDto studentDto) {
        return studentService.Register(studentDto);
    }
	
	@GetMapping("/admin")
    public String admin() {
        return "<h1>Hello Admin</h1>";
    }
}
