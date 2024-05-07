package com.example.Controller.DB.OneToOne;

import com.example.Entity.DB.OneToOne.StudentAddress;
import com.example.Service.DB.OneToOne.StudentAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/address")
public class StudentAddressController {

    @Autowired
    private StudentAddressService studentAddressService;

    @PostMapping(value="/add-student-address")
    public ResponseEntity<StudentAddress> addStudentAddress(@RequestBody StudentAddress studentAddress) {
        StudentAddress studentAddress1 = studentAddressService.save(studentAddress);
        return new ResponseEntity<>(studentAddress1, HttpStatus.CREATED);
    }

    @GetMapping(value="/getone-student-address/{id}")
    public ResponseEntity<StudentAddress> getOneStudentAddress(@PathVariable long id) {
        StudentAddress studentAddress = studentAddressService.findById(id);
        return new ResponseEntity<>(studentAddress, HttpStatus.OK);
    }

    @GetMapping(value="/getall-student-address")
    public ResponseEntity<List<StudentAddress>> getAllStudentAddress() {
        List<StudentAddress> studentAddressList = studentAddressService.findAll();
        return new ResponseEntity<>(studentAddressList, HttpStatus.OK);
    }

    @PutMapping(value="/update-student-address/{id}")
    public ResponseEntity<StudentAddress> updateStudentAddress(@RequestBody StudentAddress studentAddress, @PathVariable long id) {
        StudentAddress studentAddress1 = studentAddressService.update(studentAddress, id);
        return new ResponseEntity<>(studentAddress1, HttpStatus.OK);
    }

    @DeleteMapping(value="/delete-student-address/{id}")
    public ResponseEntity<StudentAddress> deleteStudentAddress(@PathVariable long id) {
         studentAddressService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ////  Retrieving Specific address of students   ////

    @GetMapping(value="/student-streets")
    public ResponseEntity<List<String>> getStudentStreets() {
        return ResponseEntity.ok(studentAddressService.getStreet());
    }

    @GetMapping(value="/student-city")
    public ResponseEntity<List<String>> getStudentCity() {
        return ResponseEntity.ok(studentAddressService.getCity());
    }

    @GetMapping(value="/student-state")
    public ResponseEntity<List<String>> getStudentState() {
        return ResponseEntity.ok(studentAddressService.getState());
    }

    @GetMapping(value="/student-addresses")
    public ResponseEntity<List<String>> getStudentAddresses() {
        return ResponseEntity.ok(studentAddressService.findByStreetSate());
    }
}
