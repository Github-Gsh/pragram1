package com.example.repository;

import com.example.entity.MaterialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRecordRepository extends JpaRepository<MaterialRecord, String> {
    // 这里可以自定义查询方法，比如根据 material_name 查找
    List<MaterialRecord> findByUpSite(String upSite);
}
