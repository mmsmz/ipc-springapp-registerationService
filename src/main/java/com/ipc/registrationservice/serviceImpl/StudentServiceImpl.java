package com.ipc.registrationservice.serviceImpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ipc.registrationservice.Repository.OtpMailRepository;
import com.ipc.registrationservice.dto.StudentDto;
import com.ipc.registrationservice.controller.HomeController;
import com.ipc.registrationservice.entity.OtpMailEntity;
import com.ipc.registrationservice.util.HomeConstant;

import com.ipc.registrationservice.dto.EmailMessageDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ipc.registrationservice.entity.StudentEntity;
import com.ipc.registrationservice.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipc.registrationservice.Repository.StudentRepository;

@Service
@EnableJpaRepositories("com/ipc/registrationservice/Repository")
public class StudentServiceImpl implements StudentService {

	/**
	 * The Logger
	 */
	final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	public RestTemplate restTemplate;

	@Autowired
	OtpMailRepository otpMailRepository;

	private int optcode(){
		Random randomGenerator = new Random();
		int randomInteger = randomGenerator.nextInt(99999);
		return  randomInteger;
	}


	@Override
	public String register(StudentDto studentDto) {
		LocalDate localDate = LocalDate.now();

		try {
			List<StudentEntity> checkEmail = studentRepository.findByEmail(studentDto.getEmail());
			if (checkEmail.isEmpty()) {
				List<StudentEntity> checkNicNr = studentRepository.findByNicNr(studentDto.getNicNr());
				if (checkNicNr.isEmpty()) {
					StudentEntity student = new StudentEntity();
					student.setFirstName(studentDto.getFirstName());
					student.setLastName(studentDto.getLastName());
					student.setNicNr(studentDto.getNicNr());
					student.setEmail(studentDto.getEmail());
					student.setMobile(studentDto.getMobile());
					student.setInstituteBranch(studentDto.getInstitutebranch());
					student.setDate(localDate);
					student.setUserType(HomeConstant.TYPE_STUDENT);
					student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
					student.setLoginStatus(HomeConstant.DEACTIVATE);
					student.setUserAccountType(HomeConstant.USER_ACCOUNT_IPC);
					studentRepository.save(student);
					
					//OTP Mail
					EmailMessageDto emailMessageDto=new EmailMessageDto();
					emailMessageDto.setToAddress(studentDto.getEmail());
					emailMessageDto.setSubject("IPC");

					int randomI =  optcode();

					List<StudentEntity>  studentEntitiesList = studentRepository.findByEmail(student.getEmail());

					OtpMailEntity otpMailEntity = new OtpMailEntity();
					otpMailEntity.setOtpPinNumber(randomI);
					otpMailEntity.setUserId(studentEntitiesList.get(0).getUserid());
					otpMailEntity.setCreatedDate(localDate);
					otpMailRepository.save(otpMailEntity);

					emailMessageDto.setBody("Click this link to activate you account: http://localhost:8093/registration/checkotpurl/?userId="+studentEntitiesList.get(0).getUserid()+"&otpPinNumber="+randomI);

					ObjectMapper mapper = new ObjectMapper();
					String json = mapper.writeValueAsString(emailMessageDto);
					Map<String, Object> jsonVal = new ObjectMapper().readValue(json, HashMap.class);
					HttpEntity<Map> entity = new HttpEntity<>(jsonVal);
					
					//enter the mail service Url (API)
					String url = "http://localhost:8097/mailcontroller/send";
					restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();

					return HomeConstant.SUCCESSFULLY_REGISTERED;
				} else {
					logger.info(HomeConstant.NICNR_ALREADY_EXISTING);
					return HomeConstant.NICNR_ALREADY_EXISTING;
				}

			} else {
				logger.info(HomeConstant.EMAIL_ALREADY_EXISTING);
				return HomeConstant.EMAIL_ALREADY_EXISTING;
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			return e.getMessage();
		}
	}

	@Override
	public boolean checkotpurl(String userId, Integer otpPinNumber) {

		List<OtpMailEntity> otpEntitiesList = otpMailRepository.findByUserIdAndOtpPinNumber(userId, otpPinNumber);

		if(otpEntitiesList.isEmpty()){
			return  false;
		}
		else{
			List<StudentEntity> studentEntitiesList = studentRepository.findByUserid(userId);
			StudentEntity studentEntity = new StudentEntity();
			studentEntity = studentEntitiesList.get(0);
			studentEntity.setLoginStatus(HomeConstant.ACTIVATE);
			studentRepository.save(studentEntity);
			return true;
		}
	}



}
