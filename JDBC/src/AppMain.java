import java.util.List;

public class AppMain {
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ReturnStatus returnStatus;

		EmployeeEntity employee1 = new EmployeeEntity(1, "Surya", "Kovilpatti");
		try {
			returnStatus = employeeDAO.count();
			if (!returnStatus.isReturnStatus()) {

			}
			int empId = ((int) returnStatus.getReturnObject()) + 1;
			employee1.setEmpID(empId);
			returnStatus = employeeDAO.create(employee1);
			if (!returnStatus.isReturnStatus()) {
				throw new Exception((String) returnStatus.getReturnObject());
			}
			System.out.println("Created Employee Detail are as follows:");
			EmployeeEntity response = (EmployeeEntity) returnStatus.getReturnObject();
			printEmployee(employee1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			returnStatus = employeeDAO.read();
			if (!returnStatus.isReturnStatus()) {
				throw new Exception((String) returnStatus.getReturnObject());
			}
			System.out.println("");
			System.out.println("Selected Employee Details are as follows:");
			List<EmployeeEntity> employees = (List<EmployeeEntity>) returnStatus.getReturnObject();
			for (EmployeeEntity employee : employees) {
				printEmployee(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read table");
		}

		try {
			employee1.setEmpName("Ajith");
			employee1.setEmpCity("Gova");
			returnStatus = employeeDAO.update(employee1);
			if (!returnStatus.isReturnStatus()) {
				throw new Exception((String) returnStatus.getReturnObject());
			}
			System.out.println();
			System.out.println("Updated Employee Details are as follows");
			printEmployee(employee1);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to update the table");
		}

		try {
			System.out.println();
			returnStatus = employeeDAO.delete(employee1);
			if (returnStatus.isReturnStatus()) {
				System.out.println("Employee Detail Deleted successfully: " + returnStatus.getReturnObject());
			} else {
				System.out.println("Failed to delete employee");
			}
		} catch (Exception e) {
			System.out.println("Failed to delete employee");
		}

	}

	private static void printEmployee(EmployeeEntity employeeEntity) {
//		System.out.println();
		System.out.print("Employee ID: " + employeeEntity.getEmpID() + " ");
		System.out.print("Employee Name: " + employeeEntity.getEmpName() + " ");
		System.out.print("Employee City: " + employeeEntity.getEmpCity() + " ");
		System.out.println();
	}
}