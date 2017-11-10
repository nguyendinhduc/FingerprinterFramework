package com.uet.fingerpinter.jooq.type;

public enum TypeFingerprinterInfo {
    GAUSS(0);
    private int value;

    TypeFingerprinterInfo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TypeFingerprinterInfo findByValue(Byte value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case 0:
                return GAUSS;
            default:
                return null;
        }
    }
}
