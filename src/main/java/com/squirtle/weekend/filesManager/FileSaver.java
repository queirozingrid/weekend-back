package com.squirtle.weekend.filesManager;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.squirtle.weekend.models.Evento;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileSaver {

    public static String writeLogo(MultipartFile file, Long id, String nomeFantasia) throws IOException {
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
            String blobString = directory + "/" + id + "-" + nomeFantasia + "/" + newFile.getName();

            Blob arquivo = storageClient.bucket().create(blobString, testFile);

            System.out.println("link: " + arquivo.getMediaLink());
            fireApp.delete();
            return arquivo.getMediaLink();

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<String> saveEventPics(List<MultipartFile> files, Long idEstabelecimento, String nomeFantasia) throws IOException, FileNotFoundException{
        List<File> filesList = new ArrayList<>();
        ArrayList<String> fotosEventos = new ArrayList<>();
        for (MultipartFile f : files) {
            File newFile = multipartToFile(f);
            filesList.add(newFile);

            String directory = "fotos-eventos";
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
                //exemplo:   diretório/1-nomeFantasia/arquivo.extensao
                String blobString = directory + "/" + idEstabelecimento + "-" + nomeFantasia + "/" + newFile.getName();
                Blob arquivo = storageClient.bucket().create(blobString, testFile);
                System.out.println("link: " + arquivo.getMediaLink());
                fotosEventos.add(arquivo.getMediaLink());

                fireApp.delete();


            } catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }
        return fotosEventos;

        //System.out.println("poster: " + poster.getOriginalFilename());
    }

    public static String saveEventPoster(MultipartFile poster, Long idEstabelecimento, String nomeFantasia) throws IOException {
        File newFile = multipartToFile(poster);
        String directory = "posters";
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
            String blobString = directory + "/" + idEstabelecimento + "-" + nomeFantasia + "/" + newFile.getName();

            Blob arquivo = storageClient.bucket().create(blobString, testFile);

            System.out.println("link: " + arquivo.getMediaLink());
            fireApp.delete();

            return arquivo.getMediaLink();

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
}
