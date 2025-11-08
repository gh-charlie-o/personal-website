package uy.com.antel.sandbox.carloso.carlosowebsite.model;

/**
 * Represents a social media link for a content creator.
 * This record provides type-safe representation of a link to a specific platform.
 * 
 * @param platform The social media platform (required)
 * @param url The direct URL to the creator's profile on this platform (required)
 */
public record SocialLink(
    SocialPlatformUIMapper platform,
    String url
) {
}
