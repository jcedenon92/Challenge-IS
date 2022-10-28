package com.interseguro.app.examinterseguro.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDetailDTO {

    private Integer id;

    private Integer value;

    private LocalDateTime createdAt;

    @JsonIgnore
    private ImageDTO image;
}
