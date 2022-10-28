package com.interseguro.app.examinterseguro.service.impl;

import com.interseguro.app.examinterseguro.model.ImageDetail;
import com.interseguro.app.examinterseguro.repository.IGenericRepository;
import com.interseguro.app.examinterseguro.repository.IImageDetailRepository;
import com.interseguro.app.examinterseguro.service.IImageDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageDetailServiceImpl extends CRUDImpl<ImageDetail, Integer> implements IImageDetailService {

    @Autowired
    private IImageDetailRepository repo;

    @Override
    protected IGenericRepository<ImageDetail, Integer> getRepo() {
        return repo;
    }
}
