package com.valkryst.VRSS.channel;

import com.valkryst.VRSS.builders.CloudBuilder;
import lombok.Getter;

public class Cloud {
    @Getter private final String domain;
    @Getter private final int port;
    @Getter private final String path;
    @Getter private final String registerProcedure;
    @Getter private final String protocol;

    public Cloud(final CloudBuilder builder) {
        domain = builder.getDomain();
        port = builder.getPort();
        path = builder.getPath();
        registerProcedure = builder.getRegisterProcedure();
        protocol = builder.getProtocol();
    }

    @Override
    public String toString() {
        return (domain == null ? "" : "\n\tDomain: " + domain) +
                (port != -1 ? "" : "\n\tPort: " + port) +
                (path == null ? "" : "\n\tPath: " + path) +
                (registerProcedure == null ? "" : "\n\tRegister Procedure: " + registerProcedure) +
                (protocol == null ? "" : "\n\tProtocol: " + protocol);
    }
}
