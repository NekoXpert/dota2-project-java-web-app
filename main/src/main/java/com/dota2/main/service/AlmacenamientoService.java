package com.dota2.main.service;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface AlmacenamientoService {

    String store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

   Path loadAsPath(String filename);

    void deleteAll();

    void delete(String filename);
}