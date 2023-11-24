package ua.training.microservicepatterns.upload.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.training.microservicepatterns.upload.service.UploadService;

@Tag(name = "Upload", description = "Upload operations")
@RestController
@RequiredArgsConstructor
public class Controller {

    private final UploadService uploadService;

    @Operation(summary = "Upload", description = "Save image and metadata")
    @PostMapping
    public String upload(@RequestBody(
            description = "Upload request",
            required = true,
            content = @Content(mediaType = "multipart/form-data", schema = @Schema(implementation = UploadRequest.class))
    ) @ModelAttribute UploadRequest request) {
        return uploadService.uploadFile(request);
    }
}
