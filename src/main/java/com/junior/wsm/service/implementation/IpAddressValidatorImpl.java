package com.junior.wsm.service.implementation;

import com.junior.wsm.repository.ServidorRepository;
import com.junior.wsm.service.IpAddressConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.google.common.net.InetAddresses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IpAddressValidatorImpl implements ConstraintValidator<IpAddressConstraint, String> {

    private final ServidorRepository servidorRepository;

    @Override
    public void initialize(IpAddressConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String enderecoIp, ConstraintValidatorContext cvContext) {
        return InetAddresses.isInetAddress(enderecoIp) && servidorRepository.findByEnderecoIp(enderecoIp) == null;
    }
}
