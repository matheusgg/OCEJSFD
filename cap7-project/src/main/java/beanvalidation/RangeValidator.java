package beanvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<Range, Long> {

	public static final String DEFAULT_INVALID_MESSAGE_KEY = "{bv.RangeValidator}";
	private Long min;
	private Long max;
	private String message;

	@Override
	public void initialize(Range constraintAnnotation) {
		this.min = constraintAnnotation.min();
		this.max = constraintAnnotation.max();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		if (value < this.min || value > this.max) {
			this.generateViolationMessage(context);
			return false;
		}
		return true;
	}

	private void generateViolationMessage(ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();
	}

}
