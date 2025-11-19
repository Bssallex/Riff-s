package dev.bssallex.email.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.bssallex.email.enums.EmailStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "TB_EMAIL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
