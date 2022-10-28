package com.interseguro.app.examinterseguro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDTO {

    private Integer id;

    private Integer numberColumns;

    private Integer numberRows;

    private LocalDateTime createdAt;

    private Date updatedAt;

    private List<ImageDetailDTO> imageDetails;
}
