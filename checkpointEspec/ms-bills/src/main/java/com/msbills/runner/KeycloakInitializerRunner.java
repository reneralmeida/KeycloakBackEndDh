package com.msbills.runner;

import com.msbills.security.WebSecurityConfig;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class KeycloakInitializerRunner implements CommandLineRunner {

    private final Keycloak keycloakAdmin;

    @Override
    public void run(String... args) {
        log.info("Initializing '{}' realm in Keycloak ...", COMPANY_SERVICE_REALM_NAME);

        Optional<RealmRepresentation> representationOptional = keycloakAdmin.realms()
                .findAll()
                .stream()
                .filter(r -> r.getRealm().equals(COMPANY_SERVICE_REALM_NAME))
                .findAny();
        if (representationOptional.isPresent()) {
            log.info("Removing already pre-configured '{}' realm", COMPANY_SERVICE_REALM_NAME);
            keycloakAdmin.realm(COMPANY_SERVICE_REALM_NAME).remove();
        }

        // Realm
        RealmRepresentation realmRepresentation = new RealmRepresentation();
        realmRepresentation.setRealm(COMPANY_SERVICE_REALM_NAME);
        realmRepresentation.setEnabled(true);
        realmRepresentation.setRegistrationAllowed(true);

        // Client
        ClientRepresentation clientRepresentation = new ClientRepresentation();
        clientRepresentation.setClientId(APP_CLIENT_ID);
        clientRepresentation.setDirectAccessGrantsEnabled(true);
        clientRepresentation.setPublicClient(true);
        clientRepresentation.setRedirectUris(Collections.singletonList(APP_REDIRECT_URL));
        clientRepresentation.setDefaultRoles(new String[]{WebSecurityConfig.USER});
        realmRepresentation.setClients(Collections.singletonList(clientRepresentation));

        // Users
        List<UserRepresentation> userRepresentations = APP_USERS.stream()
                .map(userPass -> {
                    // User Credentials
                    CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
                    credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
                    credentialRepresentation.setValue(userPass.getPassword());

                    // User
                    UserRepresentation userRepresentation = new UserRepresentation();
                    userRepresentation.setUsername(userPass.getUsername());
                    userRepresentation.setEnabled(true);
                    userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
                    userRepresentation.setClientRoles(getClientRoles(userPass));

                    return userRepresentation;
                })
                .collect(Collectors.toList());
        realmRepresentation.setUsers(userRepresentations);

        // Create Realm
        keycloakAdmin.realms().create(realmRepresentation);

        // Testing
        UserPass admin = APP_USERS.get(0);
        log.info("Testing getting token for '{}' ...", admin.getUsername());

        Keycloak keycloakMovieApp = KeycloakBuilder.builder().serverUrl(KEYCLOAK_SERVER_URL)
                .realm(COMPANY_SERVICE_REALM_NAME).username(admin.getUsername()).password(admin.getPassword())
                .clientId(APP_CLIENT_ID).build();

        log.info("'{}' token: {}", admin.getUsername(), keycloakMovieApp.tokenManager().grantToken().getToken());
        log.info("'{}' initialization completed successfully!", COMPANY_SERVICE_REALM_NAME);
    }

    private Map<String, List<String>> getClientRoles(UserPass userPass) {
        List<String> roles = new ArrayList<>();
        roles.add(WebSecurityConfig.USER);
        if ("user1".equals(userPass.getUsername())) {
            roles.add(WebSecurityConfig.USER);
        }
        return Map.of(APP_CLIENT_ID, roles);
    }

    private static final String KEYCLOAK_SERVER_URL = "http://localhost:8082";
    private static final String COMPANY_SERVICE_REALM_NAME = "Checkpoint";
    private static final String APP_CLIENT_ID = "checkpoint";
    private static final String APP_REDIRECT_URL = "http://localhost:8090/*";
    private static final List<UserPass> APP_USERS = Arrays.asList(
            new UserPass("admin", "admin"),
            new UserPass("user1", "user1"));

    @Value
    private static class UserPass {
        String username;
        String password;
    }
}