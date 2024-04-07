
public class EmployeeEntityQuery {
	public String read() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM EmployeeEntity");
		return sb.toString();
	}

	public String insert() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO EmployeeEntity (EmpID, EmpName, EmpCity) VALUES (?, ?, ?)");
		return sb.toString();

	}

	public String update() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE EmployeeEntity SET EmpName = ?, EmpCity = ? WHERE EmpID = ?");
		return sb.toString();

	}

	public String delete() {
		return "DELETE FROM EmployeeEntity WHERE EmpID = ?";
	}
	
	public String count() {
		return "SELECT IFNULL(COUNT(*),0) AS count FROM employeeentity";
	}
}
