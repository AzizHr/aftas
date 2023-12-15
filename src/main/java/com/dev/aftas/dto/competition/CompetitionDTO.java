package com.dev.aftas.dto.competition;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class CompetitionDTO {

    @Nullable
    private String code;
    @NotNull(message = "Date is required")
    private LocalDate date;
    @NotNull(message = "StartTime ID is required")
    private LocalTime startTime;
    @NotNull(message = "EndTime is required")
    private LocalTime endTime;
    @NotNull(message = "Number of participants is required")
    @Min(3)
    private Integer numberOfParticipants;
    @NotNull(message = "Location is required")
    private String location;
    @NotNull(message = "Amount is required")
    private Double amount;

}
