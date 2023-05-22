package com.dota2.main.service;

// Author: Felipe Reyes { Nekosor }
import com.dota2.main.model.Heroe;
import com.dota2.main.repository.HeroeRepository;
import com.dota2.main.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HeroeServiceImplement implements HeroeService {

    @Autowired
    private HeroeRepository heroeRepository;

    @Override
    public List<Heroe> getAllHeroes() {
        return heroeRepository.findAll();
    }

    @Override
    public void saveHeroe(Heroe heroe, MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            heroe.setImage(fileName.getBytes());
            heroeRepository.save(heroe);
            String uploadDir = ".heroes-images/" + heroe.getId();
            FileUploadUtil.saveFile(fileName, image, uploadDir);
        }
    }

    @Override
    public Heroe getHeroeById(long id) {
        Optional<Heroe> optional = heroeRepository.findById(id);
        return optional.orElseThrow(() -> new RuntimeException("Heroe no encontrado por id: " + id));
    }

    @Override
    public void deleteHeroeById(long id) {
        heroeRepository.deleteById(id);
    }
}