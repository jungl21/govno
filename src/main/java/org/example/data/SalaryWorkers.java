package org.example.data;

import java.util.Random;

public class SalaryWorkers {
    public static double generateSalary(String job) {
        switch (job.toUpperCase()) {
            case "MANAGER" -> {
                return generateSalaryForWorker(50_000, 90_000);
            }
            case "ADMINISTRATOR" -> {
                return generateSalaryForWorker(60_000, 120_000);
            }
            case "ENGINEER" -> {
                return generateSalaryForWorker(70_000, 100_000);
            }
            case "SUPERUSER" -> {
                return generateSalaryForWorker(100_000, 200_000);
            }
            default -> {
                return 0;
            }
        }
    }

    private static double generateSalaryForWorker(int min, int max) {
        Random random = new Random();

        return  random.nextInt(max - min + 1) + min;

    }
}
