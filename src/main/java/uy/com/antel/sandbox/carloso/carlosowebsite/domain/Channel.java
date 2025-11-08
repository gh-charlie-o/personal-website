package uy.com.antel.sandbox.carloso.carlosowebsite.domain;

import lombok.Data;
import uy.com.antel.sandbox.carloso.carlosowebsite.enums.ChannelCategory;
import uy.com.antel.sandbox.carloso.carlosowebsite.enums.SocialPlatform;

import java.io.Serializable;
import java.util.List;

@Data
public class Channel implements Serializable {
    private String name;
    private String url;
    private SocialPlatform platform;
    private String description;
    private String imageUrl;
    private List<ChannelCategory> categories;
}
