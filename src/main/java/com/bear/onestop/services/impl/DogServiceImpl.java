package com.bear.onestop.services.impl;

import com.bear.onestop.data.CreateDogRequest;
import com.bear.onestop.data.entities.Dog;
import com.bear.onestop.data.entities.DogImageDetail;
import com.bear.onestop.data.entities.User;
import com.bear.onestop.exception.UserNotFoundException;
import com.bear.onestop.repositories.DogRepository;
import com.bear.onestop.repositories.UserRepository;
import com.bear.onestop.services.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final UserRepository userRepository;
    private final DogRepository dogRepository;

    @Override
    public Dog createDog(String staffClerkId, CreateDogRequest dog){
        User staff = userRepository.findByClerkId(staffClerkId)
                .orElseThrow(()-> new UserNotFoundException(
                        String.format("User with ID '%s' not found", staffClerkId)
                ));

        Dog dogToCreate = new Dog();
        List<DogImageDetail> dogImageDetailsToCreate = dog.getImages().stream().map(
                img -> {
                    DogImageDetail dogImage = new DogImageDetail();
                    dogImage.setImgsrc(img.getImgsrc());
                    dogImage.setAlttext(img.getAlttext());
                    dogImage.setDog(dogToCreate);
                    return dogImage;
                }).toList();
        dogToCreate.setName(dog.getName());
        dogToCreate.setSlug(dog.getSlug());
        dogToCreate.setDescription(dog.getDescription());
        dogToCreate.setAge(dog.getAge());
        dogToCreate.setSize(dog.getSize());
        dogToCreate.setBreed(dog.getBreed());
        dogToCreate.setSex(dog.getSex());
        dogToCreate.setImages(dogImageDetailsToCreate);
        dogToCreate.setFeatureTag(dog.getFeatureTag());
        dogToCreate.setStatus(dog.getStatus());
        dogToCreate.setKennelLocation(dog.getKennelLocation());
        dogToCreate.setIsPublished(dog.getIsPublished());
        return dogRepository.save(dogToCreate);
    }

    @Override
    public Page<Dog> listDogs(Pageable pageable) {
        return dogRepository.findAll(pageable);
    }

    @Override
    public Optional<Dog> getDogDetails(UUID id) {

        return dogRepository.findById(id);
    }
}
