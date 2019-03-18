package com.youfan.dao;

import com.youfan.mapper.MechantMapper;
import com.youfan.model.Mechant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Component
public class MechantDao {
    @Autowired
    MechantMapper mechantMapper;

    public void insertMechant(Mechant mechant){
        mechantMapper.insertMechant(mechant);
    }

    public void upateMechant(Mechant mechant){
        mechantMapper.upateMechant(mechant);
    }
    public Mechant findMechantbyid(int id){
        return mechantMapper.findMechantbyid(id);
    }

    public void upateMechantstatus(Mechant mechant){
        mechantMapper.upateMechantstatus(mechant);
    }

    public void upatesoldout(Mechant mechant){
        mechantMapper.upatesoldout(mechant);
    }
}
