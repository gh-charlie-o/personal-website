package uy.com.antel.sandbox.carloso.carlosowebsite.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.Channel;
import uy.com.antel.sandbox.carloso.carlosowebsite.repositories.ChannelRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;

    public ChannelServiceImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    @Transactional
    public Channel create(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public Optional<Channel> findById(Long id) {
        return channelRepository.findById(id);
    }

    @Override
    public Page<Channel> findAllPaged(Pageable pageable) {
        return channelRepository.findAll(pageable);
    }

    @Override
    public List<Channel> searchByName(String q) {
        return channelRepository.findByNameContainingIgnoreCase(q);
    }

    @Override
    @Transactional
    public Channel update(Long id, Channel channel) {
        Channel existing = channelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Channel not found: " + id));
        existing.setName(channel.getName());
        existing.setUrl(channel.getUrl());
        existing.setPlatform(channel.getPlatform());
        existing.setDescription(channel.getDescription());
        existing.setImageUrl(channel.getImageUrl());
        existing.setCategories(channel.getCategories());
        return channelRepository.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        channelRepository.deleteById(id);
    }
}
