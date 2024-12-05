package kz.bloooom.administration.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.dto.storage.FileInfo;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.service.StorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StorageServiceImpl implements StorageService {

    AmazonS3 amazonS3Client;
    static String PREFIX_KEY = "documents/";

    @NonFinal
    @Value("${cloud.minio.host}")
    String s3Host;

    @NonFinal
    @Value("${cloud.minio.bucket}")
    String s3Bucket;

    @Override
    public FileInfo storeFile(MultipartFile multipartFile, String directory) {
        if (multipartFile == null) {
            throw new IllegalArgumentException("NULL_FILE_ERROR_MESSAGE");
        }
        log.info("UPLOADING_FILE_SIZE_LOG_MESSAGE {}", multipartFile.getSize());
        try {
            File file = convertMultiPartFileToFile(multipartFile);
            String fileName = uploadFileToS3BucketAndGetKey(file, directory);
            file.delete();
            return new FileInfo(fileName);
        } catch (AmazonServiceException e) {
            log.error("UPLOAD_FILE_ERROR_LOG_MESSAGE {} {}", multipartFile.getOriginalFilename(), e);
            throw new BloomAdministrationException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ErrorCodeConstant.ERROR_WHEN_FILE_STORE,
                    "An error has occurred, while file downloading");
        }
    }

    private File convertMultiPartFileToFile(MultipartFile multipartFile) {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            log.error("", e);
        }
        return file;
    }

    private String uploadFileToS3BucketAndGetKey(File file, String directory) {
        String fileName = file.getName();
        String fullFile = directory + File.separator + fileName;
        PutObjectRequest putObjectRequest = new PutObjectRequest(s3Bucket, fullFile, file);
        amazonS3Client.putObject(putObjectRequest);
        return fileName;
    }

    private String uploadFileToS3BucketAndGetKey(File file) {
        String bucketKey = PREFIX_KEY + LocalDateTime.now() + "_" + file.getName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(s3Bucket, bucketKey, file);
        amazonS3Client.putObject(putObjectRequest);
        return bucketKey;
    }

    @Override
    @Transactional
    public void delete(List<String> keys) {
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(s3Bucket)
                .withKeys(keys.toArray(new String[0]));
        amazonS3Client.deleteObjects(deleteObjectsRequest);
        log.info("Deleted from S3 keys: {}", keys);
    }

    @Override
    @Transactional
    public void delete(String path) {
        String buckets = String.format(s3Host) + "/" + s3Bucket;
        if (path.startsWith(buckets)) {
            path = path.substring(buckets.length() + 1);
        }
        amazonS3Client.deleteObject(s3Bucket, path);
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        if (multipartFile == null) {
            throw new IllegalArgumentException("NULL_FILE_ERROR_MESSAGE");
        }
        log.info("UPLOADING_FILE_SIZE_LOG_MESSAGE {}", multipartFile.getSize());
        try {
            File file = convertMultiPartFileToFile(multipartFile);
            String bucketKey = uploadFileToS3BucketAndGetKey(file);
            file.delete();
            return String.format(s3Host) + "/" + s3Bucket + "/" + bucketKey;
        } catch (AmazonServiceException e) {
            log.error("UPLOAD_FILE_ERROR_LOG_MESSAGE {} {}", multipartFile.getOriginalFilename(), e);
            throw new BloomAdministrationException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ErrorCodeConstant.ERROR_WHEN_FILE_STORE,
                    "An error has occurred, while file downloading");
        }
    }
}
