package uy.com.antel.sandbox.carloso.carlosowebsite.model;

import java.time.Instant;
import java.time.LocalDate;

public record PostInfo(
        Long id,
        Instant createdAt,
        Instant updatedAt,
        LocalDate publishedAt,
        String slug,
        String title,
        String category,
        Integer readTimeMinutes,
        String author,
        String excerpt,
        String tagsCsv,
        String content,
        String coverImageUrl,
        Boolean isPublished,
        int postOrder
) {
}