package com.interseguro.app.examinterseguro.service.impl;

import com.interseguro.app.examinterseguro.model.Image;
import com.interseguro.app.examinterseguro.model.ImageDetail;
import com.interseguro.app.examinterseguro.repository.IGenericRepository;
import com.interseguro.app.examinterseguro.repository.IImageRepository;
import com.interseguro.app.examinterseguro.service.IImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ImageServiceImpl extends CRUDImpl<Image, Integer> implements IImageService{

    @Autowired
    private IImageRepository repo;

    @Override
    protected IGenericRepository<Image, Integer> getRepo() {
        return repo;
    }

    @Override
    public Image saveTransactional(Image image, List<ImageDetail> details) throws Exception {
        details.forEach(d -> d.setImage(image));
        image.setImageDetails(details);
        return repo.save(image);
    }

    @Override
    public Image updateTransactional(Image image, List<ImageDetail> details) throws Exception {
        details.forEach(d -> d.setImage(image));
        image.setImageDetails(details);
        return repo.save(image);
    }

    @Override
    public List<Image> findByIdImage(Integer idImage) {
        return repo.findByIdImage(idImage);
    }



}
