package uy.com.antel.sandbox.carloso.carlosowebsite.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.Post;

import java.util.Optional;

public interface PostService {
    Page<Post> getPublishedByCategory(String category, Pageable pageable);
    Optional<Post> getPublishedById(Long id);
    Optional<Post> getPublishedBySlug(String slug);
}