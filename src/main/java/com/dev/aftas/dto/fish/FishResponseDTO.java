package com.dev.aftas.dto.fish;

import com.dev.aftas.dto.hunting.HuntingDTO;
import com.dev.aftas.model.Level;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class FishResponseDTO {

    private String name;
    private Double averageWeight;
    private List<HuntingDTO> huntingList;
    private Level level;

}
