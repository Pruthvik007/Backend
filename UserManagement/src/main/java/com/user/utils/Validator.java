package com.user.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

public class Validator {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String MOBILE_PATTERN = "^[1-9][0-9]{9}$";

	private static final String NUMERIC_ID_PATTERN = "[1-9][0-9]*";

	private static final String WITHOUT_SPACE_PATTERN = "(?i)[^ ]*";

	private static final String ALPHA_NUMERIC_PATTERN = "[a-zA-Z0-9]+";

	private static final String NAME_PATTERN = "^[a-zA-Z][a-zA-Z\\s\\.]{0,29}$";

	private static final String NUMERIC_PATTERN = "[0-9]+";

	public static final String ALPHA_PATTERN = "^[a-zA-Z]+$";

	private static final String PINCODE_PATTERN = "^[1-9][0-9]{5}$";

	private static final String ALPHA_WITH_SPACE_PATTERN = "^[a-zA-Z\\s]+$";

	private static final String AGE_PATTERN = "^(\\d?[1-9]|[1-9]0)$";

	private static final String NUMERIC_NAME_PATTERN = "^[a-zA-Z0-9][a-zA-Z0-9\\s\\.]{0,29}$";

	private static final String NAME_PATTERN_LENGTH_SIXTY = "^[a-zA-Z][a-zA-Z\\s\\.]{0,59}$";

	private static final String PAN_PATTERN = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$";

	private static final String AADHAR_PATTERN = "^[0-9]{12}$";

	private static final String PASSPORT_PATTERN = "^[0-9A-Z]{8}$";

	private static final String IFSC_CODE_PATTERN = "^[A-Z]{4}[A-Z0-9]{7}$";

	private static final String EMAIL_DOMAIN_PATTERN = "^[A-Za-z0-9\\-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final Gson gson = new Gson();

	private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	private static Matcher matcher;

	public static boolean validateString(String validateString) {
		return (validateString != null && !validateString.equalsIgnoreCase("null")
				&& validateString.trim().length() > 0);
	}

	public static boolean validatePinCode(Integer pincode) {
		if (isEmpty(pincode)) {
			return false;
		}
		return validatePinCode(pincode.toString());
	}

	public static boolean validatePinCode(String pincode) {
		return !(isEmpty(pincode) || !pincode.trim().matches(PINCODE_PATTERN));
	}

