package ua.training.microservicepatterns.search.repository;

import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName="metadata")
public class Metadata {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Keyword)
    private Set<String> tags;
    @Field(type = FieldType.Text)
    private String filename;
}
