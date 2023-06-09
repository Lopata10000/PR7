package com.fanta.dao;

import com.fanta.entity.DoctorEntity;
import com.fanta.entity.Entity;
import com.fanta.exception.DaoException;
import com.fanta.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao implements Dao{

    private static DoctorDao doctorDao = new DoctorDao();
    private DoctorDao(){}

    public static DoctorDao getInstance() {
        return doctorDao;
    }

    @Override
    public void saveNewEntity(Entity entity) {
        DoctorEntity doctorEntity = (DoctorEntity) entity;
        String sqlSave = "INSERT INTO DOCTORS (name, surname, phone_number, email, speciality) VALUES ( ?, ?, ?, ?, ? )";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlSave)){
            statement.setString(1, doctorEntity.getName());
            statement.setString(2, doctorEntity.getSurname());
            statement.setString(3, doctorEntity.getPhoneNumber());
            statement.setString(4, doctorEntity.getEmail());
            statement.setString(5, doctorEntity.getSpeciality());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void updateEntityById(int id, Entity entity) {
        DoctorEntity doctorEntity = (DoctorEntity) entity;
        String sqlUpdate = "UPDATE DOCTORS SET NAME = ?, SURNAME = ?, PHONE_NUMBER = ?, EMAIL = ?, SPECIALITY = ? WHERE DOCTOR_ID = ?";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlUpdate)){
            statement.setString(1, doctorEntity.getName());
            statement.setString(2, doctorEntity.getSurname());
            statement.setString(3, doctorEntity.getPhoneNumber());
            statement.setString(4, doctorEntity.getEmail());
            statement.setString(5, doctorEntity.getSpeciality());
            statement.setInt(6, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void deleteEntity(int id) {
        String sqlDelete = "DELETE FROM DOCTORS WHERE DOCTOR_ID = ?";
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlDelete)){
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public DoctorEntity findById(int id) {
        String sqlFindById = "SELECT DOCTOR_ID, NAME, SURNAME, PHONE_NUMBER, EMAIL, SPECIALITY FROM DOCTORS WHERE DOCTOR_ID = ?";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlFindById)){
            statement.setInt(1, id);
            var data = statement.executeQuery();

            if(data.next()){
                return DoctorEntity
                        .builder()
                        .doctorId(data.getInt("doctor_id"))
                        .name(data.getString("name"))
                        .surname(data.getString("surname"))
                        .phoneNumber(data.getString("phone_number"))
                        .email(data.getString("email"))
                        .speciality(data.getString("speciality"))
                        .build();
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<DoctorEntity> findAll() {
        String sqlFindAll = "SELECT DOCTOR_ID, NAME, SURNAME, PHONE_NUMBER, EMAIL, SPECIALITY FROM DOCTORS";
        List<DoctorEntity> doctorEntities = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlFindAll)){
            var data = statement.executeQuery();

            while (data.next()){
                doctorEntities.add(DoctorEntity
                        .builder()
                        .doctorId(data.getInt("doctor_id"))
                        .name(data.getString("name"))
                        .surname(data.getString("surname"))
                        .phoneNumber(data.getString("phone_number"))
                        .email(data.getString("email"))
                        .speciality(data.getString("speciality"))
                        .build()
                );
            }

            return doctorEntities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }
}
