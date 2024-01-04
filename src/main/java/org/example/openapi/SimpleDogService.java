package org.example.openapi;

import org.example.openapi.api.DogsApiDelegate;
import org.example.openapi.dto.DogDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SimpleDogService implements DogsApiDelegate {
    @Override
    public ResponseEntity<DogDto> createDog(DogDto dogDto) {
        DogDto dog = new DogDto().name(dogDto.getName()).age(dogDto.getAge());
        return ResponseEntity.ok(dog);
    }
}
