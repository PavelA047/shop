package com.example.shoptest.storage;

import org.sql2o.Connection;

import java.util.Collection;
import java.util.UUID;

public interface IFileMetaProvider {

    String checkFileExists(UUID fileHash, String fileName);

    void saveFileMeta(UUID hash, String fileName, int subType);

    Collection<FileMetaDto> getMetaFiles(int subType);

    boolean checkLinks(UUID md5);

    void deleteLink(UUID md5, String fileName);

    void deleteHash(UUID md5, String fileName);
}
