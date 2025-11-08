# Refactoring Prompt: Person-Centric Content Creator Model with Multiple Social Platforms

## Objective
Refactor the content creator recommendation system to focus on **people/creators** rather than individual platform channels. Each creator card should display the person's information once, with all their social media platforms as clickable links within the same card.

## Current Problem
- Each social platform (YouTube, Instagram, TikTok, Twitter) is treated as a separate entity
- The same creator appears multiple times if they have multiple platforms
- Users must view separate cards to find all platforms for a creator

## Desired Solution
**One card per creator** that includes:
- Creator's name
- Profile image (single, primary image)
- Bio/description (what they do, credentials)
- Personal opinion/review
- **All social media platforms as icon buttons/badges** within the same card

## Requirements

### 1. Create New Domain Models

#### ContentCreator Record/Class
Create a `ContentCreator` record (or class) with:
- `name` (String) - Creator's full name
- `description` (String) - Professional bio/credentials
- `myOpinion` (String, nullable) - Personal review/thoughts
- `profileImageUrl` (String, nullable) - Primary profile picture
- `socialLinks` (List<SocialLink>) - All platform links

#### SocialLink Record/Class
Create a `SocialLink` record (or class) with:
- `platform` (String or Enum) - Platform name (youtube, instagram, tiktok, twitter, etc.)
- `url` (String) - Direct link to the creator's profile
- `icon` (String) - Bootstrap icon class (e.g., "bi-youtube", "bi-instagram")

**Alternative Enum Approach:**
Consider using a `SocialPlatform` enum with built-in icon mappings:
```java
public enum SocialPlatform {
    YOUTUBE("bi-youtube"),
    INSTAGRAM("bi-instagram"),
    TIKTOK("bi-tiktok"),
    TWITTER("bi-twitter-x"),
    LINKEDIN("bi-linkedin"),
    FACEBOOK("bi-facebook"),
    TWITCH("bi-twitch"),
    PODCAST("bi-mic-fill");
    
    private final String iconClass;
    // constructor, getter
}
```


### 2. Refactor HomeController

Update `getChannelsForCategory()` (consider renaming to `getCreatorsForCategory()`) to:
- Return `List<ContentCreator>` instead of `List<Channel>`
- Group multiple platform entries by creator
- Each creator should have all their social platforms in the `socialLinks` list

**Example structure:**
```java
ContentCreator ariesTerron = new ContentCreator(
    "Aries Terrón",
    "Licenciado en Nutrición y Nutriólogo Deportivo...",
    "Canal con un tono humorístico, irónico...",
    "https://yt3.googleusercontent.com/...",
    List.of(
        new SocialLink("youtube", "https://www.youtube.com/@AriesTerron", "bi-youtube"),
        new SocialLink("instagram", "https://www.instagram.com/ariesterron", "bi-instagram"),
        new SocialLink("tiktok", "https://www.tiktok.com/@ariesterron", "bi-tiktok")
    )
);
```


### 3. Update Thymeleaf Template (channels.html)

Redesign the card layout to show:
- **Header**: Profile image + Creator name
- **Body**: Bio/description + personal opinion
- **Footer**: Social platform icons as clickable buttons/badges

**Key changes needed:**
- Remove the single platform badge from the header
- Add a social links section with **icon buttons for each platform**
- Each icon should be clickable and open in a new tab
- Style icons horizontally as a button group or badge group

**Visual Example:**
```
┌─────────────────────────────────┐
│ [Profile Img]  Aries Terrón     │
│                Nutrition Expert  │
├─────────────────────────────────┤
│ Description: Licensed nutritionist...│
│                                 │
│ Mi opinión: Humorous, evidence-based...│
├─────────────────────────────────┤
│ Seguir en:                      │
│ [YT] [IG] [TikTok] [Twitter]   │
└─────────────────────────────────┘
```


### 4. Suggested HTML Structure

```html
<div class="social-links mt-3">
    <p class="text-muted small mb-2">Seguir en:</p>
    <div class="d-flex gap-2 flex-wrap">
        <a th:each="link : ${creator.socialLinks}"
           th:href="${link.url}"
           target="_blank"
           rel="noopener noreferrer"
           class="btn btn-sm btn-outline-primary"
           th:title="${link.platform}">
            <i th:class="'bi ' + ${link.icon}"></i>
            <span class="ms-1 text-capitalize" th:text="${link.platform}"></span>
        </a>
    </div>
</div>
```


### 5. Model Attribute Naming
Consider renaming:
- Model attribute from `channels` to `creators` or `contentCreators`
- Fragment name from `channels-list` to `creators-list`
- CSS classes from `channel-card` to `creator-card`

### 6. Benefits of This Refactoring
- ✅ **Better UX**: Users see all platforms at once
- ✅ **Less redundancy**: No duplicate creator information
- ✅ **Cleaner data model**: Logical grouping by person
- ✅ **Easier maintenance**: Add/remove platforms without new cards
- ✅ **More scalable**: Easy to add new platforms
- ✅ **Better semantics**: Represents real-world relationships

### 7. Optional Enhancements
- Add a "primary platform" indicator (e.g., highlight YouTube if that's their main channel)
- Show follower counts per platform (if available)
- Add platform-specific tooltips (e.g., "Watch on YouTube")
- Implement platform filtering (show only creators with YouTube channels)
- Add a "verified" badge for established creators

## Expected Outcome
After refactoring:
1. Each creator appears exactly **once** in the recommendations
2. All their social platforms are visible and accessible from a **single card**
3. The data model accurately represents person → platforms relationship
4. The UI is cleaner and more user-friendly
5. Adding new platforms or creators is straightforward

Please implement this refactoring using Java records, following Spring Boot and Thymeleaf best practices.