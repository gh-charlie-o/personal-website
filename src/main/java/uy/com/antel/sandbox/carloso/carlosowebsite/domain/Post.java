package uy.com.antel.sandbox.carloso.carlosowebsite.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "posts", indexes = {
        @Index(name = "idx_posts_slug", columnList = "slug", unique = true),
        @Index(name = "idx_posts_category_id", columnList = "category_id"),
        @Index(name = "idx_posts_published_at", columnList = "published_at")
})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "published_at")
    private LocalDate publishedAt;

    @Column(name = "slug", nullable = false, length = 200)
    private String slug;

    @Column(name = "title", nullable = false, length = 300)
    private String title;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "read_time_minutes")
    private Integer readTimeMinutes;

    @Column(name = "author", length = 200)
    private String author;

    @Column(name = "excerpt", columnDefinition = "TEXT")
    private String excerpt;

    @Column(name = "tags_csv", length = 1000)
    private String tagsCsv;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "cover_image_url", length = 1024)
    private String coverImageUrl;

    @Column(name = "is_published", nullable = false)
    private Boolean isPublished = true;

    @Column(name = "post_order", nullable = false)
    private int postOrder;

    /*@ManyToOne
    @JoinColumn(name = "content_creator_id")
    private ContentCreator contentCreator;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;
    */

    @Transient
    public List<String> getTags() {
        if (StringUtils.isBlank(this.tagsCsv)) {
            return List.of();
        };

        return Arrays.stream(tagsCsv.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}
