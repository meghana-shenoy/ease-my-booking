package com.example.easemybooking.repo;
import com.example.easemybooking.model.Owners;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnersRepo extends JpaRepository<Owners, Integer> {
}
