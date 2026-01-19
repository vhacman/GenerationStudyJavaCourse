package com.generation.library.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.generation.library.model.entities.Plant;

public class SQLPlantRepository extends SQLEntityRepository<Plant>
{
    public SQLPlantRepository(String table, Connection connection)
    {
        super(connection, table);
    }

    @Override
    public Plant rowToX(ResultSet row) throws SQLException
    {
        Plant p = new Plant();

        p.setId(row.getInt("id"));
        p.setSpecies(row.getString("species"));
        p.setLength(row.getInt("length"));
        p.setCost(row.getDouble("cost"));

        return p;
    }

    @Override
    public PreparedStatement getInsertCmd(Plant plant) throws SQLException
    {
        String sql = "insert into " + getTable() + " (species, length, cost) values (?, ?, ?)";
        PreparedStatement cmd = connection.prepareStatement(sql);

        cmd.setString(1, plant.getSpecies());
        cmd.setInt(2, plant.getLength());
        cmd.setDouble(3, plant.getCost());

        return cmd;
    }

    @Override
    public PreparedStatement getUpdateCmd(Plant plant) throws SQLException
    {
        String sql = "update " + getTable() + " set species=?, length=?, cost=? where id=?";
        PreparedStatement cmd = connection.prepareStatement(sql);

        cmd.setString(1, plant.getSpecies());
        cmd.setInt(2, plant.getLength());
        cmd.setDouble(3, plant.getCost());
        cmd.setInt(4, plant.getId());

        return cmd;
    }
}
