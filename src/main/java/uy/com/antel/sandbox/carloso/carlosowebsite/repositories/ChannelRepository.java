package uy.com.antel.sandbox.carloso.carlosowebsite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.com.antel.sandbox.carloso.carlosowebsite.entities.Channel;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    List<Channel> findByNameContainingIgnoreCase(String q);
}
