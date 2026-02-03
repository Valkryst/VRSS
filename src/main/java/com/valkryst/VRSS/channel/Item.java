package com.valkryst.VRSS.channel;

import com.valkryst.VRSS.builders.ItemBuilder;
import com.valkryst.VRSS.channel.item.Category;
import com.valkryst.VRSS.channel.item.Enclosure;
import com.valkryst.VRSS.channel.item.GUID;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDateTime;

public class Item {
    @Getter private final String title;
    @Getter private final URL link;
    @Getter private final String description;
    @Getter private final String author;
    @Getter private final Category[] categories;
    @Getter private final URL comments;
    @Getter private final Enclosure enclosure;
    @Getter private final GUID guid;
    @Getter private final LocalDateTime pubDate;
    @Getter private final String source;

    public Item(final ItemBuilder builder) {
        title = builder.getTitle();
        link = builder.getLink();
        description = builder.getDescription();
        author = builder.getAuthor();
        categories = builder.getCategories();
        comments = builder.getComments();
        enclosure = builder.getEnclosure();
        guid = builder.getGuid();
        pubDate = builder.getPubDate();
        source = builder.getSource();
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();
        sb.append(title == null ? "" : "\n\tTitle: " + title);
        sb.append(link == null ? "" : "\n\tLink: " + link);
        sb.append(description == null ? "" : "\n\tDescription: " + description);
        sb.append(author == null ? "" : "\n\tAuthor: " + author);
        sb.append("\n\tCategories:");
        for (final Category category : categories) {
            sb.append("\n\t\tCategory:");
            sb.append(category.toString().replace("\t", "\t\t"));
        }
        sb.append(comments == null ? "" : "\n\tComments: " + comments);
        sb.append(enclosure == null ? "" : "\n\tEnclosure: " + enclosure);
        sb.append(guid == null ? "" : "\n\tGUID: " + guid);
        sb.append(pubDate == null ? "" : "\n\tPublication Date: " + pubDate);
        sb.append(source == null ? "" : "\n\tSource: " + source);
        return sb.toString();
    }
}
