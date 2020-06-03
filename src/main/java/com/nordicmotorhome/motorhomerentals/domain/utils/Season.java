package com.nordicmotorhome.motorhomerentals.domain.utils;

import java.time.LocalDate;
import java.time.Month;

//Author : RAP
public enum Season {
    PEAK {
        public double getMult() {
            return 1.6;
        }
    },
    MIDDLE {
        public double getMult() {
            return 1.3;
        }
    },
    LOW {
        public double getMult() {
            return 1;
        }
    };

    public static Season getSeason(LocalDate startDate) {
        Month month = startDate.getMonth();

        if (month.equals(Month.JANUARY) || month.equals(Month.FEBRUARY) || month.equals(Month.MARCH)) {
            return MIDDLE;
        }
        else if (month.equals(Month.APRIL) ||month.equals(Month.MAY) || month.equals(Month.JUNE) ||
                month.equals(Month.JULY) || month.equals(Month.AUGUST) || month.equals(Month.SEPTEMBER)) {
            return PEAK;
        }
        else {
            return LOW;
        }
    }

    public abstract double getMult();
}
