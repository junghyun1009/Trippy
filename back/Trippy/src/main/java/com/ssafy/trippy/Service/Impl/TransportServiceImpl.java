package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.Transport;
import com.ssafy.trippy.Repository.TransportRepository;
import com.ssafy.trippy.Service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;

    @Transactional
    @Override
    public Long saveTransport(Transport transport) {
        return transportRepository.save(transport).getId();
    }
}
