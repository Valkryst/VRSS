package com.valkryst.VRSS.channel.item;

import lombok.Getter;

import java.net.URL;

public class Category {
    @Getter private final URL domain;
    @Getter private final String name;

    public Category(final URL domain, final String name) {
        this.domain = domain;
        this.name = name;
    }

    @Override
    public String toString() {
        return (domain == null ? "" : "\n\tDomain: " + domain) +
                (name == null ? "" : "\n\tName: " + name);
    }
}
