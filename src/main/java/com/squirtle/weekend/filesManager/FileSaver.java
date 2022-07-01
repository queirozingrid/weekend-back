package com.squirtle.weekend.filesManager;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileSaver {

    public static void writeLogo(MultipartFile file, String cnpj) throws IOException {
        File newFile = multipartToFile(file);
        String directory = "logos-est";
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("./serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("wknd-back.appspot.com")
                    .build();

            FirebaseApp fireApp = FirebaseApp.initializeApp(options);

            StorageClient storageClient = StorageClient.getInstance(fireApp);

            InputStream testFile = new FileInputStream(newFile);
            String blobString = directory + "/" + cnpj + "-" + newFile.getName();

            Blob arquivo = storageClient.bucket().create(blobString, testFile);

            System.out.println("link: " + arquivo.getMediaLink());
            System.out.println("link: " + arquivo.getSelfLink());
            System.out.println("link: " + arquivo.getGeneration());
            System.out.println("link: " + arquivo.getGeneratedId());
            System.out.println("link: " + arquivo.getBucket());
            System.out.println("link: " + arquivo.getBlobId());
            System.out.println("link: " + arquivo.getBlobId().toGsUtilUri());
            System.out.println("link: " + arquivo.getBlobId().getGeneration());
            System.out.println("LINK: " + fireApp.getOptions().getDatabaseUrl());
            System.out.println("link: " + fireApp.getOptions().getStorageBucket());
            System.out.println("link: " + storageClient.bucket().getIamConfiguration().getPublicAccessPrevention());
            System.out.println("link: " + storageClient.bucket().getSelfLink());
            Bucket bucket = storageClient.bucket();
            System.out.println(bucket.get(arquivo.getName()));



        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
}
