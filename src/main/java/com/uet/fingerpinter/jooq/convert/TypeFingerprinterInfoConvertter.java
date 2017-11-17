package com.uet.fingerpinter.jooq.convert;

import com.uet.fingerpinter.jooq.type.TypeFingerprinterInfo;
import org.jooq.Converter;

public class TypeFingerprinterInfoConvertter implements Converter<Integer, TypeFingerprinterInfo> {
    @Override
    public TypeFingerprinterInfo from(Integer aByte) {
        if (aByte == null) {
            return null;
        }
        return TypeFingerprinterInfo.findByValue(aByte);
    }

    @Override
    public Integer to(TypeFingerprinterInfo typeFingerprinterInfo) {
        if (typeFingerprinterInfo == null) {
            return null;
        } else {
            return (int) typeFingerprinterInfo.getValue();
        }
    }

    @Override
    public Class<Integer> fromType() {
        return Integer.class;
    }

    @Override
    public Class<TypeFingerprinterInfo> toType() {
        return TypeFingerprinterInfo.class;
    }
}
