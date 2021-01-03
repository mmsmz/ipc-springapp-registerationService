package com.ipc.registrationservice.controller;

import com.ipc.registrationservice.dto.ResetPassword;
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
import org.springframework.web.servlet.view.RedirectView;

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
    public ResponseEntity<ResponseDto> register(@RequestBody StudentDto studentDto,
                                                @RequestParam(required = false) String cpid, @RequestParam(required = false) String csid) {
        logger.info("Inside the get Registration Details method Start {}", studentDto.toString());

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(HomeConstant.SUCCESS);
        responseDto.setData(studentService.register(studentDto, csid, cpid));
        logger.info("Inside the get Registration Details method End");

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // checkotpurl
    @GetMapping(value = "/checkotpurl", produces = "application/json")
    public RedirectView checkotpurl(@RequestParam String userId, @RequestParam Integer otpPinNumber) {
        logger.info("Inside the Check OTP URL method Start {}", otpPinNumber.toString());

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(HomeConstant.SUCCESS);
        responseDto.setData(studentService.checkotpurl(userId, otpPinNumber));
        logger.info("Inside the Check OTP URL method End");

        return new RedirectView("http://localhost:4200/login-page?message=activated");

    }

    @PostMapping(value = "/forgetpassword", produces = "application/json")
    public ResponseEntity<ResponseDto> forgetPassword(@RequestParam String email) {
        logger.info("Inside the forgetPassword method Start {}", email);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(HomeConstant.SUCCESS);
        responseDto.setData(studentService.forgetPassword(email));
        logger.info("Inside the forgetPassword method End");

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/checkreseturl", produces = "application/json")
    public RedirectView checkReseturl(@RequestParam String userId, @RequestParam Integer pin) {
        logger.info("Inside the forgetPassword method Start {}", userId, pin);
        String url = "";
        if (studentService.checkResetUrl(userId, pin)) {
            url = "http://localhost:4200/forget-password?userid=" + userId + "&pin=" + pin;
        }
        logger.info("Inside the forgetPassword method End");

        return new RedirectView(url);
    }

    @PostMapping(value = "/resetpassword", produces = "application/json")
    public ResponseEntity<ResponseDto> resetPassword(@RequestBody ResetPassword resetPassword) {
        logger.info("Inside the reset password method Start {}", resetPassword.getUserid(), resetPassword.getNewpassword(), resetPassword.getPin());

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(HomeConstant.SUCCESS);
        responseDto.setData(studentService.resetPassword(resetPassword.getUserid(), resetPassword.getNewpassword(), resetPassword.getPin()));
        logger.info("Inside the reset password method End");

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
