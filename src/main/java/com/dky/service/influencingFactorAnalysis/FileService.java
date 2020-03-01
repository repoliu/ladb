package com.dky.service.influencingFactorAnalysis;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface FileService {
    Map<String,Object> readExcel(MultipartFile file) throws IOException;
}
