package com.meizhuang.utils.encrypt;

import java.math.BigDecimal;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meizhuang.result.JsonResult;
import com.meizhuang.utils.security.NextIVForAES;

public class EncryptMoney {

	private static final Logger log = LoggerFactory.getLogger(EncryptMoney.class);

	private static final byte iv[] = new byte[16];

	private EncryptMoney() {

	}

	private static Key toKey(byte key[]) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key, "AES");
		return secretKey;
	}

	private static byte[] decrypt(byte data[], byte k[]) throws Exception {
		Key key = toKey(k);
		Security.addProvider(new BouncyCastleProvider());
		Cipher desCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
		IvParameterSpec ips = new IvParameterSpec(iv);
		desCipher.init(2, key, ips);
		return desCipher.doFinal(data);
	}

	private static byte[] encrypt(byte data[], byte k[]) throws Exception {
		Key key = toKey(k);
		Security.addProvider(new BouncyCastleProvider());
		Cipher desCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
		IvParameterSpec ips = new IvParameterSpec(iv);
		desCipher.init(1, key, ips);
		return desCipher.doFinal(data);
	}

	private static byte[] initKey(long keyDate) throws Exception {
		SecretKey myDesKey = null;
		NextIVForAES nextIVForAES = new NextIVForAES(keyDate);
		byte newKey[] = nextIVForAES.getNewKey();
		KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(newKey);
		keygenerator.init(secureRandom);
		myDesKey = keygenerator.generateKey();
		return myDesKey.getEncoded();
	}

	private static String encryptByDynamicKey(String _encryptStr, long _keyStr) throws Exception {
		if ("".equals(_encryptStr) || _encryptStr == null) {
			throw new Exception();
		} else {
			byte key[] = initKey(_keyStr);
			return Base64.encodeBase64String(encrypt(_encryptStr.getBytes(), key)).trim();
		}
	}

	private static String decryptByDynamicKey(String _decryptStr, long _keyStr) throws Exception {
		if ("".equals(_decryptStr) || _decryptStr == null) {
			throw new Exception();
		} else {
			byte inputData[] = Base64.decodeBase64(_decryptStr);
			byte key[] = initKey(_keyStr);
			byte outputData[] = decrypt(inputData, key);
			return new String(outputData);
		}
	}

	private static String bytesToHexString(byte src[]) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0)
			return null;
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 255;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2)
				stringBuilder.append(0);
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	private static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char hexChars[] = hexString.toCharArray();
		byte d[] = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static JsonResult<Money> encyptMoney(BigDecimal money, String keyData) {
		if (keyData == null || "".equals(keyData)) {
			return JsonResult.buildError("参数为空");
		}
		keyData = keyData.toLowerCase();
		long tableKey = System.currentTimeMillis();
		String encMoneyStr;
		String encTableStr;
		String encMoneyHexStr;
		String encTableKeyHexStr;
		String checkCode;
		try {
			encMoneyStr = encryptByDynamicKey(money.toPlainString(), tableKey);
			encTableStr = encryptByDynamicKey(String.valueOf(tableKey), 48789789787589787L);
			if (encMoneyStr == null || "".equals(encMoneyStr)) {
				return JsonResult.buildError("加密失败");
			}
		} catch (Exception e) {
			log.info(e.toString());
			return JsonResult.buildError("加密出现异常");
		}
		encMoneyHexStr = bytesToHexString(encMoneyStr.getBytes()).toUpperCase();
		encTableKeyHexStr = bytesToHexString(encTableStr.getBytes()).toUpperCase();
		if (encMoneyHexStr == null || "".equals(encMoneyHexStr)) {
			return JsonResult.buildError("加密失败");
		}
		if (encMoneyHexStr.length() != 48 && encMoneyHexStr.length() != 88) {
			return JsonResult.buildError("加密失败");
		}
		checkCode = MD5.MD5EncodeUTF8((new StringBuilder(String.valueOf(encMoneyHexStr))).append(keyData).append(encTableKeyHexStr).toString()).toUpperCase();
		return JsonResult.buildSuccess(new Money(money, keyData, encMoneyHexStr, checkCode, String.valueOf(encTableKeyHexStr)));
	}

	public static JsonResult<Money> decryptMoney(String encMoney, String keyData, String checkCode, String encTableKeyHexStr) {
		if (encMoney == null || "".equals(encMoney) || encTableKeyHexStr == null || "".equals(encTableKeyHexStr) || checkCode == null || "".equals(checkCode)) {
			return JsonResult.buildError("参数为空");
		}
		keyData = keyData.toLowerCase();
		if (encMoney.length() != 48 && encMoney.length() != 88 || checkCode.length() != 32) {
			return JsonResult.buildError("解密失败");
		}
		String nowCheckCode = MD5.MD5EncodeUTF8((new StringBuilder(String.valueOf(encMoney))).append(keyData).append(encTableKeyHexStr).toString()).toUpperCase();
		if (!checkCode.equals(nowCheckCode)) {
			return JsonResult.buildError("解密失败");
		}
		String encMoneyStrBase64;
		String tableStrBase64;
		String tableKey;
		String decrtyPtMoney;
		try {
			encMoneyStrBase64 = new String(hexStringToBytes(encMoney));
			tableStrBase64 = new String(hexStringToBytes(encTableKeyHexStr));
			if (encMoneyStrBase64 == null || "".equals(encMoneyStrBase64)) {
				return JsonResult.buildError("解密失败");
			}
			if (tableStrBase64 == null || "".equals(tableStrBase64)) {
				return JsonResult.buildError("解密失败");
			}
			tableKey = decryptByDynamicKey(tableStrBase64, 48789789787589787L);
			decrtyPtMoney = decryptByDynamicKey(encMoneyStrBase64, Long.valueOf(tableKey).longValue());
			if (decrtyPtMoney == null || "".equals(decrtyPtMoney)) {
				return JsonResult.buildError("解密失败");
			}
		} catch (Exception e) {
			log.info(e.toString());
			return JsonResult.buildError("解密出现异常");
		}
		BigDecimal money = null;
		try {
			money = new BigDecimal(decrtyPtMoney);
		} catch (NumberFormatException e) {
			return JsonResult.buildError("解密出金额" + decrtyPtMoney + "格式错误");
		}
		return JsonResult.buildSuccess(new Money(money, keyData));
	}

	public static void main(String[] args) {
		String keyData = "10000";
		JsonResult<Money> result = EncryptMoney.encyptMoney(new BigDecimal("0"), keyData);
		if (result.isSuccess()) {
			Money money = result.getData();
			System.out.println(money);
			System.out.println("EncMoney Length：" + money.getEncMoney());
			System.out.println("CheckCode Length：" + money.getCheckCode());
			System.out.println("TableKey Length：" + money.getTableKey());

			result = EncryptMoney.decryptMoney(money.getEncMoney(), keyData, money.getCheckCode(), money.getTableKey());
			if (result.isSuccess()) {
				System.out.println(result.getData().getMoney().toPlainString());
			} else {
				System.out.println(result.getMsg());
			}
		} else {
			System.out.println(result.getMsg());
		}
	}

}
