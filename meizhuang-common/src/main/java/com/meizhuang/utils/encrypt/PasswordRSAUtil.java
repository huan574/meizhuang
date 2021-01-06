package com.meizhuang.utils.encrypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class PasswordRSAUtil {

	private static RSAPublicKey publicKey;
	private static RSAPrivateKey privateKey;

	static {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			publicKey = (RSAPublicKey) keyPair.getPublic();
			privateKey = (RSAPrivateKey) keyPair.getPrivate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static RSAPublicKey getPublicKey() throws Exception {
		return publicKey;
	}

	public static String decryptByPublicKey(byte[] data, String key) throws Exception {
		byte[] privateKeyBytes = privateKey.getEncoded();
		byte[] decodedData2 = SecurityRSA.decryptByPrivateKey(data, privateKeyBytes);
		String outputStr2 = new String(decodedData2);
		return outputStr2;
	}

}
