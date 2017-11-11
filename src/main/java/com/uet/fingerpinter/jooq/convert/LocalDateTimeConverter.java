package com.uet.fingerpinter.jooq.convert;

import org.jooq.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateTimeConverter implements Converter<Timestamp, LocalDateTime>{
    @Override
    public LocalDateTime from(Timestamp timestamp) {
        if (timestamp != null) {
            Date date = Date.from(timestamp.toLocalDateTime().atZone(ZoneId.systemDefault()).toInstant());
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }
        return null;
    }

    @Override
    public Timestamp to(LocalDateTime localDateTime) {
        if ( localDateTime != null ) {
            Timestamp ts = Timestamp.valueOf(localDateTime);
            return ts;
        }
        return null;
    }

    @Override
    public Class<Timestamp> fromType() {
        return Timestamp.class;
    }

    @Override
    public Class<LocalDateTime> toType() {
        return LocalDateTime.class;
    }
}
