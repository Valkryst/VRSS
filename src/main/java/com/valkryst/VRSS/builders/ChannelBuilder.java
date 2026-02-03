package com.valkryst.VRSS.builders;

import com.valkryst.VRSS.Channel;
import com.valkryst.VRSS.RSSFormatException;
import com.valkryst.VRSS.channel.Cloud;
import com.valkryst.VRSS.channel.Image;
import com.valkryst.VRSS.channel.Item;
import lombok.Getter;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChannelBuilder extends RSSBuilder<Channel> {
    @Getter private String title;
    @Getter private URL link;
    @Getter private String description;
    @Getter private Locale language;
    @Getter private String copyright;
    @Getter private String managingEditor;
    @Getter private String webMaster;
    @Getter private LocalDateTime pubDate;
    @Getter private LocalDateTime lastBuildDate;
    @Getter private String category;
    @Getter private String generator;
    @Getter private URL docs;
    @Getter private Cloud cloud;
    @Getter private int ttl;
    @Getter private Image image;
    @Getter private String rating;
    @Getter private int[] skipHours;
    @Getter private DayOfWeek[] skipDays;
    @Getter private List<Item> items;

    public Channel build(final InputStream inputStream) throws RSSFormatException, ParserConfigurationException, IOException, SAXException {
        final var factory = DocumentBuilderFactory.newInstance();
        final var builder = factory.newDocumentBuilder();
        final var document = builder.parse(inputStream);
        final var rssElements = document.getElementsByTagName("rss");

        if (rssElements.getLength() == 0) {
            throw new RSSFormatException("Unable to find the <rss> element.");
        } else if (rssElements.getLength() > 1) {
            throw new RSSFormatException("There is more than one <rss> element.");
        }

        return build((Element) rssElements.item(0));
    }

    @Override
    public Channel build(final Element rssElement) throws MalformedURLException {
        reset();

        this.getNodeValueAsString(rssElement, "title")
            .ifPresent(value -> title = value);
        this.getNodeValueAsURL(rssElement, "link")
            .ifPresent(value -> link = value);
        this.getNodeValueAsString(rssElement, "description")
            .ifPresent(value -> description = value);
        this.getNodeValueAsLocale(rssElement, "language")
            .ifPresent(value -> language = value);
        this.getNodeValueAsString(rssElement, "copyright")
            .ifPresent(value -> copyright = value);
        this.getNodeValueAsString(rssElement, "managingEditor")
            .ifPresent(value -> managingEditor = value);
        this.getNodeValueAsString(rssElement, "webMaster")
            .ifPresent(value -> webMaster = value);
        this.getNodeValueAsLocalDateTime(rssElement, "pubDate")
            .ifPresent(value -> pubDate = value);
        this.getNodeValueAsLocalDateTime(rssElement, "lastBuildDate")
            .ifPresent(value -> lastBuildDate = value);
        this.getNodeValueAsString(rssElement, "category")
            .ifPresent(value -> category = value);
        this.getNodeValueAsString(rssElement, "generator")
            .ifPresent(value -> generator = value);
        this.getNodeValueAsURL(rssElement, "docs")
            .ifPresent(value -> docs = value);
        this.getNodeValueAsInteger(rssElement, "ttl")
            .ifPresent(value -> ttl = value);
        this.getNodeValueAsString(rssElement, "rating")
            .ifPresent(value -> rating = value);

        var elements = rssElement.getElementsByTagName("cloud");
        if (elements.getLength() == 1) {
            cloud = new CloudBuilder().build((Element) elements.item(0));
        }

        elements = rssElement.getElementsByTagName("image");
        if (elements.getLength() == 1) {
            image = new ImageBuilder().build((Element) elements.item(0));
        }

        elements = rssElement.getElementsByTagName("skipHours");
        if (elements.getLength() == 1) {
            elements = ((Element) elements.item(0)).getElementsByTagName("hour");

            skipHours = new int[elements.getLength()];
            for (int i = 0; i < elements.getLength(); i++) {
                final var element = (Element) elements.item(i);
                skipHours[i] = Integer.parseInt(element.getTextContent());
            }
        }

        elements = rssElement.getElementsByTagName("skipDays");
        if (elements.getLength() == 1) {
            elements = ((Element) elements.item(0)).getElementsByTagName("hour");

            skipDays = new DayOfWeek[elements.getLength()];
            for (int i = 0; i < elements.getLength(); i++) {
                final var element = (Element) elements.item(i);
                skipDays[i] = DayOfWeek.valueOf(element.getTextContent());
            }
        }

        final var itemElements = rssElement.getElementsByTagName("item");
        final var itemBuilder = new ItemBuilder();
        for (int i = 0 ; i < itemElements.getLength() ; i++)  {
            items.add(itemBuilder.build((Element) itemElements.item(i)));
        }

        return new Channel(this);
    }

    @Override
    public void reset() {
        title = null;
        link = null;
        description = null;
        language = null;
        copyright = null;
        managingEditor = null;
        webMaster = null;
        pubDate = null;
        lastBuildDate = null;
        category = null;
        generator = null;
        docs = null;
        cloud = null;
        ttl = -1;
        image = null;
        rating = null;
        skipHours = null;
        skipDays = null;
        items = new ArrayList<>();
    }
}
