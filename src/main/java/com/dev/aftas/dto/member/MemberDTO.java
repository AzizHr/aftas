package com.dev.aftas.dto.member;

import com.dev.aftas._enum.IdentityDocumentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MemberDTO {

    private Integer num;
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Family name is required")
    private String familyName;
    @NotNull(message = "Accession date is required")
    private LocalDate accessionDate;
    @NotNull(message = "Nationality is required")
    private String nationality;
    @NotNull(message = "identityDocument is required")
    private IdentityDocumentType identityDocument;
    @NotNull(message = "identityNumber is required")
    private String identityNumber;

}
