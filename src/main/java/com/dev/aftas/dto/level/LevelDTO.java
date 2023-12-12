package com.dev.aftas.dto.level;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LevelDTO {

    private Integer code;
    private String description;
    private Integer points;

}
