package ua.training.microservicepatterns.download.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.training.microservicepatterns.download.service.DownloadService;

@Tag(name = "Download", description = "Download operations")
@RestController
@RequiredArgsConstructor
public class Controller {

    private final DownloadService service;

    @Operation(summary = "Download", description = "Download image by filename")
    @ApiResponse(content = {@Content(mediaType = "image/jpeg"), @Content(mediaType = "application/octet-stream")})
    @GetMapping
    public Resource downloadFile(@Parameter(description = "Image filename (in UUID format)") @RequestParam String filename) {
        return service.downloadFile(filename);
    }
}
