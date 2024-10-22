package com.example.controller;

import com.example.entity.MaterialRecord;
import com.example.service.MaterialRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MaterialRecordController {

    @Autowired
    private MaterialRecordService materialRecordService;

    // 查询所有记录
    @GetMapping("/materials")
    public List<MaterialRecord> getAllRecords() {
        return materialRecordService.findAll();
    }

    // 根据ID查询
    @GetMapping("/{id}")
    public MaterialRecord getRecordById(@PathVariable String id) {
        return materialRecordService.findById(id);
    }
    // 根据 up_site 查询记录
    @GetMapping("/search")
    public List<MaterialRecord> searchByUpSite(@RequestParam String upSite) {
        return materialRecordService.getRecordsByUpSite(upSite);  // 调用 Service 层新方法
    }

}
