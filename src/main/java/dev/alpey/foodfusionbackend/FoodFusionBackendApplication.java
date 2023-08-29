package dev.alpey.foodfusionbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodFusionBackendApplication {

    protected FoodFusionBackendApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(FoodFusionBackendApplication.class, args);
    }

}
