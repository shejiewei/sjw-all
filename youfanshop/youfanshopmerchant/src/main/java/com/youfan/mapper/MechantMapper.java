package com.youfan.mapper;

import com.youfan.model.Mechant;

/**
 * Created by youfan on 2018/6/8 0008.
 */
public interface MechantMapper {
    public void insertMechant(Mechant mechant);
    public void upateMechant(Mechant mechant);
    public Mechant findMechantbyid(int id);
    public void upateMechantstatus(Mechant mechant);
    public void upatesoldout(Mechant mechant);
}
