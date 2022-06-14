package com.example.shoptest.storage;

import java.util.Collection;
import java.util.UUID;

public interface IFileMetaProvider {

    String checkFileExists(UUID fileHash);

    void saveFileMeta(UUID hash, String fileName, int subType);

    Collection<FileMetaDto> getMetaFiles(int subType);
}