	public static boolean validateEmail(String email) {
		if (isNotEmpty(email)) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(email);
			return matcher.matches();
		} else {
			return false;
		}
	}

	public static boolean validateEmailDomain(String emailDomain) {
		if (isNotEmpty(emailDomain)) {
			pattern = Pattern.compile(EMAIL_DOMAIN_PATTERN);
			matcher = pattern.matcher(emailDomain);
			return matcher.matches();
		} else {
			return false;
		}
	}

	public static boolean validateMoblie(String mobileNumber) {
		if (isNotEmpty(mobileNumber)) {
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(mobileNumber);
			return matcher.matches();
		} else {
			return false;
		}
	}

	public static boolean validatePassword(String password) {
		return !(password == null || password.trim().contains(" ") || password.trim().length() < 6);
	}

	public static boolean validateMapFromStringMap(String stringMap) {
		if (isNotEmpty(stringMap)) {
			Map<?, ?> convertedMap = gson.fromJson(stringMap, Map.class);
			return isNotEmpty(convertedMap);
		} else {
			return false;
		}
	}

	public static boolean validateName(String name) {
		return !(isEmpty(name) || !name.matches(NAME_PATTERN));
	}

	public static boolean validateNameWithSixtyDigits(String name) {
		return !(isEmpty(name) || !name.matches(NAME_PATTERN_LENGTH_SIXTY));
	}

	/** Check whether string str is numeric. */
	public static boolean isNumericId(String str) {
		if (isEmpty(str)) {
			return false;
		}
		return str.trim().matches(NUMERIC_ID_PATTERN);
	}

	public static boolean isNumeric(String str) {
		if (isEmpty(str)) {
			return false;
		}
		return str.trim().matches(NUMERIC_PATTERN);
	}

	public static boolean isAlpha(String str) {
		if (isEmpty(str)) {
			return false;
		}
		return str.matches(ALPHA_PATTERN);
	}

	public static boolean isNumericWithLength(String str, int maxLength) {
		if (!isNumeric(str)) {
			return false;
		}
		return str.length() <= maxLength ? true : false;
	}

	/** Check whether string str is alphanumeric. */
	public static boolean isAlphaNumeric(String str) {
		if (isEmpty(str)) {
			return false;
		}
		return str.matches(ALPHA_NUMERIC_PATTERN);
	}

	public static boolean validateNumericName(String name) {
		if (isEmpty(name)) {
			return false;
		}
		return name.trim().matches(NUMERIC_NAME_PATTERN);
	}

	public static boolean isDate(Object dateToValidate, String dateFromat) {

		if (isEmpty(dateToValidate) || isEmpty(dateFromat)) {
			return false;
		}
		String dateString = "";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		if (dateToValidate instanceof Date)
			dateString = sdf.format(dateToValidate);
		else
			dateString = dateToValidate.toString();
		sdf.setLenient(false);
		try {
			sdf.parse(dateString);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * Check whether an object is NOT empty, will see if it is a String, Map,
	 * Collection, etc.
	 */

	public static boolean isEmpty(Object o) {
		return o == null;
	}

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	/** Check whether string s is empty. */
	public static boolean isEmpty(String s) {
		return ((s == null) || (s.trim().length() == 0) || ("null".equalsIgnoreCase(s)));
	}

	/** Check whether collection c is empty. */
	public static <E> boolean isEmpty(Collection<E> c) {
		return (c == null || c.isEmpty());
	}

	/** Check whether map m is empty. */
	public static <K, E> boolean isEmpty(Map<K, E> m) {
		return ((m == null) || (m.size() == 0));
	}

	/** Check whether charsequence c is empty. */
	public static boolean isEmpty(CharSequence c) {
		return ((c == null) || (c.length() == 0));
	}

	public static boolean isNotEmpty(String s) {
		return ((s != null) && (s.length() > 0) && (!"null".equalsIgnoreCase(s)));
	}

	/** Check whether collection c is NOT empty. */
	public static <E> boolean isNotEmpty(Collection<E> c) {
		return ((c != null) && !c.isEmpty());
	}

	/** Check whether charsequence c is NOT empty. */
	public static boolean isNotEmpty(CharSequence c) {
		return ((c != null) && (c.length() > 0));
	}

	public static boolean isString(Object obj) {
		return ((obj != null) && (obj instanceof java.lang.String));
	}

	public static boolean validateStringWithoutSpace(String str) {
		if (Validator.isNotEmpty(str)) {
			pattern = Pattern.compile(WITHOUT_SPACE_PATTERN);
			matcher = pattern.matcher(str);
			return matcher.matches();
		} else {
			return false;
		}
	}

	public static boolean validateStringWithLength(String str, int maxLength) {
		if (Validator.isNotEmpty(str)) {
			return str.length() <= maxLength ? true : false;
		} else {
			return false;
		}
	}

	public static boolean isAlphaWithSpace(String str) {
		if (isEmpty(str)) {
			return false;
		}
		return str.trim().matches(ALPHA_WITH_SPACE_PATTERN);
	}

	public static boolean isNotMandetoryLenthCheck(String str, int maxLength) {
		if (isEmpty(str)) {
			return true;
		}
		return str.length() <= maxLength ? true : false;
	}

	/** It will allow only 1 to 99 age limit */
	public static boolean validateAge(String str) {
		if (isEmpty(str)) {
			return false;
		}
		return str.matches(AGE_PATTERN);
	}

	public static boolean isFloat(Object obj) {
		if (isNotEmpty(obj)) {
			try {
				Float floatNumber = Float.valueOf(Float.parseFloat(obj.toString()));
				return (Float.isInfinite(floatNumber.floatValue()) || Float.isNaN(floatNumber.floatValue())) ? false
						: true;
			} catch (NumberFormatException nfe) {
				return false;
			}
		}
		return false;
	}

	public static boolean isDouble(Object obj) {
		if (isNotEmpty(obj)) {
			try {
				Double doubleNumber = Double.valueOf(Double.parseDouble(obj.toString()));
				if (Double.isInfinite(doubleNumber.doubleValue())) {
					return false;
				}
				if (Double.isNaN(doubleNumber.doubleValue())) {
					return false;
				}
				return true;
			} catch (NumberFormatException nfe) {
				return false;
			}
		}
		return false;
	}

	public static boolean isInteger(Object obj) {
		if (isNotEmpty(obj)) {
			try {
				Integer.parseInt(obj.toString());
				return true;
			} catch (NumberFormatException nfe) {
				return false;
			}
		}
		return false;
	}

	public static boolean isLong(Object obj) {
		if (isNotEmpty(obj)) {
			try {
				Long.parseLong(obj.toString());
				return true;
			} catch (NumberFormatException nfe) {
				return false;
			}
		}
		return false;
	}

	public static final boolean isDouble(Double doubleValue) {
		boolean result = false;
		if (doubleValue != null) {
			Pattern pattern = Pattern.compile("^[-+]?\\d{0,2}+(\\.{0,1}(\\d{0,4}))?$");
			Matcher matcher = pattern.matcher(doubleValue.toString());
			result = matcher.matches();
		}
		return result;
	}

	public static final boolean isFloat(String floatValue) {
		boolean result = false;
		if (Validator.isNotEmpty(floatValue)) {
			Pattern pattern = Pattern.compile("^[-+]?\\d{0,3}+(\\.{0,1}(\\d{0,2}))?$");
			Matcher matcher = pattern.matcher(floatValue);
			result = matcher.matches();
		}
		return result;
	}

	public static boolean validatePanNo(String panNo) {
		if (isEmpty(panNo)) {
			return false;
		}
		pattern = Pattern.compile(PAN_PATTERN);
		matcher = pattern.matcher(panNo.toUpperCase());
		return matcher.matches();
	}

	public static boolean validateIfscCode(String ifsc) {
		if (isEmpty(ifsc)) {
			return false;
		}
		pattern = Pattern.compile(IFSC_CODE_PATTERN);
		matcher = pattern.matcher(ifsc.toUpperCase());
		return matcher.matches();
	}

	public static boolean validateAadharNo(String aadharNo) {
		if (isEmpty(aadharNo)) {
			return false;
		}

		pattern = Pattern.compile(AADHAR_PATTERN);
		matcher = pattern.matcher(aadharNo);
		return matcher.matches();
	}

	// this just does the check of the alphanumeric with size of 8
	public static boolean validatePassportNo(String passportNo) {
		if (isEmpty(passportNo)) {
			return false;
		}

		pattern = Pattern.compile(PASSPORT_PATTERN);
		matcher = pattern.matcher(passportNo.toUpperCase());
		return matcher.matches();
	}

	public static boolean isInvalid(Number number) {
		if (number == null) {
			return true;
		} else {
			if (number instanceof Short) {
				return (Short) number <= 0;
			} else if (number instanceof Integer) {
				return (Integer) number <= 0;
			} else if (number instanceof Long) {
				return (Long) number <= 0;
			} else if (number instanceof Float) {
				return (Float) number <= 0;
			} else if (number instanceof Double) {
				return (Double) number <= 0;
			}
		}
		return false;
	}

	public static boolean isValid(Number number) {
		return !isInvalid(number);
	}
}
