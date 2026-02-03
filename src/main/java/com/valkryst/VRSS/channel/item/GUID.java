package com.valkryst.VRSS.channel.item;

import lombok.Getter;

public class GUID {
    @Getter private final String value;
    @Getter private final boolean isPermaLink;

    public GUID(final String value) {
        this(value, false);
    }

    public GUID(final String value, final boolean isPermaLink) {
        this.value = value;
        this.isPermaLink = isPermaLink;
    }
}
