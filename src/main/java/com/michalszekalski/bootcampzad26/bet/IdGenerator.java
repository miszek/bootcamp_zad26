package com.michalszekalski.bootcampzad26.bet;

import java.time.LocalTime;

public class IdGenerator {

    public static String generate(Long id) {
        return LocalTime.now().toString().replace(":", "").substring(0, 6) + id.toString();
    }
}
