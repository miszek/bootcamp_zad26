package com.michalszekalski.bootcamp_zad26;

import java.time.LocalTime;

public class IdGenerator {

        public static String generate(Long id) {
            return LocalTime.now().toString().replace(":", "").substring(0, 6) + id.toString();
        }
    }
