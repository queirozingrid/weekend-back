package com.squirtle.weekend.filesManager;

import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileSaver {

    private static String path = "https://storage.googleapis.com/imagens-wknd/logos-est/";

    public static String saveLogo(MultipartFile logo){

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credential)
                .setDatabaseUrl(projectUrl)
                .setStorageBucket("YOUR BUCKET LINK")
                .build();

        FirebaseApp fireApp = FirebaseApp.initializeApp(options);

        StorageClient storageClient = StorageClient.getInstance(fireApp);
        InputStream testFile = new FileInputStream();
        String blobString = "NEW_FOLDER/" + "FILE_NAME.EXT";

        storageClient.bucket().create(blobString, testFile , Bucket.BlobWriteOption.userProject("YOUR PROJECT ID"));


        return logo.getOriginalFilename();
    }
//
//    public FileSaver(String path) {
//        this.path = path;
//    }
}
