package com.ipc.registrationservice.serviceImpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ipc.registrationservice.dto.StudentDto;
import com.ipc.registrationservice.controller.HomeController;
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
					student.setLoginstatus(HomeConstant.activated);
					studentRepository.save(student);
					
					//OTP Mail
					EmailMessageDto emailMessageDto=new EmailMessageDto();
					emailMessageDto.setToAddress(studentDto.getEmail());
					emailMessageDto.setSubject("your code:");
					emailMessageDto.setSubject("your code:");
					ObjectMapper mapper = new ObjectMapper();
					String json = mapper.writeValueAsString(emailMessageDto);
					Map<String, Object> jsonVal = new ObjectMapper().readValue(json, HashMap.class);
					HttpEntity<Map> entity = new HttpEntity<>(jsonVal);
					//
					//enter the mail service Url (API)
					String url = "http://localhost:8090";
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

}
