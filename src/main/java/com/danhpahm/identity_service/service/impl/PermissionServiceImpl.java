package com.danhpahm.identity_service.service.impl;

import com.danhpahm.identity_service.dto.request.PermissionRequest;
import com.danhpahm.identity_service.dto.response.PermissionResponse;
import com.danhpahm.identity_service.entity.Permission;
import com.danhpahm.identity_service.mapper.PermissionMapper;
import com.danhpahm.identity_service.repository.PermissionRepository;
import com.danhpahm.identity_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {

    PermissionRepository permissionRepository;

    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);    }

    @Override
    public List<PermissionResponse> getAll() {
        return permissionRepository.findAll()
                .stream()
                .map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
