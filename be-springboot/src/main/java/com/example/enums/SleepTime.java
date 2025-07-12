package com.example.enums;

public enum SleepTime {
    VIP(300),
    REGULAR(500);

    private final int sleepDuration;

    // Constructor to assign the value to each enum constant
    SleepTime(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    // Getter to access the value of the enum constant
    public int getSleepDuration() {
        return sleepDuration;
    }
}

