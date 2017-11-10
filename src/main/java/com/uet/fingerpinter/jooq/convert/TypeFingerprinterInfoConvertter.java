package com.uet.fingerpinter.jooq.convert;

import com.uet.fingerpinter.jooq.type.TypeFingerprinterInfo;
import org.jooq.Converter;

public class TypeFingerprinterInfoConvertter implements Converter<Byte, TypeFingerprinterInfo> {
    @Override
    public TypeFingerprinterInfo from(Byte aByte) {
        if (aByte == null) {
            return null;
        }
        return TypeFingerprinterInfo.findByValue(aByte);
    }

    @Override
    public Byte to(TypeFingerprinterInfo typeFingerprinterInfo) {
        if (typeFingerprinterInfo == null) {
            return null;
        } else {
            return (byte) typeFingerprinterInfo.getValue();
        }
    }

    @Override
    public Class<Byte> fromType() {
        return Byte.class;
    }

    @Override
    public Class<TypeFingerprinterInfo> toType() {
        return TypeFingerprinterInfo.class;
    }
}
