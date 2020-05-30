package com.pet.repositories;

import com.pet.documents.FilmDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends ElasticsearchRepository<FilmDocument, String> {

}
