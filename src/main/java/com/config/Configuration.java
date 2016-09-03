package com.config;

import com.logging.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

/**
 * @author Daniel
 */
public final class Configuration {

    private static final MessageDigest digest = getMessageDigest();

    public static MessageDigest getDigest() {
        return digest;
    }

    private static MessageDigest getMessageDigest() {
        try {
            return getMessageDigest(Constants.ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            Logger.log(Configuration.class, Level.SEVERE, String.format("No Such Algorithm - %s", ALGORITHM), ex);
            return null;
        }
    }

    private static MessageDigest getMessageDigest(String algorithm) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algorithm);
    }
}
