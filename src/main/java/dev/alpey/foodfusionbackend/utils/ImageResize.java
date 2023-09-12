package dev.alpey.foodfusionbackend.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

@Component
public final class ImageResize {

    private static final int NEW_HEIGHT = 800;

    private ImageResize() {
    }

    @SneakyThrows
    public static byte[] resizeImage(byte[] imageData) {
        if (imageData == null) {
            throw new IllegalArgumentException("Image data is null.");
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
        BufferedImage originalImage = ImageIO.read(inputStream);

        if (originalImage == null) {
            throw new IOException("Unable to read the image.");
        }

        int newWidth = (int) ((double) originalImage.getWidth() / originalImage.getHeight() * NEW_HEIGHT);

        BufferedImage resizedImage = new BufferedImage(newWidth, NEW_HEIGHT, originalImage.getType());

        resizedImage.createGraphics().drawImage(originalImage, 0, 0, newWidth, NEW_HEIGHT, null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", outputStream);

        return outputStream.toByteArray();
    }
}
