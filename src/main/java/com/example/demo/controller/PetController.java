package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.model.Pet;
import com.example.demo.model.Tag;



@RestController
public class PetController {

	List<Pet> pets = new ArrayList<>();
	
	//サンプルデータ
    public PetController() {
        pets.add(new Pet(1, new Category(1, "Dogs"), "doggie", List.of("url1"), List.of(new Tag(1, "friendly")), "available"));
        pets.add(new Pet(2, new Category(2, "Cats"), "kitty", List.of("url2"), List.of(new Tag(2, "shy")), "pending"));
        pets.add(new Pet(3, new Category(3, "Birds"), "parrot", List.of("url3"), List.of(new Tag(3, "colorful")), "sold"));
    }
    
    @GetMapping("/pet")
    public List<Pet> getPets(){
    	return pets;
    }
    
    
    @GetMapping("/pet/findByStatus")
    public List<Pet> findPetsByStatus(@RequestParam(required=false) List<String> status) {
    	if(status == null || status.isEmpty()) {
    		return pets;
    	}
        // 指定されたステータスでフィルタリング
        return pets.stream()
                .filter(pet -> status.contains(pet.getStatus()))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/pet/findByTags")
    public String hello3() {
    	return "hello /pet/findbytags";
    }
    
    @GetMapping("/pet/{petId}")
    public String hello4() {
    	return "/pet/petid";
    }
}