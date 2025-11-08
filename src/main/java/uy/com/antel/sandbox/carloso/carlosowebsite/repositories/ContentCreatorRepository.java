package uy.com.antel.sandbox.carloso.carlosowebsite.repositories;

import uy.com.antel.sandbox.carloso.carlosowebsite.domain.ContentCreator;

import java.util.List;

public interface ContentCreatorRepository {
    List<ContentCreator> findAll();

    List<ContentCreator> findCreatorsForCategory(String category);

    void save(ContentCreator contentCreator);
}
