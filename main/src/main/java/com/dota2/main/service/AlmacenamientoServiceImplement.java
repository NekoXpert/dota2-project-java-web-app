package com.dota2.main.service;

import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
public class AlmacenamientoServiceImplement implements AlmacenamientoService {

    private final Path uploadLocation;

    public AlmacenamientoServiceImplement() {
        this.uploadLocation = Paths.get("upload-dir");
        try {
            Files.createDirectories(uploadLocation);
        } catch (Exception e) {
            throw new StorageException("No se pudo crear el directorio para subir archivos.", e);
        }
    }

   @Override
public String store(MultipartFile file) {
    String filename = StringUtils.cleanPath(file.getOriginalFilename());

    try {
        if (file.isEmpty()) {
            throw new StorageException("No se puede subir un archivo vacío " + filename);
        }
        if (filename.contains("..")) {
            // This is a security check
            throw new StorageException(
                    "No se puede subir archivos con ruta relativa " + filename);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, this.uploadLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
    }
    catch (IOException e) {
        throw new StorageException("Fallo al almacenar archivo " + filename, e);
    }

    return uploadLocation.resolve(filename).toUri().toString();
}


    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.uploadLocation, 1)
                    .filter(path -> !path.equals(this.uploadLocation))
                    .map(this.uploadLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename) {
        return uploadLocation.resolve(filename);
    }

    @Override
public Path loadAsPath(String filename) {
    try {
        Path file = load(filename);
        if (Files.exists(file) && Files.isReadable(file)) {
            return file;
        } else {
            throw new StorageException("Could not read file: " + filename);
        }
    } catch (Exception e) {
        throw new StorageException("Could not read file: " + filename, e);
    }
}

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(uploadLocation.toFile());
    }

    @Override
    public void delete(String filename) {
        Path file = load(filename);
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new StorageException("Could not delete file: " + filename, e);
        }
    }

}
