package com.data;

import com.config.Configuration;
import com.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.logging.Level;

/**
 * @author Daniel
 */
public final class StreamHash {

    public static String getStreamHash(InputStream stream) {
        if (stream == null) {
            return null;
        }
        try {
            return checkStream(stream);
        } catch (IOException ex) {
            Logger.log(StreamHash.class, Level.WARNING, "Error getting stream", ex);
        }
        return null;
    }

    private static byte[] getBytes(final InputStream stream) throws IOException {
        final MessageDigest digest = Configuration.getDigest();
        int value;
        final byte[] buffer = new byte[128];
        while ((value = stream.read(buffer)) != -1 && value > 0) {
            digest.update(buffer, 0, value);
        }
        return digest.digest();
    }

    private static String checkStream(final InputStream stream) throws IOException {
        final byte[] bytes = getBytes(stream);
        StringBuilder result = new StringBuilder();
        for (byte array : bytes) {
            result.append(Integer.toString((array & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
