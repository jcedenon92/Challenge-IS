package com.interseguro.app.examinterseguro.controller;

import com.interseguro.app.examinterseguro.dto.ImageDTO;
import com.interseguro.app.examinterseguro.dto.ImageDetailDTO;
import com.interseguro.app.examinterseguro.exceptions.ModelNotFoundException;
import com.interseguro.app.examinterseguro.model.Image;
import com.interseguro.app.examinterseguro.service.IImageService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.interseguro.app.examinterseguro.commons.GlobalConstants.*;

@Slf4j
@RestController
@RequestMapping(API_IMAGE)
public class ImageController {


    @Autowired
    private IImageService service;

    @Autowired
    @Qualifier("imageMapper")
    private ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<Response> readAll() throws Exception {
        List<ImageDTO> list = service.readAll().stream()
                .map(i -> mapper.map(i, ImageDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(Response
                .builder()
                .message(Message.builder()
                        .code(COD_IMAGE_OBTAINED)
                        .message(MSG_IMAGE_OBTAINED)
                        .build())
                .data(list)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable("id") Integer id) throws Exception {
        Image image = service.readById(id);
        if (image == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        ImageDTO dto = mapper.map(image, ImageDTO.class);
        return new ResponseEntity<>(Response
                .builder()
                .message(Message.builder()
                        .code(COD_IMAGE_OBTAINED)
                        .message(MSG_IMAGE_OBTAINED)
                        .build())
                .data(dto)
                .build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody ImageDTO imageDTO) throws Exception {
        Image image = mapper.map(imageDTO, Image.class);
        Image imageCreated = service.saveTransactional(image, image.getImageDetails());
        ImageDTO dto = mapper.map(imageCreated, ImageDTO.class);
        return new ResponseEntity<>(Response
                                    .builder()
                                    .message(Message.builder()
                                            .code(COD_IMAGE_CREATED)
                                            .message(MSG_IMAGE_CREATED)
                                            .build())
                                    .data(dto)
                                    .build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Response> update(@RequestBody ImageDTO imageDTO) throws Exception {
        Image image = service.readById(imageDTO.getId());
        if (image == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + imageDTO.getId());
        }
        Image imageUpdated = service.updateTransactional(mapper.map(imageDTO, Image.class), image.getImageDetails());
        ImageDTO dto = mapper.map(imageUpdated, ImageDTO.class);
        return new ResponseEntity<>(Response
                                    .builder()
                                    .message(Message.builder()
                                            .code(COD_IMAGE_UPDATED)
                                            .message(MSG_IMAGE_UPDATED)
                                            .build())
                                    .data(dto)
                                    .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Integer id) throws Exception {
        Image image = service.readById(id);
        if (image == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        ImageDTO dto = mapper.map(image, ImageDTO.class);
        return new ResponseEntity<>(Response
                                    .builder()
                                    .message(Message.builder()
                                            .code(COD_IMAGE_DELETED)
                                            .message(MSG_IMAGE_DELETED)
                                            .build())
                                    .data(dto)
                                    .build(), HttpStatus.OK);
    }

    /* END POINTS ADDED */

    /**
     * LISTA DE VALORES DE LA IMAGEN(ARRAY)
     * @param id
     * @return List<ImageDetailDTO>
     * @throws Exception
     */

    @GetMapping("/{id}/details")
    public ResponseEntity<Response> getDetailsById(@PathVariable("id") Integer id) throws Exception {
        Image image = service.readById(id);
        if (image == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        List<ImageDetailDTO> list = image.getImageDetails().stream()
                .map(i -> mapper.map(i, ImageDetailDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(Response
                .builder()
                .message(Message.builder()
                        .code(COD_IMAGE_DETAIL_OBTAINED)
                        .message(MSG_IMAGE_DETAIL_OBTAINED)
                        .build())
                .data(list)
                .build(), HttpStatus.OK);
    }

    /* POST ENDPOINT CONVERT TO ARRAY ANTICLOCKWISE */

    /**
     * GET VALUES OF IMAGE(OBJ) AND CONVERT TO ARRAY
     * @param imageDTO
     * @return
     */
    private Integer[] getValuesAndConvertArray(ImageDTO imageDTO){
        List<ImageDetailDTO> lst = imageDTO.getImageDetails();
        Integer[] array = lst.stream()
                                .map(i -> mapper.map(i, ImageDetailDTO.class).getValue())
                                .toArray(Integer[]::new);
        log.info("ARRAY: {}", Arrays.toString(array));
        return array;
    }
    //GROUP IN PAIRS ARRANGEMENT

    /**
     * GROUP IN PAIRS ARRANGEMENT OF N° COLUMNS AND ROWS OF ARRAY OF VALUES
     * @param array
     * @return
     */
    private Integer[][] groupInPairsArrangement(Integer[] array){
        int size = (int) Math.sqrt(array.length);
        Integer[][] arrayPairs = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arrayPairs[i][j] = array[i*size+j];
            }
        }
        log.info("ARRAY PAIRS IN METHOD: {}", Arrays.deepToString(arrayPairs));
        return arrayPairs;
    }

    /* ROTATE ARRAY COUNTERCLOCKWISE */
    /**
     * ROTATE ARRAY COUNTERCLOCKWISE
     * @param array
     * @return
     */
    private Integer[][] rotateArrayCounterClockwise(Integer[][] array){
        int size = array.length;
        Integer[][] arrayRotated = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arrayRotated[i][j] = array[j][size-i-1];
            }
        }
        log.info("ARRAY ROTATED IN METHOD: {}", Arrays.deepToString(arrayRotated));
        return arrayRotated;
    }

    /**
     * END POINT THAT RECEIVES THE OBJ, COLLECTS THE ARRAY AND PERFORMS POSITION ROTATION
     * @param imageDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/details/anticlockwise")
    public ResponseEntity<Response> createAnticlockwise(@RequestBody ImageDTO imageDTO) throws Exception {

        Integer[][] arrayRotate = rotateArrayCounterClockwise(groupInPairsArrangement(getValuesAndConvertArray(imageDTO)));
        log.info("################INICIO DE ARRAY EN ENDPOINT POST####################");
        log.info("ARRAY ROTATED: {}", Arrays.deepToString(arrayRotate));

        Image image = mapper.map(imageDTO, Image.class);
        Image imageCreated = service.saveTransactional(image, image.getImageDetails());
        ImageDTO dto = mapper.map(imageCreated, ImageDTO.class);
        return new ResponseEntity<>(Response
                .builder()
                .message(Message.builder()
                        .code(COD_IMAGE_CREATED)
                        .message(MSG_IMAGE_CREATED_AND_ROTATE)
                        .build())
                .data(arrayRotate)
                .build(), HttpStatus.CREATED);
    }
}
