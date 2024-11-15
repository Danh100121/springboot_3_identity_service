package com.danhpahm.identity_service.service;

import com.danhpahm.identity_service.dto.request.RoleRequest;
import com.danhpahm.identity_service.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    void delete(String role);
}
