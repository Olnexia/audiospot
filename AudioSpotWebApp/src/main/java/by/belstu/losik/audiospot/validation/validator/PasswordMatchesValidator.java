package by.belstu.losik.audiospot.validation.validator;

import by.belstu.losik.audiospot.validation.annotation.PasswordMatches;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
       throw new NotImplementedException();
    }
}
