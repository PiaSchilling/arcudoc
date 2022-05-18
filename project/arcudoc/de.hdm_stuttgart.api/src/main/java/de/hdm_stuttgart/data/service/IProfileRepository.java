package de.hdm_stuttgart.data.service;

import de.hdm_stuttgart.data.model.Profile;

public interface IProfileRepository {
    void fetchProfile(String refreshToken);
}
