package uy.com.antel.sandbox.carloso.carlosowebsite.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.Post;
import uy.com.antel.sandbox.carloso.carlosowebsite.repositories.PostRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Page<Post> getPublishedByCategory(String category, Pageable pageable) {
        return postRepository.findByCategoryAndIsPublishedTrueOrderByPostOrderDescPublishedAtDesc(category, pageable);
    }

    @Override
    public Optional<Post> getPublishedById(Long id) {
        return postRepository.findByIdAndIsPublishedTrue(id);
    }

    @Override
    public Optional<Post> getPublishedBySlug(String slug) {
        return postRepository.findBySlugAndIsPublishedTrue(slug);
    }
}
