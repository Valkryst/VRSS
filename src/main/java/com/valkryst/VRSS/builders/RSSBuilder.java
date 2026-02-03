package com.valkryst.VRSS.builders;

import org.w3c.dom.Element;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;

public abstract class RSSBuilder<T> {
    private final DateTimeFormatter[] dateTimeFormatters = new DateTimeFormatter[] {
        DateTimeFormatter.RFC_1123_DATE_TIME,
        DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm z"),
        DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss z")
    };

    public abstract T build(final Element rssElement) throws DateTimeParseException, MalformedURLException;

    /** Resets the builder to its default state. */
    public abstract void reset();

    protected Optional<Integer> getNodeValueAsInteger(final Element parent, final String tag) {
        final var value = getNodeValueAsString(parent, tag);
        if (value.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(Integer.parseInt(value.get()));
    }

    protected Optional<LocalDateTime> getNodeValueAsLocalDateTime(final Element parent, final String tag) throws DateTimeParseException {
        final var value = getNodeValueAsString(parent, tag);
        if (value.isEmpty()) {
            return Optional.empty();
        }

        final var timestamp = value.get();
        for (final var dateTimeFormatter : dateTimeFormatters) {
            try {
                final var parsedDateTime = dateTimeFormatter.parse(timestamp);
                final var localDateTime = LocalDateTime.from(parsedDateTime);
                return Optional.of(localDateTime);
            } catch(final DateTimeParseException ignored) {}
        }

        throw new DateTimeParseException("The timestamp could not be parsed by any of the existing DateTimeFormatters.", timestamp, 0);
    }

    protected Optional<Locale> getNodeValueAsLocale(final Element parent, final String tag) {
        final var value = getNodeValueAsString(parent, tag);
        if (value.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(Locale.forLanguageTag(value.get()));
    }

    protected Optional<String> getNodeValueAsString(final Element parent, final String tag) {
        final var elements = parent.getElementsByTagName(tag);
        if (elements.getLength() == 0) {
            return Optional.empty();
        }

        return Optional.ofNullable(elements.item(0).getTextContent());
    }

    protected Optional<URL> getNodeValueAsURL(final Element parent, final String tag) throws MalformedURLException {
        final var value = getNodeValueAsString(parent, tag);
        if (value.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new URL(value.get()));
    }
}
