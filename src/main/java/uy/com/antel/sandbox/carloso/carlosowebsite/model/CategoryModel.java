package uy.com.antel.sandbox.carloso.carlosowebsite.model;

import java.util.List;

public record CategoryModel(
    String name,
    String description,
    List<PostPreviewCard> posts
) { }
