package com.enigma.demo.repository;

import com.enigma.demo.entity.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailRepositary extends JpaRepository<PurchaseDetail, String> {
}
