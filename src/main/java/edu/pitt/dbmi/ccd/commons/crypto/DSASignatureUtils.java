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

/**
 *
 * Mar 29, 2017 5:29:00 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class DSASignatureUtils {

    private static final String ALGORITHM = "DSA";

    private DSASignatureUtils() {
    }

    public static boolean validateMessageSignature(String message, String signedMessage, String publicKey) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, SignatureException {
        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initVerify(stringToPublicKey(publicKey));
        signature.update(message.getBytes());

        return signature.verify(Base64.getUrlDecoder().decode(signedMessage));
    }

    public static String createMessageSignature(String message, String privateKey) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, SignatureException {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        Signature sig = Signature.getInstance(ALGORITHM);
        sig.initSign(keyFactory.generatePrivate(privateKeySpec));
        sig.update(message.getBytes());

        return Base64.getUrlEncoder().encodeToString(sig.sign());
    }

    /**
     * Create PublicKey object from String.
     *
     * @param publicKey public key string
     * @return PublicKey object
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    private static PublicKey stringToPublicKey(String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);

        return keyFactory.generatePublic(publicKeySpec);
    }

    public static String getBase64EncodedPrivateKey(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    }

    public static String getBase64EncodedPublicKey(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }

    public static KeyPair generateKeyPair(int keysize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(keysize, SecureRandom.getInstance("SHA1PRNG"));

        return keyGen.genKeyPair();
    }

}
