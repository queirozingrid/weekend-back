package com.squirtle.weekend.filesManager;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class FileSaver {

    public static String writeLogo(MultipartFile file, Long id) throws IOException {
        File newFile = multipartToFile(file);
        String directory = "logos-est";
        GoogleCredentials credentials = GoogleCredentials.fromStream
                (new FileInputStream("atomic-producer-353211-797cf151dd07.json"));
        try {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .setStorageBucket("imagens-wknd")
                    .build();

            FirebaseApp fireApp = FirebaseApp.initializeApp(options);

            StorageClient storageClient = StorageClient.getInstance(fireApp);

            InputStream testFile = new FileInputStream(newFile);
            String blobString = directory + "/" + id + "-" + newFile.getName();

            Blob arquivo = storageClient.bucket().create(blobString, testFile);

            System.out.println("link: " + arquivo.getMediaLink());
            return arquivo.getMediaLink();

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static void saveEventPics(List<MultipartFile> files, MultipartFile poster) throws IOException, FileNotFoundException{
        System.out.println(files);
        System.out.println("poster: " + poster.getOriginalFilename());
    }
    public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
}
