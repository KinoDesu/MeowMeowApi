package dev.kinodesu.MeowMeowApi.model.user;

public record DTOMeowUser(Long id,
                          String name,
                          String email,
                          String documentNumber,
                          String phoneNumber,
                          Boolean active) {}
