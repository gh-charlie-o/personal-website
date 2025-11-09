package uy.com.antel.sandbox.carloso.carlosowebsite.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uy.com.antel.sandbox.carloso.carlosowebsite.entities.Channel;

import java.util.List;
import java.util.Optional;

public interface ChannelService {
    Channel create(Channel channel);
    Optional<Channel> findById(Long id);
    Page<Channel> findAllPaged(Pageable pageable);
    List<Channel> searchByName(String q);
    Channel update(Long id, Channel channel);
    void delete(Long id);
}