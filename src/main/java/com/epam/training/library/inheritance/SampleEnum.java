package com.epam.training.library.inheritance;

public enum SampleEnum {
    winter(100), autumn(100), summer(200), spring(1);

    int days;

    private SampleEnum(int days) {
        this.days = days;
    }

    public int getLength() {
        return days;
    }
}
