package com.api.services;

import com.api.RegistrationApplication;
import com.api.entity.Registration;
import com.api.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository){
        this.registrationRepository=registrationRepository;
    }

    public List<Registration> getRegistrations(){
        List<Registration> registrations=registrationRepository.findAll();
        return registrations;
    }

    public Registration createRegistration(Registration registration) {

        Registration savedEntity = registrationRepository.save(registration);

        return savedEntity;
    }

    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(Long id, Registration registration) {
        Registration r = registrationRepository.findById(id).get();
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration savedEntity = registrationRepository.save(r);

        return savedEntity;
    }
}
