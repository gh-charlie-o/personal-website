package uy.com.antel.sandbox.carloso.carlosowebsite.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "content_creators", indexes = {
        @Index(name = "idx_content_creators_email", columnList = "email", unique = true),
        @Index(name = "idx_content_creators_external_id", columnList = "external_id")
})
public class ContentCreator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "personal_description", columnDefinition = "TEXT")
    private String personalDescription;

    @Column(name = "profile_image_url", length = 1024)
    private String profileImageUrl;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "website", length = 512)
    private String website;

    @Column(name = "external_id", length = 100)
    private String externalId;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "contentCreator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Channel> channels = new ArrayList<>();
}
