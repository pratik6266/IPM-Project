package com.project.Rakshak.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageUtils {
    public String encodeToBase64(byte[] bytes) {
        if (bytes == null) {
            System.out.println("No image data provided.");
            return "";
        }
        System.out.println("Encoding image of size: " + bytes.length);
        String base64 = Base64.getEncoder().encodeToString(bytes);
        System.out.println("Encoded photo size: " + base64.length());
        return base64;
    }

}
