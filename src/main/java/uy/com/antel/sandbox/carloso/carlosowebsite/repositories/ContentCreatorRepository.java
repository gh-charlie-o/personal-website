package uy.com.antel.sandbox.carloso.carlosowebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.ContentCreator;
import uy.com.antel.sandbox.carloso.carlosowebsite.enums.ChannelCategory;

import java.util.List;
import java.util.Optional;

public interface ContentCreatorRepository extends JpaRepository<ContentCreator, Long> {

    Optional<ContentCreator> findByEmail(String email);

    @Query("select distinct cc from ContentCreator cc join cc.channels ch join ch.categories cat where cat = :category")
    List<ContentCreator> findDistinctByCategory(@Param("category") ChannelCategory category);
}
