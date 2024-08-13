package dev.kinodesu.MeowMeowApi.model.user;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "MeowUser")
@Table(name = "meow_user")
@RequiredArgsConstructor
@Data
public class MeowUser {

    public MeowUser(DTOMeowUser user) {
        this.id = user.id();
        this.name = user.name();
        this.email = user.email();
        this.documentNumber = user.documentNumber();
        this.phoneNumber = user.phoneNumber();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    
    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_document_number")
    private String documentNumber;

    @Column(name = "user_phone_number")
    private String phoneNumber;
}
