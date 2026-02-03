package com.valkryst.VRSS;

import com.valkryst.VRSS.builders.ChannelBuilder;
import com.valkryst.VRSS.channel.Cloud;
import com.valkryst.VRSS.channel.Image;
import com.valkryst.VRSS.channel.Item;
import lombok.Getter;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class Channel {
    @Getter private final String title;
    @Getter private final URL link;
    @Getter private final String description;
    @Getter private final Locale language;
    @Getter private final String copyright;
    @Getter private final String managingEditor;
    @Getter private final String webMaster;
    @Getter private final LocalDateTime pubDate;
    @Getter private final LocalDateTime lastBuildDate;
    @Getter private final String category;
    @Getter private final String generator;
    @Getter private final URL docs;
    @Getter private final Cloud cloud;
    @Getter private final int ttl;
    @Getter private final Image image;
    @Getter private final String rating;
    @Getter private final int[] skipHours;
    @Getter private final DayOfWeek[] skipDays;
    @Getter private final List<Item> items;

    public Channel(final ChannelBuilder builder) {
        title = builder.getTitle();
        link = builder.getLink();
        description = builder.getDescription();
        language = builder.getLanguage();
        copyright = builder.getCopyright();
        managingEditor = builder.getManagingEditor();
        webMaster = builder.getWebMaster();
        pubDate = builder.getPubDate();
        lastBuildDate = builder.getLastBuildDate();
        category = builder.getCategory();
        generator = builder.getGenerator();
        docs = builder.getDocs();
        cloud = builder.getCloud();
        ttl = builder.getTtl();
        image = builder.getImage();
        rating = builder.getRating();
        skipHours = builder.getSkipHours();
        skipDays = builder.getSkipDays();
        items = builder.getItems();
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();
        sb.append((title == null ? "" : "\nTitle: " + title));
        sb.append((link == null ? "" : "\nLink: " + link));
        sb.append((description == null ? "" : "\nDescription: " + description));
        sb.append((language == null ? "" : "\nLanguage: " + language));
        sb.append((copyright == null ? "" : "\nCopyright: " + copyright));
        sb.append((managingEditor == null ? "" : "\nManaging Editor: " + managingEditor));
        sb.append((webMaster == null ? "" : "\nWeb Master: " + webMaster));
        sb.append((pubDate == null ? "" : "\nPublication Date: " + pubDate));
        sb.append((lastBuildDate == null ? "" : "\nLast Build Date: " + lastBuildDate));
        sb.append((category == null ? "" : "\nCategory: " + category));
        sb.append((generator == null ? "" : "\nGenerator: " + generator));
        sb.append((docs == null ? "" : "\nDocumentation: " + docs));
        sb.append((cloud == null ? "" : "\nCloud:\n\t" + cloud.toString().replace("\t", "\t\t")));
        sb.append("\nTTL: ").append(ttl);
        sb.append((image == null ? "" : "\nImage: " + image));
        sb.append((rating == null ? "" : "\nRating: " + rating));
        sb.append((skipHours == null ? "" : "\nSkip Hours: " + skipHours));
        sb.append((skipDays == null ? "" : "\nSkip Days: " + skipDays));
        sb.append("\nItems:");
        items.forEach(item -> {
            var itemString = item.toString();
            if (!itemString.isBlank()) {
                sb.append("\n\tItem:");
                sb.append(itemString.replace("\t", "\t\t"));
            }
        });
        return sb.toString();
    }
}
