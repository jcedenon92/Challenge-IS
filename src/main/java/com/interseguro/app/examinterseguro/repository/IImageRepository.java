package com.interseguro.app.examinterseguro.repository;

import com.interseguro.app.examinterseguro.model.Image;
import com.interseguro.app.examinterseguro.model.ImageDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IImageRepository extends IGenericRepository<Image, Integer> {

    List<Image> findByIdImage(Integer idImage);
}
