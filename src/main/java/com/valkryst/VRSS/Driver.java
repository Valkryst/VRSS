package com.valkryst.VRSS;

import com.valkryst.VRSS.builders.ChannelBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class Driver {
    public static void main(final String[] args) throws IOException, SAXException, ParserConfigurationException, RSSFormatException {
        final URL[] feedURLs = new URL[] {
            new URL("https://cyber.harvard.edu/rss/examples/sampleRss092.xml"),
            new URL("https://www.feedforall.com/sample.xml"),
            new URL("https://www.feedforall.com/sample-feed.xml"),
            new URL("https://www.feedforall.com/blog-feed.xml"),
            new URL("http://www.rss-specifications.com/blog-feed.xml"),
            new URL("https://www.nasa.gov/rss/dyn/breaking_news.rss"),
            new URL("https://rss.cbc.ca/lineup/topstories.xml"),
            new URL("https://rss.cbc.ca/lineup/canada-newbrunswick.xml"),
            new URL("http://feeds.bbci.co.uk/news/world/rss.xml"),
            new URL("http://feeds.reuters.com/Reuters/worldNews")
        };

        // Parse all feeds in an attempt to cause exceptions in the code.
        final var channelBuilder = new ChannelBuilder();
//        for (final URL url : feedURLs) {
//            System.out.println(url);
//            final var channel = channelBuilder.build(url.openStream());
//        }

        System.out.println(channelBuilder.build(feedURLs[feedURLs.length - 1].openStream()));
    }
}
