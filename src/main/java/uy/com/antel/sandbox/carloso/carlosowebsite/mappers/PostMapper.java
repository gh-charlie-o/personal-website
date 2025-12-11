package uy.com.antel.sandbox.carloso.carlosowebsite.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.Post;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.PostPreviewCard;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface                                                                                                                                                                                PostMapper {
    PostPreviewCard toPreviewCard(Post post);
}
