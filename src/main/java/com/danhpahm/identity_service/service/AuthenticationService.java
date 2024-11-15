package com.danhpahm.identity_service.service;

import com.danhpahm.identity_service.dto.request.AuthenticationRequest;
import com.danhpahm.identity_service.dto.request.IntrospectRequest;
import com.danhpahm.identity_service.dto.request.LogoutRequest;
import com.danhpahm.identity_service.dto.request.RefreshRequest;
import com.danhpahm.identity_service.dto.response.AuthenticationResponse;
import com.danhpahm.identity_service.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    public AuthenticationResponse authenticate(AuthenticationRequest request);

    public void logout(LogoutRequest request) throws ParseException, JOSEException;

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;

}
