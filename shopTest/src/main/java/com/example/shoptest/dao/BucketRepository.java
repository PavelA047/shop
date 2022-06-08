package com.example.shoptest.dao;

import com.example.shoptest.entities.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
