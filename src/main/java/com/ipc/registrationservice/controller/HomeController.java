package com.ipc.registrationservice.controller;
import com.ipc.registrationservice.dto.StudentDto;
import com.ipc.registrationservice.util.HomeConstant;
import com.ipc.registrationservice.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ipc.registrationservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
@CrossOrigin(origins = "*")
public class HomeController {

    /**
     * The Logger
     */
    final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	StudentService studentService;
	
	@GetMapping("/home")
    String home() {
        return "<h1>Welcome!!!</h1>";
    }
	
	@PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<ResponseDto> register(@RequestBody StudentDto studentDto) {
        logger.info("Inside the get Registration Details method Start {}", studentDto.toString());

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(HomeConstant.SUCCESS);
        responseDto.setData( studentService.register(studentDto));
        logger.info("Inside the get Registration Details method End");

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
	
	@GetMapping("/admin")
    public String admin() {
        return "<h1>Hello Admin</h1>";
    }
}