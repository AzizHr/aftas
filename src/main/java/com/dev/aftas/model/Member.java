package com.dev.aftas.model;

import com.dev.aftas._enum.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    private String identityNumber;
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Hunting> huntingList;

}
