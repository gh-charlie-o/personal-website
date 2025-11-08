package uy.com.antel.sandbox.carloso.carlosowebsite.services;

import uy.com.antel.sandbox.carloso.carlosowebsite.model.ContentCreatorDTO;

import java.util.List;

public interface ContentCreatorService {
    List<ContentCreatorDTO> getCreatorsForCategory(String category);
}
