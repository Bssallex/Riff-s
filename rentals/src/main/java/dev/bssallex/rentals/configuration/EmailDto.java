package dev.bssallex.rentals.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.bssallex.rentals.enums.EmailStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class EmailDto {

    private UUID emailId;

    private String emailFrom;

    private String emailTo;

    private String emailSubject;

    @Column(columnDefinition = "TEXT")
    private String body;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate sendDateEmail;

    @Enumerated(EnumType.STRING)
    private EmailStatus statusEmail;
}
