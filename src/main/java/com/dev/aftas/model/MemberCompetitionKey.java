package com.dev.aftas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class MemberCompetitionKey implements Serializable {

    @Column(name = "member_id")
    private Integer memberId;
    @Column(name = "competition_id")
    private String competitionId;

}
