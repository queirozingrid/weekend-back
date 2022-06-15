package com.squirtle.weekend.filesManager;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;

public class FileSaver {
    private static String path = "https://console.cloud.google.com/storage/browser/imagens-wknd/";

    @Autowired
    private static Storage storage;

    public static String saveLogo(String fileName) {
        String directory = "logos-est";
        String fullPath = path + directory + "/" + fileName;
        Bucket bucket = storage.create(BucketInfo.of(path));

        storage.create(
                BlobInfo.newBuilder(path, directory + "/" + fileName).build(),
                "file contents".getBytes()
        );
        return fullPath;
    }

    public FileSaver(String path) {
        this.path = path;
    }
}
