package fr.movieapp.controllers;

import fr.movieapp.controllers.requests.ProfileRequest;
import fr.movieapp.controllers.responses.ProfileResponse;
import fr.movieapp.exceptions.AlreadyExistsException;
import fr.movieapp.mappers.ToModelMapper;
import fr.movieapp.mappers.ToResponseMapper;
import fr.movieapp.services.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/profiles")
@CrossOrigin("http://localhost:5173")
public class ProfilesController {
    private final ProfileService profileService;

    public ProfilesController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponse>> getProfiles() {
        return ResponseEntity.ok()
                .body(ToResponseMapper.profilesToResponse(profileService.getProfiles()));
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> addProfile(@RequestBody ProfileRequest req) throws AlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ToResponseMapper.toResponse(
                        profileService.addProfile(ToModelMapper.toModel(req)))
                );
    }
}
