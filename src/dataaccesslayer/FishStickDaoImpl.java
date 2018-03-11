/*
 * File: FishStickDaoImpl.java
 * Author: Joel Schmuland and Jordan Mckenzie, based off of class example by Stanley Pieda.
 * Date: Feb, 2018
 * 
 * Ram N. (2013). Data Access Object Design Pattern or DAO Pattern [blog] Retrieved from
 * http://ramj2ee.blogspot.in/2013/08/data-access-object-design-pattern-or.html
 *
 */
package dataaccesslayer;

/* File: CabbageDaoImpl.java
 * Author: Stanley Pieda
 * Date: Sept, 2017
 * References:
 * Ram N. (2013). Data Access Object Design Pattern or DAO Pattern [blog] Retrieved from
 * http://ramj2ee.blogspot.in/2013/08/data-access-object-design-pattern-or.html
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datatransfer.FishStick;

/**
 * Description: Complete implementation for DAO design pattern. Has one insert
 * method, and one find-by-UUID method.
 * 
 * @author Joel Schmuland, Jordan Mckenzie, and Stanley Pieda.
 */
public class FishStickDaoImpl implements FishStickDao {

	/**
	 * Returns a reference to a FishStick object, loaded with data from the
	 * database, based on lookup using a UUID as String.
	 *
	 * @author Stanley Pieda
	 * @author Joel Schmuland and Jordan Mckenzie
	 * @param uuid            String based UUID
	 * @return FishStick transfer object, or null if no match based on uuid found
	 * @throws SQLException the SQL exception
	 */
	@Override
	public FishStick findByUUID(String uuid) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FishStick fishStick = null;
		try {
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM FishSticks WHERE uuid = ?");
			pstmt.setString(1, uuid);

			rs = pstmt.executeQuery();

			rs.next();
			fishStick = new FishStick();
			fishStick.setId(rs.getInt("id"));
			fishStick.setRecordNumber(rs.getInt("recordnumber"));
			fishStick.setOmega(rs.getString("omega"));
			fishStick.setLambda(rs.getString("lambda"));
			fishStick.setUUID(rs.getString("uuid"));
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return fishStick;
	}

	/* (non-Javadoc)
	 * @see dataaccesslayer.FishStickDao#findByRecordNumber(java.lang.String)
	 */
	/*
	 * Set method for the rec Num. Need to do this for all columns
	 * @author Joel Schmuland and Jordan Mckenzie
	 */
	public FishStick findByRecordNumber(String recNum) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FishStick fishStick = null;

		try {
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM FishSticks WHERE recordnumber = ?");
			pstmt.setString(1, recNum);

			rs = pstmt.executeQuery();

			rs.next();

			fishStick = new FishStick();
			fishStick.setId(rs.getInt("id"));
			fishStick.setRecordNumber(rs.getInt("recordnumber"));
			fishStick.setOmega(rs.getString("omega"));
			fishStick.setLambda(rs.getString("lambda"));
			fishStick.setUUID(rs.getString("uuid"));

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return fishStick;
	}

	/* (non-Javadoc)
	 * @see dataaccesslayer.FishStickDao#findByOmega(java.lang.String)
	 * @author Joel Schmuland and Jordan Mckenzie
	 */
	public FishStick findByOmega(String omega) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FishStick fishStick = null;

		try {
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM FishSticks WHERE omega = ?");
			pstmt.setString(1, omega);

			rs = pstmt.executeQuery();

			rs.next();

			fishStick = new FishStick();
			fishStick.setId(rs.getInt("id"));
			fishStick.setRecordNumber(rs.getInt("recordnumber"));
			fishStick.setOmega(rs.getString("omega"));
			fishStick.setLambda(rs.getString("lambda"));
			fishStick.setUUID(rs.getString("uuid"));

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return fishStick;
	}

	/* (non-Javadoc)
	 * @see dataaccesslayer.FishStickDao#findByLambda(java.lang.String)
	 * @author Joel Schmuland and Jordan Mckenzie
	 */
	public FishStick findByLambda(String lambda) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FishStick fishStick = null;

		try {
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM FishSticks WHERE lambda = ?");
			pstmt.setString(1, lambda);

			rs = pstmt.executeQuery();

			rs.next();

			fishStick = new FishStick();
			fishStick.setId(rs.getInt("id"));
			fishStick.setRecordNumber(rs.getInt("recordnumber"));
			fishStick.setOmega(rs.getString("omega"));
			fishStick.setLambda(rs.getString("lambda"));
			fishStick.setUUID(rs.getString("uuid"));

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return fishStick;
	}

	/**
	 * Accepts a FishTick object reference, inserting the encapsulated data into
	 * database.
	 *
	 * @author Stanley Pieda
	 * @author Joel Schmuland and Jordan Mckenzie
	 * @param fishStick            with data for record insertion
	 * @throws SQLException the SQL exception
	 */
	public void insertFishStick(FishStick fishStick) throws SQLException {
		DataSource source = new DataSource();
		Connection con = source.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(
					"INSERT INTO FishSticks (recordnumber, omega, lambda, uuid) " + "VALUES(?, ?, ?, ?)");
			pstmt.setInt(1, fishStick.getRecordNumber());
			pstmt.setString(2, fishStick.getOmega());
			pstmt.setString(3, fishStick.getLambda());
			pstmt.setString(4, fishStick.getUUID());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
