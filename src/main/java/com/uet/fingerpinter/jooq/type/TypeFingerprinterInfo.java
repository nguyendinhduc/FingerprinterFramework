package com.uet.fingerpinter.jooq.type;

public enum TypeFingerprinterInfo {
    GAUSS_NORMAL(0),
    GAUSS_K_NEAREST_HISTORY(1),
    GAUSS_K_NEAREST_CENTER(2),
    GAUSS_K_NEAREST_CENTER_NEAR(3),
    NEAREST(4),
    K_NEAREST_CENTER(5),
    K_NEAREST_CENTER_NEAR(6);
    private int value;

    TypeFingerprinterInfo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TypeFingerprinterInfo findByValue(Integer value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case 0:
                return GAUSS_NORMAL;
            case 1:
                return GAUSS_K_NEAREST_HISTORY;
            case 2:
                return GAUSS_K_NEAREST_CENTER;
            case 3:
                return GAUSS_K_NEAREST_CENTER_NEAR;
            case 4:
                return NEAREST;
            case 5:
                return K_NEAREST_CENTER;
            case 6:
                return K_NEAREST_CENTER_NEAR;
            default:
                return null;
        }
    }
}
