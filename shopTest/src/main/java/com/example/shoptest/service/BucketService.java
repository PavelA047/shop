package com.example.shoptest.service;

import com.example.shoptest.dto.BucketDto;
import com.example.shoptest.entities.Bucket;
import com.example.shoptest.entities.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);

    BucketDto getBucketByUser(String name);

    void deleteFromUserBucket(Long id, String name);
}
