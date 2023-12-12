package com.dev.aftas.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Ranking {

    @EmbeddedId
    private MemberCompetitionKey id;
    private Integer rank;
    private Integer score;

}
