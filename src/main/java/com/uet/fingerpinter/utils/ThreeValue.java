package com.uet.fingerpinter.utils;

public class ThreeValue<T1, T2, T3> {
    private T1 valueOne;
    private T2 valueTwo;
    private T3 valueThree;

    public ThreeValue(T1 valueOne, T2 valueTwo, T3 valueThree) {
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
        this.valueThree = valueThree;
    }

    public ThreeValue() {
    }

    public T1 getValueOne() {
        return valueOne;
    }

    public void setValueOne(T1 valueOne) {
        this.valueOne = valueOne;
    }

    public T2 getValueTwo() {
        return valueTwo;
    }

    public void setValueTwo(T2 valueTwo) {
        this.valueTwo = valueTwo;
    }

    public T3 getValueThree() {
        return valueThree;
    }

    public void setValueThree(T3 valueThree) {
        this.valueThree = valueThree;
    }
}
