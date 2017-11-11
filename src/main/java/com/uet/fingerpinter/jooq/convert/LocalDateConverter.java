//package com.uet.fingerpinter.jooq.convert;
//
//import org.jooq.Converter;
//
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//
//public class LocalDateConverter implements Converter<Timestamp, LocalDate>{
//    @Override
//    public LocalDate from(Timestamp timestamp) {
//        if (timestamp != null) {
//            Date date = Date.from(timestamp.toLocalDateTime().atZone(ZoneId.systemDefault()).toInstant());
//            return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
//        }
//        return null;
//    }
//
//    @Override
//    public Timestamp to(LocalDate localDate) {
//        return null;
//    }
//
//    @Override
//    public Class<Timestamp> fromType() {
//        return null;
//    }
//
//    @Override
//    public Class<LocalDate> toType() {
//        return null;
//    }
//}
