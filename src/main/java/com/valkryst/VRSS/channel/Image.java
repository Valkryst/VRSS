package com.valkryst.VRSS.channel;

import com.valkryst.VRSS.builders.ImageBuilder;
import lombok.Getter;

import java.net.URL;

public class Image {
    @Getter private URL url;
    @Getter private URL link;
    @Getter private String title;
    @Getter private String description;
    @Getter private int width;
    @Getter private int height;

    public Image(final ImageBuilder builder) {
        url = builder.getUrl();
        link = builder.getLink();
        title = builder.getTitle();
        description = builder.getDescription();
        width = builder.getWidth();
        height = builder.getHeight();
    }

    @Override
    public String toString() {
        return (url == null ? "" : "\n\tURL: " + url) +
                (link == null ? "" : "\n\tLink: " + link) +
                (title == null ? "" : "\n\tTitle: " + title) +
                (description == null ? "" : "\n\tDescription: " + description) +
                (width == -1 ? "" : "\n\tWidth: " + width) +
                (height == -1 ? "" : "\n\tHeight: " + height);
    }
}
