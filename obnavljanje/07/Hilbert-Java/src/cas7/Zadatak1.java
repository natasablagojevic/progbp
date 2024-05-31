package cas7;

public class Zadatak1 {
	static {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
