import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	private ConnectionUtil connectionUtil = new ConnectionUtil();
	private EmployeeEntityQuery employeeEntityQuery = new EmployeeEntityQuery();

	// Proper exception handling and opened resources are properly closed.
	public ReturnStatus create(EmployeeEntity saveEmployeeEntity) {
		Connection connection = null;
		PreparedStatement stmnt = null;
		try {
			connection = connectionUtil.getDBconnection();
			stmnt = connection.prepareStatement(employeeEntityQuery.insert());

			stmnt.setInt(1, saveEmployeeEntity.getEmpID());
			stmnt.setString(2, saveEmployeeEntity.getEmpName());
			stmnt.setString(3, saveEmployeeEntity.getEmpCity());
			stmnt.execute();
		} catch (Exception e) {
			return new ReturnStatus(false, e.getMessage());
		} finally {
			try {
//				boolean bln = true;
				connection.close();
//				if (bln) {
//					throw new Exception("Test Exception");
//				}
				stmnt.close();
			} catch (Exception e) {
				return new ReturnStatus(false, e.getMessage());
			}
		}
		return new ReturnStatus(true, saveEmployeeEntity);
	}

	public ReturnStatus read() {
		Connection connection = null;
		Statement statement = null;
		List<EmployeeEntity> employees = new ArrayList<>();
		try {
			connection = connectionUtil.getDBconnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(employeeEntityQuery.read());

			while (rs.next()) {
				EmployeeEntity employee = new EmployeeEntity(0, null, null);
				employee.setEmpID(rs.getInt("EmpID"));
				employee.setEmpName(rs.getString("EmpName"));
				employee.setEmpCity(rs.getString("EmpCity"));
				employees.add(employee);
			}
		} catch (Exception e) {
			return new ReturnStatus(false, e.getMessage());
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (Exception e) {
				return new ReturnStatus(false, e.getMessage());
			}
		}
		return new ReturnStatus(true, employees);
	}

	public ReturnStatus update(EmployeeEntity updateEmployeeEntity) {
		Connection connection = null;
		PreparedStatement stmnt = null;
		try {
			connection = connectionUtil.getDBconnection();
			stmnt = connection.prepareStatement(employeeEntityQuery.update());
			stmnt.setString(1, updateEmployeeEntity.getEmpName());
			stmnt.setString(2, updateEmployeeEntity.getEmpCity());
			stmnt.setInt(3, updateEmployeeEntity.getEmpID());
			stmnt.execute();
		} catch (Exception e) {
			return new ReturnStatus(false, e.getMessage());
		} finally {
			try {
				stmnt.close();
				connection.close();
			} catch (Exception e) {
				return new ReturnStatus(false, e.getMessage());
			}
		}
		return new ReturnStatus(true, updateEmployeeEntity);
	}

	public ReturnStatus delete(EmployeeEntity employee1) {
		Connection connection = null;
		PreparedStatement stmnt = null;
		int rowsAffected;
		try {
			connection = connectionUtil.getDBconnection();
			String query = employeeEntityQuery.delete();
			stmnt = connection.prepareStatement(query);
			stmnt.setInt(1, employee1.getEmpID());

			rowsAffected = stmnt.executeUpdate();
			if (rowsAffected > 0) {
				return new ReturnStatus(true, rowsAffected);
			} else {
				return new ReturnStatus(false, employee1.getEmpID());
			}
		} catch (Exception e) {
			return new ReturnStatus(false, e.getMessage());
		} finally {
			try {

				stmnt.close();
				connection.close();

			} catch (Exception e) {
				return new ReturnStatus(false, e.getMessage());
			}
		}

	}

	public ReturnStatus count() {
		try (Connection connection = connectionUtil.getDBconnection(); Statement stmnt = connection.createStatement()) {

			ResultSet rs = stmnt.executeQuery(employeeEntityQuery.count());
			int rowCount = 0;
			if (rs.next()) {
				rowCount = rs.getInt("count");
			}

			return new ReturnStatus(true, rowCount);
		} catch (Exception e) {
			return new ReturnStatus(false, e.getMessage());
		}
	}
}
