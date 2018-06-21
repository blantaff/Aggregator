package com.ag.utilities;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encryption {

	private static Logger logger = Logger.getLogger(Encryption.class);
	public static SecretKey skey;
	public static String strKey = "f8t769U4C0EG2WUG";

	/**
	 * This method will return an encrypted string
	 * @param enctext
	 * @return String
	 */
	public static String encrypt(String enctext) {
		String returnString = null;
		BASE64Encoder b64encode = new BASE64Encoder();

		try {
			byte[] raw = strKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			returnString = b64encode.encode(cipher.doFinal(enctext.getBytes()));

		} catch (Exception e) {
			logger.error("Error occured during encryption", e);
		}
		return returnString;
	}

	/**
	 * This method will return a decrypted string
	 * @param dectext
	 * @return String
	 */
	public static String decrypt(String dectext) {
		String returnString = null;
		BASE64Decoder b64decode = new BASE64Decoder();

		try {
			byte[] raw = strKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			returnString = new String(cipher.doFinal(b64decode
					.decodeBuffer(dectext)));
		} catch (Exception e) {
			logger.error("Error occured during decryption", e);
		}
		return returnString;
	}
}