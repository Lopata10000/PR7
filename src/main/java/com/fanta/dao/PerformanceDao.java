package com.fanta.dao;

import com.fanta.entity.Entity;
import com.fanta.entity.PatientEntity;
import com.fanta.exception.DaoException;
import com.fanta.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements Dao{

    private static final PatientDao PATIENT_DAO = new PatientDao();

    private PatientDao(){}

    public static PatientDao getInstance() {
        return PATIENT_DAO;
    }

    @Override
    public void saveNewEntity(Entity entity) {
        PatientEntity patientEntity = (PatientEntity) entity;
        String sqlSave = "INSERT INTO patients (name, surname, phone_number, email, address) VALUES ( ?, ?, ?, ?, ? )";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlSave)){
            statement.setString(1, patientEntity.getName());
            statement.setString(2, patientEntity.getSurname());
            statement.setString(3, patientEntity.getPhoneNumber());
            statement.setString(4, patientEntity.getEmail());
            statement.setString(5, patientEntity.getAddress());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateEntityById(int id, Entity entity) {
        PatientEntity patientEntity = (PatientEntity) entity;
        String sqlUpdate = "UPDATE PATIENTS SET NAME = ?, SURNAME = ?, PHONE_NUMBER = ?, EMAIL = ?, ADDRESS = ? WHERE PATIENT_ID = ?";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlUpdate)){
            statement.setString(1, patientEntity.getName());
            statement.setString(2, patientEntity.getSurname());
            statement.setString(3, patientEntity.getPhoneNumber());
            statement.setString(4, patientEntity.getEmail());
            statement.setString(5, patientEntity.getAddress());
            statement.setInt(6, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteEntity(int id) {
        String sqlDelete = "DELETE FROM PATIENTS WHERE PATIENT_ID = ?";
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlDelete)){
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PatientEntity findById(int id) {
        String sqlFindById = "SELECT PATIENT_ID, NAME, SURNAME, PHONE_NUMBER, EMAIL, ADDRESS FROM PATIENTS WHERE PATIENT_ID = ?";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlFindById)){
            statement.setInt(1, id);
            var data = statement.executeQuery();

            if(data.next()){
                return PatientEntity
                        .builder()
                        .patientId(data.getInt("patient_id"))
                        .name(data.getString("name"))
                        .surname(data.getString("surname"))
                        .phoneNumber(data.getString("phone_number"))
                        .email(data.getString("email"))
                        .address(data.getString("address"))
                        .build();
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public List<PatientEntity> findAll() {
        String sqlFindAll = "SELECT PATIENT_ID, NAME, SURNAME, PHONE_NUMBER, EMAIL, ADDRESS FROM PATIENTS";
        List<PatientEntity> patientEntities = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlFindAll)){
            var data = statement.executeQuery();

            while (data.next()){
                patientEntities.add(PatientEntity
                        .builder()
                        .patientId(data.getInt("patient_id"))
                        .name(data.getString("name"))
                        .surname(data.getString("surname"))
                        .phoneNumber(data.getString("phone_number"))
                        .email(data.getString("email"))
                        .address(data.getString("address"))
                        .build()
                );
            }

            return patientEntities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
