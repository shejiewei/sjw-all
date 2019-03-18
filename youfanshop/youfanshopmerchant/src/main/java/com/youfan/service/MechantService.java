package com.youfan.service;

import com.youfan.dao.MechantDao;
import com.youfan.mapper.MechantMapper;
import com.youfan.model.Mechant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Service
public class MechantService {

    @Autowired
    MechantDao mechantDao;

    public void insertMechant(Mechant mechant){
        mechant.setAuditstatus(1);
        mechant.setSoldout(1);
        mechantDao.insertMechant(mechant);
    }

    public void upateMechant(Mechant mechant){
        mechant.setSoldout(1);
        mechantDao.upateMechant(mechant);
    }

    public Mechant findMechantbyid(int id){
        return mechantDao.findMechantbyid(id);
    }

    public void upateMechantstatus(int status,int id){
        Mechant mechant = new Mechant();
        mechant.setId(id);
        mechant.setAuditstatus(status);
        mechantDao.upateMechantstatus(mechant);
    }

    public void upatesoldout(int status,int id){
        Mechant mechant = new Mechant();
        mechant.setId(id);
        mechant.setSoldout(status);
        mechantDao.upatesoldout(mechant);
    }

}
