/*
 * Copyright (C) 2015 University of Pittsburgh.
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
package edu.pitt.dbmi.ccd.commons.security;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * Oct 6, 2015 12:36:04 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class WebSecurityDSA {

    private static final String ALGORITHM = "DSA";

    private static final int KEY_SIZE = 1024;

    private WebSecurityDSA() {
    }

    public static KeyPair generateKeyPair() {
        KeyPair keyPair = null;

        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(KEY_SIZE, SecureRandom.getInstance("SHA1PRNG"));
            keyPair = keyGen.genKeyPair();
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace(System.err);
        }

        return keyPair;
    }

    public static boolean validateSignature(String message, String signedMessage, String publicKey) {
        boolean flag = false;
        try {
            Signature signature = Signature.getInstance(ALGORITHM);
            signature.initVerify(stringToPublicKey(publicKey));
            signature.update(message.getBytes());

            flag = signature.verify(Base64.getUrlDecoder().decode(signedMessage));
        } catch (InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException | SignatureException exception) {
            exception.printStackTrace(System.err);
        }

        return flag;
    }

    public static String createPlainTextSignature(String url, Map<String, String> attributeValues) {
        Set<String> orderedKeys = new TreeSet<>(attributeValues.keySet());

        StringBuilder signature = new StringBuilder(url);
        signature.append("?");
        orderedKeys.forEach(key -> {
            signature.append(key);
            signature.append("=");
            signature.append(attributeValues.get(key));
            signature.append("&");
        });
        signature.deleteCharAt(signature.length() - 1);

        return signature.toString();
    }

    public static String createSignature(String url, Map<String, String> attributeValues, String privateKey) {
        String plainText = createPlainTextSignature(url, attributeValues);

        return createSignature(plainText, privateKey);
    }

    public static String createSignature(String message, String privateKey) {
        String signature = null;

        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

            Signature sig = Signature.getInstance(ALGORITHM);
            sig.initSign(keyFactory.generatePrivate(privateKeySpec));
            sig.update(message.getBytes());

            signature = Base64.getUrlEncoder().encodeToString(sig.sign());
        } catch (InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException | SignatureException exception) {
            exception.printStackTrace(System.err);
        }

        return signature;
    }

    public static String getBase64EncodedPrivateKey(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    }

    public static String getBase64EncodedPublicKey(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }

    /**
     * Recreate PublicKey object from String.
     *
     * @param publicKey - Base64 encode public key
     * @return PublicKey object of publicKey
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    private static PublicKey stringToPublicKey(String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);

        return keyFactory.generatePublic(publicKeySpec);
    }

}
