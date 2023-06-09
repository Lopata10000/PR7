package com.fanta.dao;

import com.fanta.entity.AppointmentEntity;
import com.fanta.entity.Entity;
import com.fanta.exception.DaoException;
import com.fanta.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao implements Dao{

    private static AppointmentDao appointmentDao = new AppointmentDao();

    private AppointmentDao(){}

    public static AppointmentDao getInstance() {
        return appointmentDao;
    }

    @Override
    public void saveNewEntity(Entity entity) {
        AppointmentEntity appointmentEntity = (AppointmentEntity) entity;
        String sqlSave = "INSERT INTO APPOINTMENTS (APPOINTMENT_DATE, PATIENT_ID, DOCTOR_ID, SERVICE_ID) VALUES ( ?, ?, ?, ? )";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlSave)){
            statement.setObject(1, appointmentEntity.getAppointmentDate());
            statement.setInt(2, appointmentEntity.getPatientEntity().getPatientId());
            statement.setInt(3, appointmentEntity.getDoctorEntity().getDoctorId());
            statement.setInt(4, appointmentEntity.getServiceEntity().getServiceId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void updateEntityById(int id, Entity entity) {
        AppointmentEntity appointmentEntity = (AppointmentEntity) entity;
        String sqlUpdate = "UPDATE APPOINTMENTS SET APPOINTMENT_DATE = ?, PATIENT_ID = ?, DOCTOR_ID = ?, SERVICE_ID = ? WHERE APPOINTMENT_ID = ?";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlUpdate)){
            statement.setObject(1, appointmentEntity.getAppointmentDate());
            statement.setInt(2, appointmentEntity.getPatientEntity().getPatientId());
            statement.setInt(3, appointmentEntity.getDoctorEntity().getDoctorId());
            statement.setInt(4, appointmentEntity.getServiceEntity().getServiceId());

            statement.setInt(5, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteEntity(int id) {
        String sqlDelete = "DELETE FROM APPOINTMENTS WHERE APPOINTMENT_ID = ?";
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlDelete)){
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public AppointmentEntity findById(int id) {
        String sqlFindById = "SELECT APPOINTMENT_ID, APPOINTMENT_DATE, PATIENT_ID, DOCTOR_ID, SERVICE_ID FROM APPOINTMENTS WHERE APPOINTMENT_ID = ?";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlFindById)){
            statement.setInt(1, id);
            var data = statement.executeQuery();

            if(data.next()){
                return AppointmentEntity
                        .builder()
                        .appointmentId(data.getInt("appointment_id"))
                        .appointmentDate( data.getTimestamp("appointment_date").toLocalDateTime() )
                        .patientEntity( PatientDao.getInstance().findById( data.getInt("patient_id") ) )
                        .doctorEntity( DoctorDao.getInstance().findById( data.getInt("doctor_id") ) )
                        .serviceEntity( ServiceDao.getInstance().findById( data.getInt("service_id") ) )
                        .build();
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<AppointmentEntity> findAll() {
        String sqlFindAll = "SELECT APPOINTMENT_ID, APPOINTMENT_DATE, PATIENT_ID, DOCTOR_ID, SERVICE_ID FROM APPOINTMENTS";
        List<AppointmentEntity> appointmentEntities = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlFindAll)){
            var data = statement.executeQuery();

            while (data.next()){
                appointmentEntities.add(AppointmentEntity
                        .builder()
                        .appointmentId(data.getInt("appointment_id"))
                        .appointmentDate( data.getTimestamp("appointment_date").toLocalDateTime() )
                        .patientEntity( PatientDao.getInstance().findById( data.getInt("patient_id") ) )
                        .doctorEntity( DoctorDao.getInstance().findById( data.getInt("doctor_id") ) )
                        .serviceEntity( ServiceDao.getInstance().findById( data.getInt("service_id") ) )
                        .build()
                );
            }

            return appointmentEntities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
