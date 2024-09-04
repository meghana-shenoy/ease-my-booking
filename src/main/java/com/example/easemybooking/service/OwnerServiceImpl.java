package com.example.easemybooking.service;

import com.example.easemybooking.model.Destination;
import com.example.easemybooking.model.Owners;
import com.example.easemybooking.repo.OwnersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnersRepo ownerRepo;

    @Override
    public Owners findById(int Oid){ return ownerRepo.findById(Oid).get();
    }

    @Override
    public List<Owners> findAll(){
        return ownerRepo.findAll();
    }

    @Override
    public Owners addOwner(Owners owner){
        ownerRepo.save(owner);
        System.out.println("OWNER AFTER SAVING" +owner);
        return owner;
    }
    @Override
    public Owners updateOwner(Owners owner){
        if(ownerRepo.existsById(owner.getOwnerid())){
            ownerRepo.save(owner);
        }
        else
        {
            throw new RuntimeException("not found");

        }
        return owner;
    }

    @Transactional
    @Override
    public void removeById(int Ownerid){
        ownerRepo.deleteById(Ownerid);
    }


}