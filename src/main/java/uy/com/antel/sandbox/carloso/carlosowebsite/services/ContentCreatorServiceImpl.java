package uy.com.antel.sandbox.carloso.carlosowebsite.services;

import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Service;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.ContentCreator;
import uy.com.antel.sandbox.carloso.carlosowebsite.mappers.ContentCreatorMapper;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.ContentCreatorDTO;
import uy.com.antel.sandbox.carloso.carlosowebsite.repositories.ContentCreatorRepository;

import java.util.List;

@Service
@NullMarked
public class ContentCreatorServiceImpl implements ContentCreatorService {
    private final ContentCreatorRepository contentCreatorRepository;
    private final ContentCreatorMapper contentCreatorMapper;

    public ContentCreatorServiceImpl(final ContentCreatorRepository contentCreatorRepository,
                                     final ContentCreatorMapper contentCreatorMapper) {
        this.contentCreatorRepository = contentCreatorRepository;
        this.contentCreatorMapper = contentCreatorMapper;
    }

    public void addContentCreator(final ContentCreatorDTO contentCreatorDTO) {
        final ContentCreator contentCreator = this.contentCreatorMapper.toEntity(contentCreatorDTO);

        this.contentCreatorRepository.save(contentCreator);
    }

    @Override
    public List<ContentCreatorDTO> getCreatorsForCategory(String category) {
        return this.contentCreatorRepository.findCreatorsForCategory(category)
                .stream()
                .map(this.contentCreatorMapper::toDto)
                .toList();
    }
}
