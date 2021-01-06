package com.meizhuang.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	private static final Pattern PHONE_PATTERN = Pattern.compile("^1(3|4|5|6|7|8|9)\\d{9}$");

	private boolean require = false;

	@Override
	public void initialize(Phone constraintAnnotation) {
		require = constraintAnnotation.required();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if (require) {
			return isPhone(value);
		} else {
			if (StringUtils.isEmpty(value)) {
				return true;
			} else {
				return isPhone(value);
			}
		}
	}

	public boolean isPhone(String value) {
		if (StringUtils.isEmpty(value)) {
			return false;
		}
		return PHONE_PATTERN.matcher(value).matches();
	}

}