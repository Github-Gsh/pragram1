package com.example.service;

import com.example.entity.MaterialRecord;
import com.example.repository.MaterialRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialRecordService {

    @Autowired
    private MaterialRecordRepository materialRecordRepository;

    // 查询所有记录
    public List<MaterialRecord> findAll() {
        return materialRecordRepository.findAll();
    }

    // 根据ID查找
    public MaterialRecord findById(String id) {
        return materialRecordRepository.findById(id).orElse(null);
    }

    // 根据 up_site 查询
    public List<MaterialRecord> getRecordsByUpSite(String upSite) {
        return materialRecordRepository.findByUpSite(upSite);  // 调用新的 Repository 方法
    }
}
