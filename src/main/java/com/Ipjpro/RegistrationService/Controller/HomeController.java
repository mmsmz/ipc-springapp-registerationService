package com.Ipjpro.RegistrationService.Controller;
import com.Ipjpro.RegistrationService.Dto.ResponseDto;
import com.Ipjpro.RegistrationService.Util.HomeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.Ipjpro.RegistrationService.Dto.StudentDto;
import com.Ipjpro.RegistrationService.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {

    /**
     * The Logger
     */
    final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	StudentService studentService;
	
	@GetMapping("/")
    String home() {
        return "<h1>Welcome!!!</h1>";
    }
	
	@PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<ResponseDto> register(@RequestBody StudentDto studentDto) {
        logger.info("Inside the get Registration Details method Start" + studentDto.toString());

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
