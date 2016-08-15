package com.config;

import com.io.Stream;

/**
 * @author Daniel
 */
public final class Configuration {

    private static final Stream fileStream = new Stream();

    private static final Stream urlStream = new Stream();

    public static Stream getFileStream() {
        return fileStream;
    }

    public static Stream getUrlStream() {
        return urlStream;
    }
}
