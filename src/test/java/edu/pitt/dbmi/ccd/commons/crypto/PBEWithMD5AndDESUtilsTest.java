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
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Mar 29, 2017 3:51:42 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class PBEWithMD5AndDESUtilsTest {

    public PBEWithMD5AndDESUtilsTest() {
    }

    /**
     * Test of encrypt method, of class PBEWithMD5AndDES.
     *
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    @Test
    public void testEncrypt()
            throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException,
            InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        String clearPassword = "thisisapassword";
        String secretKey = "This is a test!";
        String expResult = "7RxXRH6VuwIPPkpbcDaTxw==";
        String result = PBEWithMD5AndDESUtils.encrypt(clearPassword, secretKey);

        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of decrypt method, of class PBEWithMD5AndDES.
     *
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    @Test
    public void testDecrypt()
            throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException,
            InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        String encryptedPassword = "7RxXRH6VuwIPPkpbcDaTxw==";
        String secretKey = "This is a test!";
        String expResult = "thisisapassword";
        String result = PBEWithMD5AndDESUtils.decrypt(encryptedPassword, secretKey);

        Assert.assertEquals(expResult, result);
    }

}
