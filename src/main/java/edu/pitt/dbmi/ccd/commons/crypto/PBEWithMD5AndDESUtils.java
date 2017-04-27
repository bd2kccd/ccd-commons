/*
 * Copyright (C) 2017 University of Pittsburgh.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package edu.pitt.dbmi.ccd.commons.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Password-based encryption.
 *
 * Mar 29, 2017 2:44:39 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class PBEWithMD5AndDESUtils {

    private static final String ALGORITHM = "PBEWithMD5AndDES";

    private static final String DEFAULT_SECRET_KEY = "2e535e615c4a7b2345234e242c";

    private static final byte[] DEFAULT_SALT = {
        -74, -127, 101, 86, 42, -67, -126, -77
    };

    private PBEWithMD5AndDESUtils() {
    }

    /**
     *
     * Encrypt password in clear text.
     *
     * @param clearPassword clear text
     * @param secretKey key used for encrypting and decrypting text
     * @param salt additional input used for encrypting and decrypting text
     * @return Base64 encoded encrypted text
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     */
    public static String encrypt(String clearPassword, String secretKey, byte[] salt)
            throws InvalidAlgorithmParameterException, InvalidKeyException,
            NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException, NoSuchPaddingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new PBEParameterSpec(salt, 32));
        byte[] encryptedPwd = cipher.doFinal(clearPassword.getBytes());

        return Base64.getEncoder().encodeToString(encryptedPwd);
    }

    /**
     * Encrypt password in clear text.
     *
     * @param clearPassword clear text
     * @param secretKey key used for encrypting and decrypting text
     * @return Base64 encoded encrypted text
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public static String encrypt(String clearPassword, String secretKey)
            throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException,
            InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        if (clearPassword == null || clearPassword.isEmpty()) {
            return null;
        }
        if (secretKey == null || secretKey.isEmpty()) {
            return null;
        }

        return encrypt(clearPassword, secretKey, DEFAULT_SALT);
    }

    /**
     * Encrypt password in clear text.
     *
     * @param clearPassword clear text
     * @return Base64 encoded encrypted text
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public static String encrypt(String clearPassword)
            throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException,
            InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        return encrypt(clearPassword, DEFAULT_SECRET_KEY);
    }

    /**
     * Decrypt Base64 encoded encrypted password.
     *
     * @param encryptedPassword Base64 encoded encrypted text
     * @param secretKey key used for encrypting and decrypting text
     * @param salt additional input used for encrypting and decrypting text
     * @return original clear text
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     */
    public static String decrypt(String encryptedPassword, String secretKey, byte[] salt)
            throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException,
            InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new PBEParameterSpec(salt, 32));
        byte[] decryptedPwd = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));

        return new String(decryptedPwd);
    }

    /**
     * Decrypt Base64 encoded encrypted password.
     *
     * @param encryptedPassword Base64 encoded encrypted text
     * @param secretKey key used for encrypting and decrypting text
     * @return original clear text
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public static String decrypt(String encryptedPassword, String secretKey)
            throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException,
            InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        if (encryptedPassword == null || encryptedPassword.isEmpty()) {
            return null;
        }
        if (secretKey == null || secretKey.isEmpty()) {
            return null;
        }

        return decrypt(encryptedPassword, secretKey, DEFAULT_SALT);
    }

    /**
     * Decrypt Base64 encoded encrypted password.
     *
     * @param clearPassword Base64 encoded encrypted text
     * @return original clear text
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public static String decrypt(String clearPassword)
            throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException,
            InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(clearPassword, DEFAULT_SECRET_KEY);
    }

}
