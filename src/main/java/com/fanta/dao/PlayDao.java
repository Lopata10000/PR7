package com.fanta.dao;

import com.fanta.entity.Entity;
import com.fanta.entity.ServiceEntity;
import com.fanta.exception.DaoException;
import com.fanta.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao implements Dao{

    private static ServiceDao serviceDao = new ServiceDao();

    private ServiceDao(){}

    public static ServiceDao getInstance() {
        return serviceDao;
    }

    @Override
    public void saveNewEntity(Entity entity) {
        ServiceEntity serviceEntity = (ServiceEntity) entity;
        String sqlSave = "INSERT INTO SERVICES (name, description, price) VALUES ( ?, ?, ? )";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlSave)){
            statement.setString(1, serviceEntity.getName());
            statement.setString(2, serviceEntity.getDescription());
            statement.setDouble(3, serviceEntity.getPrice());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void updateEntityById(int id, Entity entity) {
        ServiceEntity serviceEntity = (ServiceEntity) entity;
        String sqlUpdate = "UPDATE SERVICES SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE SERVICE_ID = ?";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlUpdate)){
            statement.setString(1, serviceEntity.getName());
            statement.setString(2, serviceEntity.getDescription());
            statement.setDouble(3, serviceEntity.getPrice());

            statement.setInt(4, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void deleteEntity(int id) {
        String sqlDelete = "DELETE FROM SERVICES WHERE SERVICE_ID = ?";
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlDelete)){
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public ServiceEntity findById(int id) {
        String sqlFindById = "SELECT SERVICE_ID, NAME, DESCRIPTION, PRICE FROM SERVICES WHERE SERVICE_ID = ?";

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlFindById)){
            statement.setInt(1, id);
            var data = statement.executeQuery();

            if(data.next()){
                return ServiceEntity
                        .builder()
                        .serviceId(data.getInt("service_id"))
                        .name(data.getString("name"))
                        .description(data.getString("description"))
                        .price(data.getDouble("price"))
                        .build();
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ServiceEntity> findAll() {
        String sqlFindAll = "SELECT SERVICE_ID, NAME, DESCRIPTION, PRICE FROM SERVICES";
        List<ServiceEntity> serviceEntities = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlFindAll)){
            var data = statement.executeQuery();

            while (data.next()){
                serviceEntities.add(ServiceEntity
                        .builder()
                        .serviceId(data.getInt("service_id"))
                        .name(data.getString("name"))
                        .description(data.getString("description"))
                        .price(data.getDouble("price"))
                        .build()
                );
            }

            return serviceEntities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
