package uy.com.antel.sandbox.carloso.carlosowebsite.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uy.com.antel.sandbox.carloso.carlosowebsite.entities.Channel;
import uy.com.antel.sandbox.carloso.carlosowebsite.enums.SocialPlatform;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.ChannelDTO;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.SocialPlatformUIMapper;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ChannelMapper {
    @Mapping(target = "icon", source = "platform", qualifiedByName = "platformToIcon")
    ChannelDTO toDto(Channel entity);

    @Named("platformToIcon")
    default String platformToIcon(SocialPlatform platform) {
        if (platform == null) {
            return null;
        }
        return SocialPlatformUIMapper.valueOf(platform.name()).getIconClass();
    }
}
