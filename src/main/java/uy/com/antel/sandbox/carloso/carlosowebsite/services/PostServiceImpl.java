package uy.com.antel.sandbox.carloso.carlosowebsite.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.Post;
import uy.com.antel.sandbox.carloso.carlosowebsite.mappers.PostMapper;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.PostPreviewCard;
import uy.com.antel.sandbox.carloso.carlosowebsite.repositories.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(final PostRepository postRepository, final PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public Page<Post> getPublishedByCategory(String category, Pageable pageable) {
        return postRepository.findByCategoryAndIsPublishedTrueOrderByPostOrderDescPublishedAtDesc(category, pageable);
    }

    @Override
    public Page<Post> getPublished(Pageable pageable) {
        final Page<Post> allOrderByCreatedAtDesc = this.postRepository.findAllByOrderByCreatedAtDesc(pageable);

        return allOrderByCreatedAtDesc;
    }

    @Override
    public Optional<Post> getPublishedById(Long id) {
        return postRepository.findByIdAndIsPublishedTrue(id);
    }

    @Override
    public Optional<Post> getPublishedBySlug(String slug) {
        return postRepository.findBySlugAndIsPublishedTrue(slug);
    }

    @Override
    public List<PostPreviewCard> getLatestsPosts() {
        return this.postRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(0, 6))
                .getContent()
                .stream()
                .map(this.postMapper::toPreviewCard)
                .toList();
    }
}
