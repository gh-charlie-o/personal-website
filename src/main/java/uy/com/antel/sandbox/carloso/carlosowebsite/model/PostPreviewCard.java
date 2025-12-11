package uy.com.antel.sandbox.carloso.carlosowebsite.model;

public record PostPreviewCard(
        Long id,
        String slug,
        String title,
        String category,
        String excerpt,
        String tagsCsv,
        String coverImageUrl
) { }