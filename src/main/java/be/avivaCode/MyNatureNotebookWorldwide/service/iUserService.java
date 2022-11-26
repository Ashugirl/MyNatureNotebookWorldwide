package be.avivaCode.MyNatureNotebookWorldwide.service;

import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.dto.UserDto;
import java.util.List;



public interface iUserService {


        void saveUser(UserDto userDto);

        User findUserByEmail(String email);

        List<UserDto> findAllUsers();

       // void deleteUser(UserDto userDto);
    }
