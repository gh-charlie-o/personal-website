package uy.com.antel.sandbox.carloso.carlosowebsite.model;

/**
 * Represents a content creator's channel on various platforms.
 * This record provides type-safe representation of channel data with immutability.
 * 
 * @param name The name of the channel or content creator (required)
 * @param platform The platform where the channel exists (e.g., youtube, instagram, twitter) (required)
 * @param url The URL to the channel (required)
 * @param description The official description of the channel (required)
 * @param myDescription Personal opinion or review of the channel (nullable)
 * @param icon Bootstrap icon class for the platform (e.g., bi-youtube) (required)
 * @param imageUrl URL to the channel's profile image (nullable)
 */
public record ChannelDTO(
    String name,
    String platform,
    String url,
    String description,
    String myDescription,
    String icon,
    String imageUrl
) {
}
