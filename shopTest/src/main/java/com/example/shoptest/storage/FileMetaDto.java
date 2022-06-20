package com.example.shoptest.storage;

import lombok.Data;

import java.util.UUID;

@Data
public class FileMetaDto {
    private UUID hash;
    private String fileName;
}
