package com.javaspringboot.petclinic.dao;


import java.util.List;

import com.javaspringboot.petclinic.model.Pet;

public interface PetRepository {
	Pet findById(Long id);
	List<Pet> findByOwner(Long ownerId);
	void  create(Pet pet);
	Pet update(Pet pet);
	void delete();
	void deleteByOwnerId(Long ownerId);
	
}
