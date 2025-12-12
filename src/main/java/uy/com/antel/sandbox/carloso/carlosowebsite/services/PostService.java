package uy.com.antel.sandbox.carloso.carlosowebsite.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.Post;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.PostInfo;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.PostPreviewCard;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Page<Post> getPublishedByCategory(String categorySlug, Pageable pageable);

    List<PostPreviewCard> getPreviewPostPublishedByCategory(String category, Pageable pageable);

    Page<Post> getPublished(Pageable pageable);
    PostInfo getPublishedById(Long id);
    Optional<Post> getPublishedBySlug(String slug);

    List<PostPreviewCard> getLatestsPosts();

}