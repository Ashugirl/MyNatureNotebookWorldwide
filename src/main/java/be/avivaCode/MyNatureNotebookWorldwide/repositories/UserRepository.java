package be.avivaCode.MyNatureNotebookWorldwide.repositories;

import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // List<User> findAll();
    User findByEmail(String email);
    Optional<User> findById(Long id);
   // Optional<User> findByName(String name);
    //Optional<User> findUserById(Long userId);
    Optional<User> findUserByUserName(String userName);
//    Optional<User> findUserByUserNameAndPassword(String userName, String password);
}