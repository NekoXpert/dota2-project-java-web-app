package com.dota2.main.service;

// Author: Felipe Reyes { Nekosor }
import com.dota2.main.model.Heroe;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface HeroeService {

    List<Heroe> getAllHeroes();

    void saveHeroe(Heroe heroe, MultipartFile multipartFile) throws IOException;

    Heroe getHeroeById(long id);

    void deleteHeroeById(long id);
}