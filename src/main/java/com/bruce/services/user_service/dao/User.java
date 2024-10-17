package com.bruce.services.user_service.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Builder(toBuilder = true)
@Data
@Entity
@AllArgsConstructor
public class User {

    // Method 1
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")

    // Method 2
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    private String name;
    private String email;
    private int age;
    private String address;
    @Column(name = "created_at")
    //    @JsonProperty("created_at") // for json usage
    //    @SerializedName("id") // For gson usage
    private LocalDateTime createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;


    // Method 2
//    @PrePersist
//    public void prePersist() {
//        // Generate UUID before persisting the entity
//        this.id = UUID.randomUUID();
//    }

    // Method 3 ** - Can see the generated UUID
    public User() {
//        //        // Unless is using abstract base class
//        //        super();
//        //        super.setId(UUID.randomUUID());
//        this.id = UUID.randomUUID();
    }

}
