package com.valkryst.VRSS.builders;

import com.valkryst.VRSS.channel.Cloud;
import lombok.Getter;
import org.w3c.dom.Element;

import java.time.format.DateTimeParseException;
import java.util.Optional;

public class CloudBuilder extends RSSBuilder<Cloud> {
    @Getter private String domain;
    @Getter private int port;
    @Getter private String path;
    @Getter private String registerProcedure;
    @Getter private String protocol;

    @Override
    public Cloud build(final Element cloudElement) throws DateTimeParseException {
        reset();

        Optional.ofNullable(cloudElement.getAttribute("domain"))
                .ifPresent(value -> domain = value);
        Optional.ofNullable(cloudElement.getAttribute("port"))
                .ifPresent(value -> port = Integer.parseInt(value));
        Optional.ofNullable(cloudElement.getAttribute("path"))
                .ifPresent(value -> path = value);
        Optional.ofNullable(cloudElement.getAttribute("registerProcedure"))
                .ifPresent(value -> registerProcedure = value);
        Optional.ofNullable(cloudElement.getAttribute("protocol"))
                .ifPresent(value -> protocol = value);

        return new Cloud(this);
    }

    @Override
    public void reset() {
        domain = null;
        port = -1;
        path = null;
        registerProcedure = null;
        protocol = null;
    }
}
