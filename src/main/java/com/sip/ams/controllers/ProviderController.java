package com.sip.ams.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;
import com.sip.ams.services.ProviderService;

@RestController
@RequestMapping("providers")
@CrossOrigin("*")
public class ProviderController {
	
	//@Autowired
	//ProviderRepository providerRepository;
	
	@Autowired
	ProviderService providerService;
		
	@GetMapping("/")
	@Operation(
	        summary = "Récupération de tous les providers",
	        description = "Cette méthode retourne la liste de tous les providers."
	    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Succès de get All"),
        @ApiResponse(responseCode = "404", description = "provider non trouvé"),
        @ApiResponse(responseCode = "500", description = "Problème lors de la récupération, Erreur interne du serveur")
    })
	
	public ResponseEntity<List<Provider>> getAllProviders()
	{
		return new ResponseEntity<>(this.providerService.getAllProviders(), HttpStatus.OK);
	}
	
	@PostMapping("/")
	@Operation(
	        summary = "Ajout d'un nouvel provider",
	        description = "Cette méthode retourne la liste de tous les providers."
	    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Insertion avec Succès"),
        @ApiResponse(responseCode = "500", description = "Erreur lors de l'insertion, le serveur")
    })
	public ResponseEntity<Provider> saveProvider(@RequestBody Provider p)
	{
		//Provider p = new Provider("HP", "hp@gmail.com", "USA");
		return new ResponseEntity<>(this.providerService.saveProvider(p), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@Operation(
	        summary = "Recherche d'un provider par son id",
	        description = "Cette méthode retourne un unique provider."
	    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Si provider est trouvé"),
        @ApiResponse(responseCode = "404", description = "Provider inexistant")
    })
	public ResponseEntity<Provider> getProviderById(@PathVariable int id)
	{
		Optional<Provider> opt = this.providerService.getProviderById(id);
		
		if(opt.isEmpty())
			return  ResponseEntity.notFound().build();
		else
			return new ResponseEntity<>(opt.get(), HttpStatus.OK); // code 200
	}
	
	
	@DeleteMapping("/{id}")
	@Operation(
	        summary = "Suppression d'un provider par son id",
	        description = "Cette méthode Supprime un unique provider via son id."
	    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Si provider est trouvé puis supprimé"),
        @ApiResponse(responseCode = "404", description = "Provider inexistant")
    })
	public ResponseEntity<Provider> deleteProviderById(@PathVariable int id)
	{
		//Optional<Provider> opt = providerRepository.findById(provider.getId());
		
		Optional<Provider> opt = this.providerService.getProviderById(id);
		
		if(opt.isEmpty())
			return ResponseEntity.notFound().build();    // code 404
		else {
			this.providerService.deleteProviderById(id);
			return ResponseEntity.noContent().build();   // code 204
		}
	}
	
	
	@PutMapping("/")
	@Operation(
	        summary = "Mise à jour d'un provider",
	        description = "Cette méthode permet de mettre à jour un provider."
	    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Si mise à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Provider inexistant")
    })
	public ResponseEntity<Provider> updateProvider(@RequestBody Provider provider)
	{
		
		//Optional<Provider> opt = providerRepository.findById(provider.getId());
		
		Optional<Provider> opt = this.providerService.getProviderById(provider.getId());
		
		if(opt.isEmpty())
			return ResponseEntity.notFound().build();    // code 404
		else {
			Provider savedProvider = opt.get();
			savedProvider.setName(provider.getName());
			savedProvider.setAddress(provider.getAddress());
			savedProvider.setEmail(provider.getEmail());
			return new ResponseEntity<>(this.providerService.saveProvider(savedProvider), HttpStatus.OK);   // code 204
		}
	}
	
	
	
}
