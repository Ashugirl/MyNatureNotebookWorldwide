package be.avivaCode.MyNatureNotebookWorldwide.repositories;

import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  Optional<User> findById(Long id);

  Optional<User> findUserByUserName(String userName);

}