package com.generation.bw.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.generation.bw.model.entities.Bike;
import com.generation.bw.model.entities.BikeStatus;
import com.generation.library.repository.PartialCacheSQLEntityRepository;

/**
 * SQL-based implementation of BikeRepository with partial caching support.
 * Extends the generic repository pattern with bike-specific query methods.
 */
public class SQLBikeRepository extends PartialCacheSQLEntityRepository<Bike> implements BikeRepository
{
	/**
	 * Comparator for sorting bikes by their status workflow order.
	 * Uses lambda expression to compare BikeStatus enum ordinal positions.
	 */
	Comparator<Bike>        compareByStatus     = (b1, b2) -> b1.getStatus().getOrder() - b2.getStatus().getOrder();

    /**
     * Constructs a SQLBikeRepository with caching capabilities.
     * 
     * @param table the database table name for bikes
     * @param connection the active database connection
     * @param maxSize the maximum cache size for frequently accessed entities
     */
    public SQLBikeRepository(String table, Connection connection, int maxSize)
    {
        super(table, connection, maxSize);
    }
    
    /**
     * Finds a bike by its unique license plate number.
     * 
     * @param plate the license plate to search for
     * @return the Bike entity, or null if not found
     */
    @Override
    public Bike findByPlate(String plate)
    {
        List<Bike>	matches = findWhere("plate='" + plate + "'"); 
        return matches.size() > 0 ? matches.get(0) : null;
    }

    /**
     * Retrieves all bikes currently in processing stages (not delivered).
     * 
     * @return list of Bike entities sorted by status workflow order
     */
    @Override
    public List<Bike> findProcessing()
    {
        List<Bike>	processing  = new ArrayList<>();     
        processing.sort(compareByStatus);
        return processing;
    }

    /**
     * Creates a PreparedStatement for inserting a new bike.
     * 
     * @param bike the Bike entity to insert
     * @return configured PreparedStatement with INSERT command
     * @throws SQLException if statement preparation fails
     */
    @Override
    protected PreparedStatement getInsertCmd(Bike bike) throws SQLException
    {
        String              query   = "INSERT INTO " + table
                                    + " (brand, model, plate, power, cost, work, price, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement   ps      = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        
        ps.setString    (1, bike.getBrand());
        ps.setString    (2, bike.getModel());
        ps.setString    (3, bike.getPlate());
        ps.setInt       (4, bike.getPower());
        ps.setInt       (5, bike.getCost());
        ps.setInt       (6, bike.getWork());
        ps.setInt       (7, bike.getPrice());
        ps.setString    (8, bike.getStatus().name());
        
        return ps;
    }
    
    /**
     * Creates a PreparedStatement for updating an existing bike.
     * 
     * @param bike the Bike entity with updated values
     * @return configured PreparedStatement with UPDATE command
     * @throws SQLException if statement preparation fails
     */
    @Override
    protected PreparedStatement getUpdateCmd(Bike bike) throws SQLException
    {
        String              query   = "UPDATE " + table
                                    + " SET brand = ?, model = ?, plate = ?, power = ?, cost = ?, work = ?, price = ?, status = ? WHERE id = ?";
        PreparedStatement   ps      = connection.prepareStatement(query);
        
        ps.setString    (1, bike.getBrand());
        ps.setString    (2, bike.getModel());
        ps.setString    (3, bike.getPlate());
        ps.setInt       (4, bike.getPower());
        ps.setInt       (5, bike.getCost());
        ps.setInt       (6, bike.getWork());
        ps.setInt       (7, bike.getPrice());
        ps.setString    (8, bike.getStatus().name());
        ps.setInt       (9, bike.getId());
        
        return ps;
    }
    
    /**
     * Maps a database ResultSet row to a Bike entity.
     * 
     * @param rs the ResultSet positioned at a valid row
     * @return fully populated Bike entity
     * @throws SQLException if column extraction fails
     */
    @Override
    public Bike rowToX(ResultSet rs) throws SQLException
    {
        Bike bike = new Bike();
        
        bike.setId      (rs.getInt      ("id"));
        bike.setBrand   (rs.getString   ("brand"));
        bike.setModel   (rs.getString   ("model"));
        bike.setPlate   (rs.getString   ("plate"));
        bike.setPower   (rs.getInt      ("power"));
        bike.setCost    (rs.getInt      ("cost"));
        bike.setWork    (rs.getInt      ("work"));
        bike.setPrice   (rs.getInt      ("price"));
        bike.setStatus  (BikeStatus.valueOf(rs.getString("status")));
        
        return bike;
    }
}
