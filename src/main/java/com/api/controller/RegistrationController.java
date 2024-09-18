package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.services.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService){
        this.registrationService=registrationService;
    }
    @GetMapping
    public ResponseEntity<List<Registration>> getAllRegistrations(){
        List<Registration> registrations = registrationService.getRegistrations();
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<RegistrationDto> createRegistration(@RequestBody RegistrationDto registrationDto){
        RegistrationDto reg = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(reg,HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam Long id){
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable Long id,@RequestBody Registration registration){

        Registration updatedReg=registrationService.updateRegistration(id,registration);

        return new ResponseEntity<>(updatedReg,HttpStatus.OK);
    }
}
