package com.oussema.keylearn.keycloak;

public record KcUser(
    String id, String userName, String email, String firstName, String lastName, Actions action) {}
