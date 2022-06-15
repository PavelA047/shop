package com.example.shoptest.storage;

import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class FileMetaProvider implements IFileMetaProvider {

    private static final String GET_FILES_META = "select f.filename, h.hash from file_info_metadata f left join hash_table h on f.hash_id = h.id where f.sub_type = :subtype";

    private static final String GET_FILE_NAME_BY_HASH = "select f.filename from file_info_metadata f join hash_table h on f.hash_id = h.id where hash = :hash";

    private static final String SAVE_FILE_META_DATA_MAIN_TAB = "insert into file_info_metadata (hash_id, filename, sub_type) values (:hash_id, :filename, :subtype)";

    private static final String SAVE_FILE_META_DATA_HASH_TAB = "insert into hash_table (hash) values (:hash)";

    private static final String GET_HASH_LIST = "select hash from hash_table";

    private static final String GET_HASH_ID_BY_HASH_HASH_TAB = "select id from hash_table where hash = :hash";

    private static final String GET_HASH_ID_BY_HASH_MAIN_TAB = "select filename from file_info_metadata where hash_id = :hash_id";

    private final Sql2o sql2o;

    public FileMetaProvider() {
        this.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/shop", "postgres", "root");
    }

    @Override
    public String checkFileExists(UUID fileHash, String fileName) {
        try (Connection connection = sql2o.open()) {
            List<String> fileNameList = connection.createQuery(GET_FILE_NAME_BY_HASH, false)
                    .addParameter("hash", fileHash)
                    .executeScalarList(String.class);
            return fileNameList.stream().filter(f -> f.equals(fileName)).findFirst().orElse(null);
        }
    }

    @Override
    public void saveFileMeta(UUID fileHash, String fileName, int sybType) {
        try (Connection connection = sql2o.open()) {
            UUID uuidInDbEqualToFileHash = connection.createQuery(GET_HASH_LIST, false)
                    .executeScalarList(UUID.class)
                    .stream()
                    .filter(f -> f.equals(fileHash))
                    .findFirst()
                    .orElse(null);
            if (uuidInDbEqualToFileHash == null) {
                connection.createQuery(SAVE_FILE_META_DATA_HASH_TAB, false)
                        .addParameter("hash", fileHash)
                        .executeUpdate();
            }
            Integer fileHashId = connection.createQuery(GET_HASH_ID_BY_HASH_HASH_TAB, false)
                    .addParameter("hash", fileHash)
                    .executeScalar(Integer.class);
            connection.createQuery(SAVE_FILE_META_DATA_MAIN_TAB, false)
                    .addParameter("hash_id", fileHashId)
                    .addParameter("filename", fileName)
                    .addParameter("subtype", sybType)
                    .executeUpdate();
        }
    }

    @Override
    public Collection<FileMetaDto> getMetaFiles(int subtype) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(GET_FILES_META, false)
                    .addParameter("subtype", subtype)
                    .executeAndFetch(FileMetaDto.class);
        }
    }

    @Override
    public boolean checkLinks(UUID md5) {
        try (Connection connection = sql2o.open()) {
            Integer hashId = connection.createQuery(GET_HASH_ID_BY_HASH_HASH_TAB, false)
                    .addParameter("hash", md5)
                    .executeScalar(Integer.class);
            String fileNameIfExists = connection.createQuery(GET_HASH_ID_BY_HASH_MAIN_TAB, false)
                    .addParameter("hash_id", hashId)
                    .executeScalar(String.class);
            if (fileNameIfExists != null) return true;
        }
        return false;
    }

    @Override
    public void deleteLink(UUID md5, String fileName) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("delete from file_info_metadata where filename = :filename", false)
                    .addParameter("filename", fileName)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteHash(UUID md5, String fileName) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("delete from hash_table where hash = :md5", false)
                    .addParameter("md5", md5)
                    .executeUpdate();
        }
    }
}