package kz.bloooom.administration.domain.dto.storage;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class StorageInfo {
    private String s3Host;
    private String s3Bucket;
}
