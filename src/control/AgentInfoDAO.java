package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.AgentInfoVO;

public class AgentInfoDAO {

	public int deleteAgentInfo(String rNo) {
		
		int i = 0;
		
		String sql = "delete from agent where a_registNo = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rNo);
			i = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			
			System.err.println(se);
			
		} catch (Exception e) {
			
			System.err.println(e);
			
		} finally {
			
			try {
				
				if (pstmt == null)
					pstmt.close();
				
				if (con == null)
					con.close();
				
			} catch (SQLException se) {
			}
			
		}
		
		return i;
		
	}
	
	public int updateAgentInfo(AgentInfoVO aVo) {
		
		int i = 0;
		
		String sql = "update agent set a_phone = ?, a_expiredate = ?, a_univ = ?, a_imgpath = ? where a_registNo = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, aVo.getA_phone());
			pstmt.setString(2, aVo.getA_expireDate().toString());
			pstmt.setString(3, aVo.getA_univ());
			pstmt.setString(4, aVo.getA_imgPath());
			pstmt.setString(5, aVo.getA_rNo());
			i = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			
			System.err.println(se);
			
		} catch (Exception e) {
			
			System.err.println(e);
			
		} finally {
			
			try {
				
				if (pstmt == null)
					pstmt.close();
				
				if (con == null)
					con.close();
				
			} catch (SQLException se) {
			}
			
		}
		
		return i;
		
	}
	
	public int insertAgentInfo(AgentInfoVO aVo) {
		
		String sql = "insert into agent values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, aVo.getA_rNo());
			pstmt.setString(2, aVo.getA_name());
			pstmt.setString(3, aVo.getA_phone());
			pstmt.setString(4, aVo.getA_birth().toString());
			pstmt.setString(5, aVo.getA_gender());
			pstmt.setString(6, aVo.getA_qualifyDate().toString());
			pstmt.setString(7, aVo.getA_expireDate().toString());
			pstmt.setString(8, aVo.getA_univ());
			pstmt.setString(9, aVo.getA_address());
			pstmt.setString(10, aVo.getA_language());
			pstmt.setInt(11, aVo.getA_sal());
			pstmt.setString(12, aVo.getA_hireDate().toString());
			pstmt.setString(13, aVo.getA_nationality());
			pstmt.setString(14, aVo.getA_imgPath());
			i = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			
			System.err.println(se);
			
		} catch (Exception e) {
			
			System.err.println(e);
			
		} finally {
			
			try {
				
				if (pstmt == null)
					pstmt.close();
				
				if (con == null)
					con.close();
				
			} catch (SQLException se) {
			}
			
		}
		
		
		return i;
		
	}
	
	public ArrayList<AgentInfoVO> getAgentTotal() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ArrayList<AgentInfoVO> list = new ArrayList<AgentInfoVO>();
		String sql = "select * from agent order by a_registNo";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AgentInfoVO aVo = null;
		String birthdate = "";
		String qdate = "";
		String edate = "";
		String hdate = "";
		
		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				birthdate = rs.getDate(4).toString().substring(0, 10);
				LocalDate birthDate = LocalDate.parse(birthdate, formatter);
				qdate = rs.getDate(6).toString().substring(0, 10);
				LocalDate qDate = LocalDate.parse(qdate, formatter);
				edate = rs.getDate(7).toString().substring(0, 10);
				LocalDate eDate = LocalDate.parse(edate, formatter);
				hdate = rs.getDate(12).toString().substring(0, 10);
				LocalDate hDate = LocalDate.parse(hdate, formatter);
				
				aVo = new AgentInfoVO(rs.getString(1), rs.getString(2),
						rs.getString(3), birthDate, rs.getString(5),
						qDate, eDate, rs.getString(8), rs.getString(9), rs.getString(10), 
						rs.getInt(11), hDate, rs.getString(13), rs.getString(14));
				list.add(aVo);

			}

		} catch (SQLException se) {

			System.err.println(se);
			
		} catch (Exception e) {

			System.err.println(e);
			System.out.println(birthdate.toString());
			System.out.println(qdate.toString());
			System.out.println(edate.toString());
			System.out.println(hdate.toString());
			
		} finally {

			try {

				if (rs == null)
					rs.close();

				if (pstmt == null)
					pstmt.close();

				if (con == null)
					con.close();

			} catch (SQLException se) {
			}

		}

		return list;

	}

	public ArrayList<String> getColumnName() {

		ArrayList<String> columnName = new ArrayList<String>();
		String sql = "select * from agent";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();

			int cols = rsmd.getColumnCount();

			for (int i = 1; i <= cols; i++) {

				columnName.add(rsmd.getColumnName(i));

			}

		} catch (SQLException se) {

			System.err.println(se);

		} catch (Exception e) {

			System.err.println(e);

		} finally {

			try {

				if (rs == null)
					rs.close();

				if (pstmt == null)
					pstmt.close();

				if (con == null)
					con.close();

			} catch (SQLException se) {
			}

		}

		return columnName;

	}

}
