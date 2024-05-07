package com.example.Controller.DB.ManyToMany;


import com.example.Entity.DB.ManyToMany.Doctor;
import com.example.Service.DB.ManyToMany.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping(value = "/save-doctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor d = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(d, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update-doctor/{id}")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor, @PathVariable long id) {
        Doctor d = doctorService.updateDoctor(doctor, id);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-doctor/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>("Doctor deleted", HttpStatus.OK);
    }

    @GetMapping(value = "/getall-doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping(value = "/getone-doctor/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable long id) {
        Doctor d = doctorService.getDoctor(id);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }
}
