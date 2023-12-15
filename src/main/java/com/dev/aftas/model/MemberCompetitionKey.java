package com.dev.aftas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MemberCompetitionKey implements Serializable {

    @Column(name = "member_num")
    private Integer memberNum;
    @Column(name = "competition_code")
    private String competitionCode;

}
