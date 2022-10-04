package com.msusers.repository;

import org.keycloak.admin.client.resource.UserResource;

public interface UserRepository {
    UserResource findById(String id);
}
