/* File: FishStickDao.java
 * Author: Stanley Pieda
 * Date: Jan 2018
 * Description: Sample solution to Assignment 2
 * References:
 * Ram N. (2013).  Data Access Object Design Pattern or DAO Pattern [blog] Retrieved from
 * http://ramj2ee.blogspot.in/2013/08/data-access-object-design-pattern-or.html
 */
package dataaccesslayer;

import java.sql.SQLException;
// import java.util.List; // not needed for now
import datatransfer.FishStick; 

/**
 * Partially complete interface for DAO design pattern.
 * Has one insert method, and one find-by-UUID method.
 * @author Stanley Pieda
 */
public interface FishStickDao {
	/** 
	 * Should return a reference to a FishStick object, loaded with data
	 * from the database, based on lookup using a UUID as String
	 * @param uuid String based UUID
	 * @return FishStick transfer object, or null if no match based on uuid found
	 * @throws SQLException
	 * @author Stanley Pieda
	 */
	FishStick findByUUID(String uuid) throws SQLException;

	/**
	 * Should return a reference to a Fishstick object with 
	 * the required data from the database, based on lookup using a record number as string
	 * @param recNum String based on record number
	 * @return FishStick transfer object, or null if no match based on record number found
	 * @throws SQLException
	 */
	FishStick findByRecordNumber(String recNum) throws SQLException;

	/**
	 * Should return a reference to a Fishstick object with 
	 * the required data from the database, based on lookup using a omega string as string
	 * @param omega string based on omega 
	 * @return FishStick transfer object, or null if no match based on omega found
	 * @throws SQLException
	 */
	FishStick findByOmega(String omega) throws SQLException;

	/**
	 * Should return a reference to a Fishstick object with 
	 * the required data from the database, based on lookup using a lambda string as string
	 * @param lambda string based on lambda
	 * @return FishStick transfer object, or null if no match based on lambda found
	 * @throws SQLException
	 */
	FishStick findByLambda(String lambda) throws SQLException;

	/**
	 * Should accept a FishTick object reference, insert the encapsulated data into database.
	 * @param fishStick with data for record insertion
	 * @throws SQLException
	 * @author Stanley Pieda
	 */
	void insertFishStick(FishStick fishStick) throws SQLException;

}
