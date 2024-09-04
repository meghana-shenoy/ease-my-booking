package com.example.easemybooking.repo;
import com.example.easemybooking.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Integer> {
//    Location findByLname(String lname);
//    Location deleteByLname(String lname);
}
