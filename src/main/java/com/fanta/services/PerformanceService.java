package com.fanta.services;

import com.fanta.dao.DoctorDao;
import com.fanta.entity.DoctorEntity;

import java.util.List;

public final class DoctorService {
    private static DoctorService doctorService = new DoctorService();
    private DoctorService(){}

    public static DoctorService getInstance(){
        return doctorService;
    }

    public void addNewDoctor(DoctorEntity doctorEntity) {
        DoctorDao.getInstance().saveNewEntity(doctorEntity);
    }

    public void updateDoctorById(int id, DoctorEntity doctorEntity){
        DoctorDao.getInstance().updateEntityById(id, doctorEntity);
    }

    public void deleteDoctorById(int id){
        DoctorDao.getInstance().deleteEntity(id);
    }

    public DoctorEntity getDoctorById(int id){
        return DoctorDao.getInstance().findById(id);
    }

    public List<DoctorEntity> getAllDoctors(){
        return DoctorDao.getInstance().findAll();
    }
}
