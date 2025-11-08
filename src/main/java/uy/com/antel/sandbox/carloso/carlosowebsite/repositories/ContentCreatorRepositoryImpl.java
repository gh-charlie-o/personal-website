package uy.com.antel.sandbox.carloso.carlosowebsite.repositories;

import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Component;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.ContentCreator;
import uy.com.antel.sandbox.carloso.carlosowebsite.enums.ChannelCategory;

import java.util.List;

@Component
@NullMarked
public class ContentCreatorRepositoryImpl implements ContentCreatorRepository {
    private final ContentCreators contentCreators;

    public ContentCreatorRepositoryImpl(final ContentCreators contentCreators) {
        this.contentCreators = contentCreators;
    }

    public void addContentCreator(final ContentCreator contentCreator) {
        this.contentCreators.add(contentCreator);
    }

    @Override
    public List<ContentCreator> findAll() {
        return contentCreators.findAll();
    }

    @Override
    public List<ContentCreator> findCreatorsForCategory(String category) {
        return this.contentCreators.findAll().stream()
                .filter(creator -> creator.getChannels().stream()
                        .anyMatch(channel -> channel.getCategories().contains(ChannelCategory.valueOf(category.toUpperCase()))))
                .toList();
    }

    @Override
    public void save(ContentCreator contentCreator) {
        this.contentCreators.add(contentCreator);
    }
}
