package com.ipc.registrationservice.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ipc.registrationservice.entity.StudentPurchaseEntity;

@Repository
public interface PurchaseRepository extends JpaRepository<StudentPurchaseEntity, String> {

}
