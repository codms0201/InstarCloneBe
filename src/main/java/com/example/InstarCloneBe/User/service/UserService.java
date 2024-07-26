package com.example.InstarCloneBe.User.service;

import com.example.InstarCloneBe.User.dto.UserDto;
import com.example.InstarCloneBe.User.entity.UserEntity;
import com.example.InstarCloneBe.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    @Transactional
    public void createUser(UserDto.Create dto) {
        UserEntity user = userRepo.save(UserEntity.localToEntity(dto));
    }

    @Transactional
    public UserEntity createOrUpdateUser(String email) {
        Optional<UserEntity> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isPresent()) {
            log.info("\uD83D\uDCCD user already is made up");
            return optionalUser.get();
        } else {
            UserEntity user = UserEntity.builder()
                    .email(email)
                    .build();
            log.info("\uD83D\uDCCD user make up first");
            return userRepo.save(user);
        }
    }

    public UUID readByEmail(String email){
        UserEntity user = userRepo.findByEmail(email).orElseThrow();
        return user.getId();
    }

    public String readNickNameByID(UUID userId){
        UserEntity user = userRepo.findById(userId).orElseThrow();
        return user.getNickname();
    }

    public UserDto.Read readById(UUID userId){
        UserEntity user = userRepo.findById(userId).orElseThrow();
        return new UserDto.Read(user);
    }

    @Transactional
    public void updateUser(UUID id, String name, String petName) {
        UserEntity user = userRepo.findById(id).orElseThrow();
        user.localToUpdate(name, petName);
        userRepo.save(user);
    }

    @Transactional
    public void deleteUser(UUID id){
        Optional<UserEntity> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            userRepo.delete(user);
        }
    }

    public boolean validateUser(String email, String password) {
        UserEntity user = userRepo.findByEmail(email).orElseThrow();
        return (user.getPassword().equals(password));
    }

    public boolean userExists(String email) {
        return userRepo.existsByEmail(email);
    }
}
