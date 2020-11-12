package com.lpjpro.RegistrationService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ipjpro.RegistrationService.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByEmail(String email);
	List<Student> findByNicNr(String nicNr);
	List<Student> findByMobile(String mobile);
}
