package uy.com.antel.sandbox.carloso.carlosowebsite.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uy.com.antel.sandbox.carloso.carlosowebsite.enums.ChannelCategory;
import uy.com.antel.sandbox.carloso.carlosowebsite.enums.SocialPlatform;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "channels", indexes = {
        @Index(name = "idx_channels_name", columnList = "name")
})
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "url", nullable = false, length = 1024)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false, length = 50)
    private SocialPlatform platform;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", length = 1024)
    private String imageUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "channel_categories", joinColumns = @JoinColumn(name = "channel_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 50, nullable = false)
    private List<ChannelCategory> categories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_creator_id")
    private ContentCreator contentCreator;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
