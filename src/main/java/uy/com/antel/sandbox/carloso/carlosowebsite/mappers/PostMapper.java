package uy.com.antel.sandbox.carloso.carlosowebsite.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.Post;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.PostInfo;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.PostPreviewCard;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface                                                                                                                                                                                PostMapper {
    @Mapping(target = "category", source = "category.name")
    PostPreviewCard toPreviewCard(Post post);

    @Mapping(target = "category", source = "category.name")
    PostInfo toPostInfo(Post post);
}
