package com.danhpahm.identity_service.mapper;

import com.danhpahm.identity_service.dto.request.PermissionRequest;
import com.danhpahm.identity_service.dto.response.PermissionResponse;
import com.danhpahm.identity_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);

}
