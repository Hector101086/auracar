package com.hbs.auracar.service;

import com.hbs.auracar.repository.CountDownRepository;
import com.hbs.auracar.repository.entity.CountDownEntity;
import com.hbs.auracar.service.dto.CountDownDto;
import org.springframework.stereotype.Service;

@Service
public class CountDownService {

    private CountDownRepository countDownRepository;

    public CountDownService(CountDownRepository countDownRepository) {
        this.countDownRepository = countDownRepository;
    }

    public CountDownDto save(CountDownDto countDownDto) {
        CountDownEntity countDownEntity = new CountDownEntity();//Mapper
        countDownRepository.save(countDownEntity);
        CountDownDto result = new CountDownDto();//Mapper
        return result;

    }

    public void delete(CountDownDto countDownDto) {
        CountDownEntity countDownEntity = new CountDownEntity();//Mapper
        countDownRepository.delete(countDownEntity);
    }
}
