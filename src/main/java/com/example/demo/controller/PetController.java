package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.model.Pet;
import com.example.demo.model.Tag;

import io.swagger.v3.oas.annotations.parameters.RequestBody;



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
    public List<Pet> findPetsByStatus(@RequestParam(required = false) List<String> status) {
    	if(status == null || status.isEmpty()) {
    		return pets;
    	}
        // 指定されたステータスでフィルタリング
        return pets.stream()
                .filter(pet -> status.contains(pet.getStatus()))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/pet/findByTags")
    public List<Pet> findPetByTag(@RequestParam(required = false) List<String> tagNames) {
        if (tagNames == null || tagNames.isEmpty()) {
            return pets;
        }
    	//指定されたステータスでフィルタリンぐ
        return pets.stream()
                .filter(pet -> pet.getTags().stream()
                        .anyMatch(tag -> tagNames.contains(tag.getName())))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/pet/{petId}")
    public Pet findPetById(@PathVariable Integer petId) {  	
    	return pets.stream()
    			.filter(pet -> pet.getId().equals(petId))
    			.findFirst()
    			.orElse(null);
    }
    
    @PostMapping("/pet")
    public ResponseEntity<Pet> add(@RequestBody Pet newPet) {
        // 受け取った新しいペットを表示
        System.out.println("Received Pet: " + newPet);

        // ペットリストに追加
        pets.add(newPet);

        // 201 (Created) ステータスとともに新しいペットを返す
        return ResponseEntity.status(HttpStatus.CREATED).body(newPet);
    }




    @PostMapping("/pet/{petId}")
    public ResponseEntity<Pet> updatePet(
            @PathVariable("petId") Integer petId,
            @RequestBody Pet updatedPetDetails) {

        // デバッグ出力: 受信したペットIDと詳細を表示
        System.out.println("Updating Pet with ID: " + petId);
        System.out.println("Updated details: " + updatedPetDetails);

        // 対象のペットを検索
        for (Pet pet : pets) {
            if (pet.getId().equals(petId)) {
                // ペット情報を更新
                if (updatedPetDetails.getName() != null && !updatedPetDetails.getName().isEmpty()) {
                    pet.setName(updatedPetDetails.getName());
                }
                if (updatedPetDetails.getStatus() != null && !updatedPetDetails.getStatus().isEmpty()) {
                    pet.setStatus(updatedPetDetails.getStatus());
                }

                // 更新後のペット情報を返す
                return ResponseEntity.ok(pet); 
            }
        }
        // 該当のペットが見つからない場合
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(null); // ペットが見つからない場合、404を返す
    }

    
    
    
    
}
