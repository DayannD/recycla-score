package com.simplon.recyclascore.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class Utils {
  public static String getUniqueName(String name) {
    String newName = name.replaceAll("\\s+", "-").toLowerCase();

    return  UUID.randomUUID().toString() + "-" + newName;
  }

  public static File convertMultiPartToFile(MultipartFile file) throws IOException {
    File convFile = new File(file.getOriginalFilename());
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(file.getBytes());
    fos.close();
    return convFile;
  }
}
