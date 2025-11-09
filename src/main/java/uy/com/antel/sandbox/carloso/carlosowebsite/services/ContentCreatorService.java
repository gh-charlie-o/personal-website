package uy.com.antel.sandbox.carloso.carlosowebsite.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.ContentCreatorDTO;

import java.util.List;
import java.util.Optional;

public interface ContentCreatorService {
    // Existing method used by controllers
    List<ContentCreatorDTO> getCreatorsForCategory(String category);

    // New CRUD methods
    ContentCreatorDTO create(ContentCreatorDTO dto);

    Optional<ContentCreatorDTO> findById(Long id);

    Page<ContentCreatorDTO> findAllPaged(Pageable pageable);

    Optional<ContentCreatorDTO> findByEmail(String email);

    ContentCreatorDTO update(Long id, ContentCreatorDTO dto);

    void delete(Long id);
}
