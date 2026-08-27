package com.bear.onestop.services;

import com.bear.onestop.data.CreateDogRequest;
import com.bear.onestop.data.entities.Dog;

public interface DogService {
    Dog createDog(String chiefstaffId, CreateDogRequest dog);
}
