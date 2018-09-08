package com.lysenkova.soapshop.service;

import java.util.Optional;

public interface SecurityService {
    Optional<String> getToken(String login, String password);
}
