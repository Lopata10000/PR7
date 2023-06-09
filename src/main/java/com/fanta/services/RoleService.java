package com.fanta.services;

import com.fanta.dao.PatientDao;
import com.fanta.entity.PatientEntity;

import java.util.List;

public final class PatientService {

    private static PatientService patientService = new PatientService();
    private PatientService(){}

    public static PatientService getInstance() {
        return patientService;
    }

    public void addNewPatient(PatientEntity patientEntity) {
        PatientDao.getInstance().saveNewEntity(patientEntity);
    }

    public void updatePatientById(int id, PatientEntity patientEntity){
        PatientDao.getInstance().updateEntityById(id, patientEntity);
    }

    public void deletePatientById(int id){
        PatientDao.getInstance().deleteEntity(id);
    }

    public PatientEntity getPatientById(int id){
        return PatientDao.getInstance().findById(id);
    }

    public List<PatientEntity> getAllPatient(){
        return PatientDao.getInstance().findAll();
    }
}
