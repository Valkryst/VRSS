package com.valkryst.VRSS;

public class RSSVersionException extends Exception {
    public RSSVersionException(final String version) {
        super("RSS Version '" + version + "' is not supported.");
    }
}
