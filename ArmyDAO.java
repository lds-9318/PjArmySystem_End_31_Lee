package swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class ArmyDAO {

	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;




	public ArmyDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
			String userid = "kosea";
			String passwd = "kosea2019a";
			con = DriverManager.getConnection(url, userid, passwd);

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);

		}

	}

	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}

	public boolean getIdByCheck(String amynum) {

		boolean result = true;
		

		try {
			ps = con.prepareStatement("SELECT * FROM TB_army WHERE amynum = ?");
			ps.setString(1, amynum.trim());
			System.out.println(ps.toString());
			rs = ps.executeQuery();

			if (rs.next()) {
				result = false; // ���ڵ尡 �����ϸ� false

				
			}

		} catch (SQLException e) {
			System.out.println("getIdByCheck() ����:" + e);
		} finally {
			dbClose();

		}
		return result;

	}

	public int userListInsert(ArmyJDailogGUI user) {
		int result = 0;
		try {

			String sql = "insert into TB_Army(" + "amynum,name,birth,address,fdate,clas," + "spec,posit,gunnum) "
					+ "values(?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			// ddddddddddddd
			ps.setString(1, user.amynum.getText());
			ps.setString(2, user.name.getText());
			ps.setString(3, user.birth.getText());
			ps.setString(4, user.address.getText());
			ps.setString(5, user.fdate.getText());
			ps.setString(6, user.clas.getText());
			ps.setString(7, user.spec.getText());
			ps.setString(8, user.posit.getText());
			ps.setString(9, user.gunnum.getText());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("userListInsert() ����" + e);

		} finally {
			dbClose();
		}
		return result;
	}

	public void userSelectAll(DefaultTableModel t_model) {

		try {
			String sql = "select * from TB_Army order by name asc";
			st = con.createStatement();
			rs = st.executeQuery(sql);

			t_model.setNumRows(0);

			while (rs.next()) {
				Object data[] = { rs.getString(1),

						rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9) };

				t_model.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println("userSelectAll() ����" + e);

		} finally {
			dbClose();
		}
	}

	public int userDelete(String amynum) {
		int result = 0;

		try {

			ps = con.prepareStatement("delete TB_Army where amynum = ? ");
			ps.setString(1, amynum.trim());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("userDelete()" + e);

		} finally {
			dbClose();
		}
		return result;
	}

//ȸ������

	public int userUpdate(ArmyJDailogGUI user) {

		int result = 0;

		String sql = "UPDATE TB_Army SET name=?, birth=?, address=?, fdate=?, clas=?, spec=?, posit=?, gunnum=? WHERE amynum=?";

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, user.name.getText());
			ps.setString(2, user.birth.getText());
			ps.setString(3, user.address.getText());
			ps.setString(4, user.fdate.getText());
			ps.setString(5, user.clas.getText());
			ps.setString(6, user.spec.getText());
			ps.setString(7, user.posit.getText());
			ps.setString(8, user.gunnum.getText());
			ps.setString(9, user.amynum.getText());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("userUpdate() ����:" + e);

		} finally {
			dbClose();
		}

		return result;
	}

	public void getUserSearch(DefaultTableModel dt, String fieldName, String word) {
		String sql = "SELECT * FROM TB_Army WHERE " + fieldName.trim() + " LIKE '%" + word.trim() + "%'";

		try {

			st = con.createStatement();
			rs = st.executeQuery(sql);

			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);

			}

			while (rs.next()) {

				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9) };

				dt.addRow(data);

			}

		} catch (SQLException e) {
			System.out.println("getUserSearch() ����:" + e);

		} finally {
			dbClose();

		}

	}

}