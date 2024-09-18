package com.api.services;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper){
        this.registrationRepository=registrationRepository;
        this.modelMapper=modelMapper;
    }

    public List<Registration> getRegistrations(){
        List<Registration> registrations=registrationRepository.findAll();
        return registrations;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {

        //Copy dto to entity
        Registration registration = mapToEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);

        // Copy entity to dto
        RegistrationDto dto = mapToDto(savedEntity);
        return dto;
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

    Registration mapToEntity(RegistrationDto registrationDto){

        Registration registration=new Registration();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());
        return registration;
    }
    RegistrationDto mapToDto(Registration registration){

        RegistrationDto dto=new RegistrationDto();
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        return dto;
    }
}
