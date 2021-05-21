package com.codecool.woofWoofCar.RideDetails;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AnimalTypeService {
    private  final AnimalTypeRepository animalTypeRepository;

    @Transactional
    public List<AnimalType> getAllAnimalTypes() {
        return animalTypeRepository.findAll();
    }

    public AnimalType getAnimalTypeById(long id) {
        return animalTypeRepository.getOne(id);
    }
}
