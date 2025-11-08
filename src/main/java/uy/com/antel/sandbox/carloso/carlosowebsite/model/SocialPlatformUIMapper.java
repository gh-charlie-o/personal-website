package uy.com.antel.sandbox.carloso.carlosowebsite.model;

import lombok.Getter;

@Getter
public enum SocialPlatformUIMapper {
    YOUTUBE("YouTube", "bi bi-youtube"),
    INSTAGRAM("Instagram", "bi bi-instagram"),
    TIKTOK("TikTok", "bi bi-tiktok"),
    TWITTER("Twitter", "bi bi-twitter-x"),
    LINKEDIN("LinkedIn", "bi bi-linkedin"),
    FACEBOOK("Facebook", "bi bi-facebook"),
    TWITCH("Twitch", "bi bi-twitch"),
    PODCAST("Podcast", "bi bi-mic-fill");
    
    private final String displayName;
    private final String iconClass;
    
    SocialPlatformUIMapper(String displayName, String iconClass) {
        this.displayName = displayName;
        this.iconClass = iconClass;
    }
}
