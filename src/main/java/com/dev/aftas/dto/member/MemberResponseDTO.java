package com.dev.aftas.dto.member;

import com.dev.aftas._enum.IdentityDocumentType;
import com.dev.aftas.dto.hunting.HuntingDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberResponseDTO {

    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private String identityNumber;
    private List<HuntingDTO> huntingList;

}
