package be.avivaCode.MyNatureNotebookWorldwide.service;

import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        if(user.isPasswordsMatch()){
            return userRepository.save(user);
        } else{
            throw new RuntimeException("Passwords don't match.");
        }}

    public User getUserById(Long id){
        User user = userRepository.findById(id).get();
        return user;
    }

    public User getUserByUserName(String userName){
        Optional<User> user = userRepository.findUserByUserName(userName);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    public User getCurrentUser(Long userId) {
        Optional user = userRepository.findById(userId);
        if (user.isPresent()) {
            return (User) user.get();
        }
        return null;
    }


    public void updateUser(User user){
        userRepository.findById(user.getUserId())
                .ifPresent(updatedUser -> {
                    updatedUser.setEmail(user.getEmail());
                    updatedUser.setPassword(user.getPassword());
                    updatedUser.setPasswordCheck(user.getPasswordCheck());

                    userRepository.save(updatedUser);
                });
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public User authenticateUser(String userName, String password){
        return userRepository.findUserByUserNameAndPassword(userName, password).orElse(null);
    }
}
