package com.msusers.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.stereotype.Repository;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;

@Slf4j
@Repository
@RequiredArgsConstructor
public class KeycloakRepository implements UserRepository {

    private static final String SERVER_URL = "http://localhost:8090/";
    private static final String REALM = "avaliacao";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "user";
    private static final String CLIENT_ID = "gateway";

        Keycloak keycloak = KeycloakBuilder
                .builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .username(USERNAME)
                .password(PASSWORD)
                .clientId(CLIENT_ID)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();
    @Override
    public UserResource findById(String id) {
        UsersResource usersResource = keycloak.realm(REALM).users();
        UserResource userResource = usersResource.get(id);
        return userResource;
    }
}
