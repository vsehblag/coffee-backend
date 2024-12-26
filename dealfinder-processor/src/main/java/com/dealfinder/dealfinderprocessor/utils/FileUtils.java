package com.dealfinder.dealfinderprocessor.utils;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.generation.BarcodeGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static byte[] readPictureFromPath(String path){
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveQrWithEncodedUrl(String url, String filePath) {
        try {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, url);
            generator.getParameters().setResolution(400);
            generator.save(filePath);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
