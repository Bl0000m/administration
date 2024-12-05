package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.dto.storage.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {

    FileInfo storeFile(MultipartFile file, String directory);

    void delete(List<String> keys);

    void delete(String path);

    String uploadFile(MultipartFile multipartFile);
}
