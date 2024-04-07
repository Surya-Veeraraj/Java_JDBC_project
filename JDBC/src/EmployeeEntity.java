
public class EmployeeEntity {
	private int EmpID;
	private String EmpName;
	private String EmpCity;

	public EmployeeEntity(int empID, String empName, String empCity) {

		EmpID = empID;
		EmpName = empName;
		EmpCity = empCity;
	}

	public int getEmpID() {
		return EmpID;
	}

	public void setEmpID(int empID) {
		EmpID = empID;
	}

	public String getEmpName() {
		return EmpName;
	}

	public void setEmpName(String empName) {
		EmpName = empName;
	}

	public String getEmpCity() {
		return EmpCity;
	}

	public void setEmpCity(String empCity) {
		EmpCity = empCity;
	}
}
