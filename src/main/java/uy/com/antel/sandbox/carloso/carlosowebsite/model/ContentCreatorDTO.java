package uy.com.antel.sandbox.carloso.carlosowebsite.model;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

@NullMarked
public record ContentCreatorDTO(
    String name,
    String description,
    @Nullable String personalDescription,
    @Nullable String profileImageUrl,
    @Nullable List<ChannelDTO> channels
) {
}
