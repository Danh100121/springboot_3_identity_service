package com.danhpahm.identity_service.service.impl;

import com.danhpahm.identity_service.dto.request.RoleRequest;
import com.danhpahm.identity_service.dto.response.RoleResponse;
import com.danhpahm.identity_service.mapper.RoleMapper;
import com.danhpahm.identity_service.repository.PermissionRepository;
import com.danhpahm.identity_service.repository.RoleRepository;
import com.danhpahm.identity_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;

    PermissionRepository permissionRepository;

    RoleMapper roleMapper;
    @Override
    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse).toList();
    }

    @Override
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
