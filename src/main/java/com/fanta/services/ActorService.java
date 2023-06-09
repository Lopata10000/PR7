package com.fanta.services;

import com.fanta.dao.AppointmentDao;
import com.fanta.entity.AppointmentEntity;

import java.util.List;

public final class ActorService  {
    private static AppointmentService appointmentService = new AppointmentService();
    private AppointmentService(){}

    public static AppointmentService getInstance() {
        return appointmentService;
    }

    public void addNewAppointment(AppointmentEntity appointmentEntity) {
        AppointmentDao.getInstance().saveNewEntity(appointmentEntity);
    }

    public void updateAppointmentById(int id, AppointmentEntity appointmentEntity){
        AppointmentDao.getInstance().updateEntityById(id, appointmentEntity);
    }

    public void deleteAppointmentById(int id){
        AppointmentDao.getInstance().deleteEntity(id);
    }

    public AppointmentEntity getAppointmentById(int id){
        return AppointmentDao.getInstance().findById(id);
    }

    public List<AppointmentEntity> getAllAppointments(){
        return AppointmentDao.getInstance().findAll();
    }
}
