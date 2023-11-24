package ua.training.microservicepatterns.search.repository;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface MetadataRepository extends CrudRepository<Metadata, String> {

    Iterable<Metadata> findByTagsIn(Set<String> tags);

    Iterable<Metadata> findByDescriptionContaining(String desc);
}
