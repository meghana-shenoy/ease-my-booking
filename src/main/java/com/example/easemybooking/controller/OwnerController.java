package com.example.easemybooking.controller;

import com.example.easemybooking.model.Destination;
import com.example.easemybooking.model.Owners;
import com.example.easemybooking.service.DestinationService;
import com.example.easemybooking.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Component
@RestController
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    @GetMapping("/owner/{id}")
    public Owners findbyoid(@PathVariable("id") int id) {
        return ownerService.findById(id);
    }

    @GetMapping("/owners")
    public List<Owners> findAllOwners() {
        return ownerService.findAll();
    }

    @PostMapping("/owner/add")
    public Owners addOwners(@RequestBody Owners owner) {
        return ownerService.addOwner(owner);
    }

    @PutMapping("/owner/update")
    public Owners updateTheOwner(@RequestBody Owners owner) {
        return ownerService.updateOwner(owner);
    }

    @DeleteMapping("/owner/delete/{id}")
    public void removeByOId(@PathVariable("id") int id) {
        ownerService.removeById(id);
    }

}

