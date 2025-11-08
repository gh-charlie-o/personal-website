package uy.com.antel.sandbox.carloso.carlosowebsite.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.ContentCreator;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.ContentCreatorDTO;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = { ChannelMapper.class }
)
public interface ContentCreatorMapper {
    ContentCreatorDTO toDto(ContentCreator entity);

    ContentCreator toEntity(ContentCreatorDTO dto);
}
