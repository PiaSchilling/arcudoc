package de.hdm_stuttgart.data.service;

public interface IProfileRepository {
    void fetchProfileAsync();
    void fetchProfileSync();
}
