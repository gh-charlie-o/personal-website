package uy.com.antel.sandbox.carloso.carlosowebsite.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ContentCreator implements Serializable {
    private String name;
    private String description;
    private String personalDescription;
    private String profileImageUrl;
    private List<Channel> channels;
}
