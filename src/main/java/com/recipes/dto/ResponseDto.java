package com.recipes.dto;

import com.recipes.StatusEnum;
import lombok.Data;

@Data
public class ResponseDto {
    private StatusEnum Status;
    private Object content;

}
