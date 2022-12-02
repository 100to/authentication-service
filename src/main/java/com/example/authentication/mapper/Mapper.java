package com.example.authentication.mapper;

import com.example.authentication.entity.User;
import com.example.authentication.payload.request.ProfileProducerRequest;
import com.example.authentication.payload.request.RegistrationRequest;
import org.mapstruct.InjectionStrategy;

@org.mapstruct.Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface Mapper {
    User map(RegistrationRequest request);

    ProfileProducerRequest map(RegistrationRequest request, Long id);
}
