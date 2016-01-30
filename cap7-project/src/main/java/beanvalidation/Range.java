package beanvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Constraint(validatedBy = RangeValidator.class)
public @interface Range {

	long min() default Long.MIN_VALUE;

	long max() default Long.MAX_VALUE;

	String message() default RangeValidator.DEFAULT_INVALID_MESSAGE_KEY;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
