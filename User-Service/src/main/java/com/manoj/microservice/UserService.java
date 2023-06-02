package com.manoj.microservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class UserService {

    UserResponse userResponse;
    List<User> users;
    public UserService() {
        userResponse = new UserResponse();
        users = Arrays.asList(new User(1, "Manoj Basnet", "9843242332", "Kalopul"),
                new User(2, "Deepak Saud", "9801245369", "Kapan"),
                new User(3, "Ganesh Lama", "9841225647", "Thamel"));
        userResponse.setUsers(users);
    }
    public UserResponse getUsers(){
        return userResponse;
    }
    public User getUser(Integer id){
        List<User> oneUser=users.stream().filter(user -> user.getId()==id).collect(Collectors.toList());
        return oneUser.get(0);
    }
}
