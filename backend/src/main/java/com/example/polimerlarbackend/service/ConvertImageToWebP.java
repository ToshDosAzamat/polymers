package com.example.polimerlarbackend.service;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Service
public class ConvertImageToWebP {

    public byte[] compressImage(MultipartFile file, int width, int height, float quality) throws IOException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Thumbnails.of(file.getInputStream())
                .size(width, height)
                .outputQuality(quality)
                .outputFormat("jpeg")
                .toOutputStream(outputStream);
        return outputStream.toByteArray();
    }
}