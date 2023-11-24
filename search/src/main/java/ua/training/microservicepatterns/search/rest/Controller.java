package ua.training.microservicepatterns.search.rest;

import java.util.Set;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.training.microservicepatterns.search.repository.Metadata;
import ua.training.microservicepatterns.search.service.MetadataService;

@Tag(name = "Search", description = "Search operations")
@RestController
@RequiredArgsConstructor
public class Controller {

    private final MetadataService service;
    private final Environment environment;

    @Operation(summary = "Find all", description = "Get all images and metadata")
    @GetMapping("/all")
    public Iterable<Metadata> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Find by tags", description = "Get by tags using exact match")
    @GetMapping("/byTags")
    public Iterable<Metadata> getByTags(@RequestParam Set<String> tags) {
        return service.findByTags(tags);
    }

    @Operation(summary = "Find by description", description = "Get by description using substring match")
    @GetMapping("/byDescription")
    public Iterable<Metadata> getByDescription(@RequestParam String desc) {
        return service.findByDescription(desc);
    }

    @Operation(summary = "Save metadata", description = "Create new entry with description, tags, filename")
    @PostMapping("/save")
    public void save(@RequestBody Metadata metadata) {
        mimicErrorState();
        service.save(metadata);
    }

    private void mimicErrorState() {
        if (environment.acceptsProfiles(Profiles.of("broken"))) {
            throw new RuntimeException("This is a fake error");
        }
    }
}
