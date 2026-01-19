package com.generation.library.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.generation.library.model.entities.Pet;

public class SQLPetRepository extends SQLEntityRepository<Pet>
{
    public SQLPetRepository(String table, Connection connection)
	{
		super(connection, table);
	}

	@Override
    public Pet rowToX(ResultSet row) throws SQLException
    {
        Pet p = new Pet();
        
        p.setId(row.getInt("id"));
        p.setName(row.getString("name"));
        p.setSpecies(row.getString("species"));
        
        return p;
    }

	@Override
	public PreparedStatement getInsertCmd(Pet pet) throws SQLException
	{
		String sql = "insert into " + getTable() + " (name, species) values (?, ?)";
		PreparedStatement cmd = connection.prepareStatement(sql);

		cmd.setString(1, pet.getName());
		cmd.setString(2, pet.getSpecies());

		return cmd;
	}

	@Override
	public PreparedStatement getUpdateCmd(Pet pet) throws SQLException
	{
		String sql = "update " + getTable() + " set name=?, species=? where id=?";
		PreparedStatement cmd = connection.prepareStatement(sql);

		cmd.setString(1, pet.getName());
		cmd.setString(2, pet.getSpecies());
		cmd.setInt(3, pet.getId());

		return cmd;
	}
}
