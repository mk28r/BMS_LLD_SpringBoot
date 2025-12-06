package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.User;
import org.example.bookmyshow_lld.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
     return  this.userRepository.save(user);
    }
}
