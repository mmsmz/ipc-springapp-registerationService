package com.ipc.registrationservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipc.registrationservice.entity.ForgetPasswordEntity;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPasswordEntity,String>{
	ForgetPasswordEntity findByUserIdAndPin(String userId,Integer pin);
}
