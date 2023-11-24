package ua.training.microservicepatterns.upload.rest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Schema(description = "Upload request")
@Data
public class UploadRequest {
    @Parameter(description = "Description", required = true)
    private String description;
    @Parameter(description = "Tags", required = true)
    private Set<String> tags;
    @Parameter(description = "File to upload", required = true)
    private MultipartFile file;
}
