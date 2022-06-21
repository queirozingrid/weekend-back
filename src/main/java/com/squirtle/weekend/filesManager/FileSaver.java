package com.squirtle.weekend.filesManager;

import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileSaver {
    private static String path = "https://storage.googleapis.com/imagens-wknd/logos-est/";

//    @Autowired
//    private static Storage storage = StorageOptions.getDefaultInstance().getService();
//
//    public static String saveLogo(MultipartFile logo){
//        try {
//            BlobInfo blob = storage.create(
//                    BlobInfo.newBuilder("imagens-wknd", logo.getOriginalFilename()).build(),
//                    logo.getBytes(),
//                    Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ)
//            );
//            System.out.printf(blob.getMediaLink());
//            return blob.getMediaLink();
//
//        } catch (IllegalStateException | IOException e){
//            throw new RuntimeException(e);
//        }
//    }

    public FileSaver(String path) {
        this.path = path;
    }
}
