package com.example.easemybooking.service;

import com.example.easemybooking.model.Owners;

import java.util.List;

public interface OwnerService {
    Owners findById(int ownerId);
    List<Owners> findAll();
    Owners addOwner(Owners owner);
    Owners updateOwner(Owners owner);
    void removeById(int ownerId);
}