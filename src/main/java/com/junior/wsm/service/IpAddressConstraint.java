package com.junior.wsm.service;

import com.junior.wsm.service.implementation.IpAddressValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Documented
@Constraint(validatedBy = IpAddressValidatorImpl.class)
public @interface IpAddressConstraint
{
    String message() default "Endereço IP Inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
