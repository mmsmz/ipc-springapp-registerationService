package com.lpjpro.RegistrationService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ipjpro.RegistrationService.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
