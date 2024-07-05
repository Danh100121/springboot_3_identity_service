package com.danhpahm.identity_service.mapper;

import com.danhpahm.identity_service.dto.request.UserCreationRequest;
import com.danhpahm.identity_service.dto.request.UserUpdateRequest;
import com.danhpahm.identity_service.dto.response.UserResponse;
import com.danhpahm.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(source = "firstName", target = "lastName")
//    @Mapping(target = "lastName", ignore = true)
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
