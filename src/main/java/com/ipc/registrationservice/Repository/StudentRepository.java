package com.ipc.registrationservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipc.registrationservice.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
	List<StudentEntity> findByEmail(String email);
	List<StudentEntity> findByNicNr(String nicNr);

}
