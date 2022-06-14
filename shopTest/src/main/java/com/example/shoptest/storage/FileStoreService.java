package com.example.shoptest.storage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.UUID;

@Component
public class FileStoreService implements IFileStoreService {

    @Autowired
    IFileSystemProvider systemProvider;

    @Autowired
    IFileMetaProvider fileMetaProvider;

    @Override
    public String storeFile(byte[] content, String fileName, int subFileType) throws IOException, NoSuchAlgorithmException {
        final UUID md5 = HashHelper.getMd5Hash(content);

        String fileNameFromDb = fileMetaProvider.checkFileExists(md5);
        if (fileNameFromDb == null) {
            fileMetaProvider.saveFileMeta(md5, fileName, subFileType);
            fileNameFromDb = systemProvider.storeFile(content, md5, fileName);
        }

        return fileNameFromDb;
    }

    @Override
    public byte[] getFile(UUID md5) throws IOException {
        String fileNameFromDb = fileMetaProvider.checkFileExists(md5);
        String ext = FilenameUtils.getExtension(fileNameFromDb);
        String fullFileNameFromDb = md5.toString() + "." + ext;
        return systemProvider.getFile(fullFileNameFromDb);
    }

    @Override
    public Collection<FileMetaDto> getMetaFiles(int subtype) {
        return fileMetaProvider.getMetaFiles(subtype);
    }
}
