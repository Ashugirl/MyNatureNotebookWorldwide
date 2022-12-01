package be.avivaCode.MyNatureNotebookWorldwide.service;

import be.avivaCode.MyNatureNotebookWorldwide.data.Role;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.dto.UserDto;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.RoleRepository;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements iUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(){
    }
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        //encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    public void updateUser(UserDto userDto){
        User user = userRepository.findByEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    public void deleteUser(User user){
//        User user = userRepository.findByEmail(userDto.getEmail());
       System.out.println("SERVICE DELETE METHOD " + user);
       userRepository.delete(user);

    }
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getUserName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUserName(String userName){
        return userRepository.findUserByUserName(userName);
    }


//    public void update(Optional<User> updateUser) {
//        userRepository.findById(updateUser.getId())
//                .ifPresent(user1 -> {
//                    user1.setUserName(updateUser.getUserName());
//
//                    user1.setEmail(updateUser.getEmail());
//                    user1.setPassword(updateUser.getPassword());
//
//                    userRepository.save(user1);
//                });
//
//
//    }


//
//    public User createUser(User user){
//        if(user.isPasswordsMatch()){
//            return userRepository.save(user);
//        } else{
//            throw new RuntimeException("Passwords don't match.");
//        }}
//
//    public User getUserById(Long id){
//        User user = userRepository.findById(id).get();
//        return user;
//    }
//
//    public User getUserByUserName(String userName){
//        Optional<User> user = userRepository.findUserByUserName(userName);
//        if(user.isPresent()){
//            return user.get();
//        }
//        return null;
//    }
//
//    public User getCurrentUser(Long userId) {
//        Optional user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            return (User) user.get();
//        }
//        return null;
//    }
//
//
//    public void updateUser(User user){
//        userRepository.findById(user.getUserId())
//                .ifPresent(updatedUser -> {
//                    updatedUser.setEmail(user.getEmail());
//                    updatedUser.setPassword(user.getPassword());
//                    updatedUser.setPasswordCheck(user.getPasswordCheck());
//
//                    userRepository.save(updatedUser);
//                });
//    }
//
//    public void deleteUser(Long userId){
//        userRepository.deleteById(userId);
//    }
//
//    public User authenticateUser(String userName, String password){
//        return userRepository.findUserByUserNameAndPassword(userName, password).orElse(null);
//    }
}
