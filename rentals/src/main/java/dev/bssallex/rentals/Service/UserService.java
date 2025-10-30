package dev.bssallex.rentals.Service;

import dev.bssallex.rentals.Repository.UserRepository;
import dev.bssallex.rentals.dtos.mapper.UserMapper;
import dev.bssallex.rentals.dtos.request.UserRequest;
import dev.bssallex.rentals.dtos.response.UserResponse;
import dev.bssallex.rentals.entity.User;
import dev.bssallex.rentals.exceptions.UserNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserResponse> findAllUsers(){
        List<User> users = repository.findAll();
        return users
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    public UserResponse findUserById(Long id){
        User user = repository.findById(id).orElseThrow(() -> new UserNotFound("Usuário não encontrado."));
        return UserMapper.toResponse(user);
    }

    public UserResponse createUser(UserRequest request){
        User savedUser = repository.save(UserMapper.toRequest(request));
        return UserMapper.toResponse(savedUser);
    }

    public UserResponse updateUser(Long id, UserRequest request){
        User user = repository.findById(id).orElseThrow(() -> new UserNotFound("Usuário não encontrado."));

        user.setName(request.name());
        user.setEmail(request.email());

        User updatedUser = repository.save(user);
        return UserMapper.toResponse(updatedUser);
    }

    public void deleteUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFound("Usuário não encontrado."));
        repository.delete(user);
    }


}
