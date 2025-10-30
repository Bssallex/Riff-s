package dev.bssallex.rentals.dtos.mapper;

import dev.bssallex.rentals.dtos.request.UserRequest;
import dev.bssallex.rentals.dtos.response.UserResponse;
import dev.bssallex.rentals.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toRequest(UserRequest request){
        return User.
                builder()
                .name(request.name())
                .email(request.email())
                .build();
    }

    public static UserResponse toResponse(User user){
        return UserResponse.
                builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
