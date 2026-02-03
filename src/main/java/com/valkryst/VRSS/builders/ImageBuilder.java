package com.valkryst.VRSS.builders;

import com.valkryst.VRSS.channel.Image;
import lombok.Getter;
import org.w3c.dom.Element;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.format.DateTimeParseException;

public class ImageBuilder extends RSSBuilder<Image> {
    @Getter private URL url;
    @Getter private URL link;
    @Getter private String title;
    @Getter private String description;
    @Getter private int width;
    @Getter private int height;

    @Override
    public Image build(final Element image) throws DateTimeParseException, MalformedURLException {
        reset();

        this.getNodeValueAsURL(image, "url")
            .ifPresent(value -> url = value);
        this.getNodeValueAsURL(image, "link")
            .ifPresent(value -> link = value);
        this.getNodeValueAsString(image, "title")
            .ifPresent(value -> title = value);
        this.getNodeValueAsString(image, "description")
            .ifPresent(value -> description = value);
        this.getNodeValueAsInteger(image, "width")
            .ifPresent(value -> width = value);
        this.getNodeValueAsInteger(image, "height")
            .ifPresent(value -> height = value);

        return new Image(this);
    }

    @Override
    public void reset() {
        url = null;
        link = null;
        title = null;
        description = null;
        width = -1;
        height = -1;
    }
}
