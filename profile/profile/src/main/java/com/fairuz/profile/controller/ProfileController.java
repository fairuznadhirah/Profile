package com.fairuz.profile.controller;

import com.fairuz.profile.model.Profile;
import com.fairuz.profile.repo.ProfileRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ProfileController {

    private final ProfileRepo profileRepo;

    @GetMapping("/getProfile")
    public ResponseEntity<List<Profile>> getAllProfile(){
        try {
            List<Profile> profileList = new ArrayList<>();
            profileRepo.findAll().forEach(profileList::add);

            if (profileList.isEmpty()) {
                return new ResponseEntity<>(profileList, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getProfileById/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id){
        Optional<Profile> profileData = profileRepo.findById(id);

        if(profileData.isPresent()){
            return new ResponseEntity<>(profileData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addProfile")
    public ResponseEntity<Profile> addProfile(@RequestBody Profile profile){
        Profile profile1 = profileRepo.save(profile);

        return new ResponseEntity<>(profile1, HttpStatus.OK);

    }

    @PutMapping("/updateProfileById/{id}")
    public ResponseEntity<Profile> updateProfileById(@PathVariable Long id, @RequestBody Profile newProfileData){
        Optional<Profile> oldProfileData = profileRepo.findById(id);

        if(oldProfileData.isPresent()){
           Profile updatedProfile = oldProfileData.get();
           updatedProfile.setName(newProfileData.getName());
           updatedProfile.setAge(newProfileData.getAge());
           updatedProfile.setDOB(newProfileData.getDOB());
           updatedProfile.setState(newProfileData.getState());

           Profile profile1 = profileRepo.save(updatedProfile);
           return new ResponseEntity<>(profile1, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteProfileById/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        profileRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
