package com.squirtle.weekend.filesManager;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

public class FileSaver {
    private static String path = "https://console.cloud.google.com/storage/browser/imagens-wknd/";
    //private static String path = "https://storage.googleapis.com/imagens-wknd/logos-est/";
    @Autowired
    private static Storage storage = StorageOptions.getDefaultInstance().getService();


    public static String saveLogo(MultipartFile logo, String cnpj) throws IOException {
        File newFile = multipartToFile(logo);
        String directory = "logos-est";
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream
                    (new FileInputStream("atomic-producer-353211-797cf151dd07.json"));
            System.out.println(credentials);

            BlobId blobId = BlobId.of("imagens-wknd", logo.getOriginalFilename());
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

            StorageClient storageClient = StorageClient.getInstance((FirebaseApp) storage);

            InputStream testFile = new FileInputStream(newFile);
            String blobString = directory + "/" + cnpj + "-" + newFile.getName();

            Blob arquivo = storageClient.bucket().create(blobString, testFile);

            System.out.println(arquivo.getMediaLink());

//            storage.getOptions().toBuilder().setCredentials(credentials);
//            System.out.println(credentials.getAccessToken());
//
//            BlobInfo blob2 = storage.create(blobInfo, logo.getBytes());
//            BlobInfo blob = storage.create(
//                    BlobInfo.newBuilder(
//                            "imagens-wknd",
//                            logo.getOriginalFilename())
//                            .build(),
//                    logo.getBytes(),
//                    Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ)
//
//            );
//            //String api = "AIzaSyBCR2Dm36jKBurKS_QXlO2FpW6s4MBJDKM";
//            System.out.printf(blob.getMediaLink());
            return arquivo.getMediaLink();

        } catch (IllegalStateException | IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
}
