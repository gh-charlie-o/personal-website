package uy.com.antel.sandbox.carloso.carlosowebsite.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.Post;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByCategoryAndIsPublishedTrueOrderByPostOrderDescPublishedAtDesc(String category, Pageable pageable);

    Optional<Post> findByIdAndIsPublishedTrue(Long id);

    Optional<Post> findBySlugAndIsPublishedTrue(String slug);
}
