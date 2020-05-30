package com.pet.controllers;

import com.pet.documents.FilmDocument;
import com.pet.models.InsertFilmRequest;
import com.pet.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/film")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public FilmDocument insertFilm(@RequestBody InsertFilmRequest insertFilmRequest) {
        return filmService.addFilm(insertFilmRequest);
    }

    @DeleteMapping("/{id}")
    public String removeFilm(@PathVariable String id) {
        var status = filmService.removeFilm(id);
        return switch (status) {
            case REMOVED -> "film is removed successfully";
            case NOT_REMOVED -> "something gets wrong";
            case NOT_FOUND -> "film is not found";
        };
    }
}
