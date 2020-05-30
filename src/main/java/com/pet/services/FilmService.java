package com.pet.services;

import com.pet.documents.FilmDocument;
import com.pet.enums.FilmRemoveStatus;
import com.pet.models.InsertFilmRequest;
import com.pet.repositories.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmDocument addFilm(InsertFilmRequest request) {
        FilmDocument filmDocument = new FilmDocument();
        filmDocument.setTitle(request.title());
        filmDocument.setDescription(request.description());
        filmDocument.setYear(request.year());
        filmRepository.save(filmDocument);
        return filmDocument;
    }

    public FilmRemoveStatus removeFilm(String id) {
        return filmRepository.findById(id).isPresent() ?
                (tryToDo(filmRepository::deleteById, id) ? FilmRemoveStatus.REMOVED : FilmRemoveStatus.NOT_REMOVED)
                : FilmRemoveStatus.NOT_FOUND;
    }

    private boolean tryToDo(Consumer<String> func, String s) {
        try {
            func.accept(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
