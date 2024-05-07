package com.example.Repository.ManyToMany;

import com.example.Entity.DB.ManyToMany.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
