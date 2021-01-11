package org.upgrad.upstac.testrequests;

import lombok.Getter;
import lombok.Setter;
import org.upgrad.upstac.users.User;
import org.upgrad.upstac.users.models.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class TestRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long requestId;

    @ManyToOne
    private User createdBy;

    private LocalDate created = LocalDate.now();

    RequestStatus status;

    String name;
    Gender gender;
    String address;
    Integer age;
    String email;
    private String phoneNumber;
    private Integer pinCode;
}
