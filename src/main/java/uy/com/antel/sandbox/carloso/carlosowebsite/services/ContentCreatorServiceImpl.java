package uy.com.antel.sandbox.carloso.carlosowebsite.services;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.ContentCreator;
import uy.com.antel.sandbox.carloso.carlosowebsite.enums.ChannelCategory;
import uy.com.antel.sandbox.carloso.carlosowebsite.mappers.ContentCreatorMapper;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.ContentCreatorDTO;
import uy.com.antel.sandbox.carloso.carlosowebsite.repositories.ContentCreatorRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@NullMarked
@Transactional(readOnly = true)
public class ContentCreatorServiceImpl implements ContentCreatorService {
    private final ContentCreatorRepository contentCreatorRepository;
    private final ContentCreatorMapper contentCreatorMapper;

    public ContentCreatorServiceImpl(final ContentCreatorRepository contentCreatorRepository,
                                     final ContentCreatorMapper contentCreatorMapper) {
        this.contentCreatorRepository = contentCreatorRepository;
        this.contentCreatorMapper = contentCreatorMapper;
    }

    @Transactional
    public ContentCreatorDTO create(final ContentCreatorDTO contentCreatorDTO) {
        final ContentCreator entity = this.contentCreatorMapper.toEntity(contentCreatorDTO);
        final ContentCreator saved = this.contentCreatorRepository.save(entity);
        return this.contentCreatorMapper.toDto(saved);
    }

    @Override
    public List<ContentCreatorDTO> getCreatorsForCategory(String category) {
        final ChannelCategory cat = ChannelCategory.valueOf(category.toUpperCase(Locale.ROOT));
        return this.contentCreatorRepository.findDistinctByCategory(cat)
                .stream()
                .map(this.contentCreatorMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ContentCreatorDTO> findById(Long id) {
        return this.contentCreatorRepository.findById(id).map(this.contentCreatorMapper::toDto);
    }

    @Override
    public Page<ContentCreatorDTO> findAllPaged(Pageable pageable) {
        final Page<ContentCreator> page = this.contentCreatorRepository.findAll(pageable);
        final List<ContentCreatorDTO> dtos = page.getContent().stream().map(this.contentCreatorMapper::toDto).toList();
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public Optional<ContentCreatorDTO> findByEmail(String email) {
        return this.contentCreatorRepository.findByEmail(email).map(this.contentCreatorMapper::toDto);
    }

    @Override
    @Transactional
    public ContentCreatorDTO update(Long id, ContentCreatorDTO dto) {
        final ContentCreator entity = this.contentCreatorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ContentCreator not found: " + id));
        // MapStruct would normally have @MappingTarget update method; for simplicity, update selected fields
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPersonalDescription(dto.personalDescription());
        entity.setProfileImageUrl(dto.profileImageUrl());
        final ContentCreator saved = this.contentCreatorRepository.save(entity);
        return this.contentCreatorMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.contentCreatorRepository.deleteById(id);
    }
}
