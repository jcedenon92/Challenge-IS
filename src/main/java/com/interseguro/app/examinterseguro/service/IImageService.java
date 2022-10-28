package com.interseguro.app.examinterseguro.service;

import com.interseguro.app.examinterseguro.model.Image;
import com.interseguro.app.examinterseguro.model.ImageDetail;

import java.util.List;
import java.util.Map;

public interface IImageService extends ICRUD<Image, Integer> {

    Image saveTransactional(Image image, List<ImageDetail> details) throws Exception;

    Image updateTransactional(Image image, List<ImageDetail> details) throws Exception;

    List<Image> findByIdImage(Integer idImage);


}
