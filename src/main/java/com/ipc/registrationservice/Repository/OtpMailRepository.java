package com.ipc.registrationservice.Repository;

import com.ipc.registrationservice.entity.OtpMailEntity;
import com.ipc.registrationservice.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OtpMailRepository extends JpaRepository <OtpMailEntity, Long> {
    List<OtpMailEntity> findByUserId(String userId);
    List<OtpMailEntity> findByUserIdAndOtpPinNumber(String userId, Integer checkOptNumber);

}
