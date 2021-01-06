package com.meizhuang.utils.redis;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.meizhuang.result.JsonResult;
import com.meizhuang.utils.encrypt.Base64Local;
import com.meizhuang.utils.encrypt.SecurityRSA;

public class RedisRSAUtil {
	
	private static Logger logger = Logger.getLogger(RedisRSAUtil.class);
	
	public static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJKjqpxCtViiW1wIWQO9GYe9+BWeGRw1jeJS11bIzB8uJmLgyzJQ4XkQkQh4X3D2FKLspOtinGNTDvNwHeKGpddLvLWUm/di60k8o70kO/bQPwavWfKQ/nmcUVx/CmCODd5fyRhb3GvdbjfHLXf+iE0JbUARUm1Y+KLeh2R9nLUlAgMBAAECgYANG3gKk6PmEk0cQtrCDMsVaROdyw3viZHvIvyAk147g0KHIBTJoJUm3yY3nZraXASoM4LiT2QvNKNneyQQtXxpSXylgWe2oDCy5ZNwaI7flWUEmepzmFFnn//9EbH5GMSlfTtrpFMdNTybIk5HBwzCGsSdI8X93+BAsgAAk9zoWQJBAMsqrcuZ0ONgJFepJgNFLdbhGegKKrkvBzzXDZjIEuypbz5rweM8eVg/NLUI5cVBhRDYChzUOCcjqQ54pemNopcCQQC4xdD7YtDC31ocM8B4T1Eia2KOMTYcSilUvoW213atSkoqJwXl2BRqtYHZ4bznvWwh5Hz+7ojYEI8VgvayPCmjAkEAqm/EWtBP99+YvV1uQUIQwXoXpJya2L2GIdj7VBdMIPPHGiOGBR6fYZOsoM61CzT673V9jk/OR69XzIcUEIk8QwJARVbm8A/LK/ZJiJ/bMMsDNZl66MWK+t7R192PrGf7P1jLsDnourJ2Jv1qd2uBSq4JMEbgqYK2k+7S4028anKtZQJAfKfo5baTbQXWSbkpwpKx8IRpZ6PAbIJc8DZKNNbDplkNeuTFkQzTF8hX9JE67+DTvttWcH+MIpSuoTqHtoEtEg==";
	public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSo6qcQrVYoltcCFkDvRmHvfgVnhkcNY3iUtdWyMwfLiZi4MsyUOF5EJEIeF9w9hSi7KTrYpxjUw7zcB3ihqXXS7y1lJv3YutJPKO9JDv20D8Gr1nykP55nFFcfwpgjg3eX8kYW9xr3W43xy13/ohNCW1AEVJtWPii3odkfZy1JQIDAQAB";

	public static String encrypt(String value) {
		try {
			byte by[] = SecurityRSA.encryptByPublicKey(value.getBytes("UTF-8"), Base64Local.decode(publicKey));
			return Base64Local.encodeToString(by, true);
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
			return null;
		}
	}

	public static String decrypt(String value) {
		try {
			return new String(SecurityRSA.decryptByPrivateKey(Base64Local.decode(value), Base64Local.decode(privateKey)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
			return null;
		}
	}

	public static JsonResult<Object> set(String key, String value, int seconds) {
		return RedisUtil.set(key, encrypt(value), seconds);
	}

	public static JsonResult<Object> get(String key) {
		JsonResult<Object> result = RedisUtil.get(key);
		if (!result.isSuccess()) {
			return result;
		}
		result.setData(decrypt(String.valueOf(result.getData())));
		return result;
	}

}
