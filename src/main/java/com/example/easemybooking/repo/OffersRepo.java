package com.example.easemybooking.repo;
import com.example.easemybooking.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffersRepo extends JpaRepository<Offer, Integer> {
}
