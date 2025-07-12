package com.example.services.util;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class RandomFourDigitGenerationService {
    private static final Random random = new Random();
    private final Set<Integer> generatedNumbers = new HashSet<>();

    public int generateUniqueRandomFourDigit() {
        int number;

        do {
            number = 1000 + random.nextInt(9000); // Generate a number between 1000 and 9999
        } while (generatedNumbers.contains(number)); // Ensure uniqueness

        generatedNumbers.add(number);

        return number;
    }
}
