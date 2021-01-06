package com.meizhuang.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {
	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;

	public PasswordUtils() {
	}

	public static String getSalt(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; ++i) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}

		return new String(returnValue);
	}

	public static String generatePassword(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; ++i) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}

		return new String(returnValue);
	}

	public static String encryptPassword(String password) {
		return encryptPassword(password, getSalt());
	}

	public static String encryptPassword(String password, String salt) {
		if (password != null && salt != null && password.length() != 0 && salt.length() != 0) {
			String returnValue = null;

			try {
				byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.update(securePassword);
				securePassword = messageDigest.digest();
				returnValue = parseByte2HexStr(securePassword);
			} catch (NoSuchAlgorithmException var5) {
				var5.printStackTrace();
			}

			return returnValue;
		} else {
			return null;
		}
	}

	public static boolean verifyPassword(String providedPassword, String securedPassword) {
		return verifyPassword(providedPassword, securedPassword, getSalt());
	}

	public static boolean verifyPassword(String providedPassword, String securedPassword, String salt) {
		if (providedPassword != null && securedPassword != null && salt != null && providedPassword.length() != 0 && securedPassword.length() != 0 && salt.length() != 0) {
			boolean returnValue = false;
			String newSecurePassword = encryptPassword(providedPassword, salt);
			returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
			return returnValue;
		} else {
			return false;
		}
	}

	private static String getSalt() {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(ALPHABET.getBytes());
			byte[] b = messageDigest.digest();
			return parseByte2HexStr(b);
		} catch (Exception var2) {
			var2.printStackTrace();
			return null;
		}
	}

	private static byte[] hash(char[] password, byte[] salt) {
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, '\u0000');

		byte[] var5;
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			var5 = skf.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException | NoSuchAlgorithmException var8) {
			throw new AssertionError("Error while hashing a password: " + var8.getMessage(), var8);
		} finally {
			spec.clearPassword();
		}

		return var5;
	}

	private static String parseByte2HexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < buf.length; ++i) {
			String hex = Integer.toHexString(buf[i] & 255);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}

			sb.append(hex.toUpperCase());
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		String myPassword = generatePassword(8);
		System.out.println("My password = " + myPassword);
		String mySecurePassword = encryptPassword(myPassword);
		System.out.println("My secure password = " + mySecurePassword);
		boolean verifyState = verifyPassword(myPassword, mySecurePassword);
		System.out.println(verifyState);
	}
}
