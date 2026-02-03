package com.valkryst.VRSS.builders;

import com.valkryst.VRSS.channel.Item;
import com.valkryst.VRSS.channel.item.Category;
import com.valkryst.VRSS.channel.item.Enclosure;
import com.valkryst.VRSS.channel.item.GUID;
import lombok.Getter;
import org.w3c.dom.Element;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class ItemBuilder extends RSSBuilder<Item> {
    @Getter private String title;
    @Getter private URL link;
    @Getter private String description;
    @Getter private String author;
    @Getter private Category[] categories;
    @Getter private URL comments;
    @Getter private Enclosure enclosure;
    @Getter private GUID guid;
    @Getter private LocalDateTime pubDate;
    @Getter private String source;

    @Override
    public Item build(final Element item) throws MalformedURLException {
        reset();

        this.getNodeValueAsString(item, "title")
            .ifPresent(value -> title = value);
        this.getNodeValueAsURL(item, "link")
            .ifPresent(value -> link = value);
        this.getNodeValueAsString(item, "description")
            .ifPresent(value -> description = value);
        this.getNodeValueAsString(item, "author")
            .ifPresent(value -> author = value);
        // todo Category
        // todo Enclosure
        // todo GUID
        this.getNodeValueAsLocalDateTime(item, "pubDate")
            .ifPresent(value -> pubDate = value);
        this.getNodeValueAsString(item, "source")
            .ifPresent(value -> source = value);

        var elements = item.getElementsByTagName("category");
        categories = new Category[elements.getLength()];
        for(int i = 0 ; i < elements.getLength() ; i++) {
            final var element = (Element) elements.item(0);
            System.out.println(element.getAttribute("domain"));
            final var domain = new URL(element.getAttribute("domain"));
            final var name = element.getTextContent();
            categories[i] = new Category(domain, name);
        }

        return new Item(this);
    }

    @Override
    public void reset() {
        title = null;
        link = null;
        description = null;
        author = null;
        categories = null;
        comments = null;
        enclosure = null;
        guid = null;
        pubDate = null;
        source = null;
    }
}
