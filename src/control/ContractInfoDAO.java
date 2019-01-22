package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.ContractInfoVO;

public class ContractInfoDAO {

	public ArrayList<ContractInfoVO> getSearchData(LocalDate sdate, LocalDate qdate) {
		
		ArrayList<ContractInfoVO> list = new ArrayList<ContractInfoVO>();
		ContractInfoVO cVo = new ContractInfoVO();
		String sql = "select * from contract where c_condate between ? and ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sdate.toString());
			pstmt.setString(2, qdate.toString());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int index = rs.getInt(1) - 1;
				cVo = new ContractInfoVO(index, rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7),
						rs.getString(8), rs.getString(9), rs.getString(10), rs.getDouble(11),
						rs.getString(12), rs.getDouble(13), rs.getString(14), rs.getDouble(15),
						rs.getString(16), rs.getDouble(17), rs.getString(18), rs.getString(19),
						rs.getString(20), rs.getDouble(21), rs.getString(22), rs.getString(23),
						rs.getString(24), rs.getString(25), rs.getString(26), rs.getDouble(27),
						rs.getString(28), rs.getString(29), rs.getDouble(30), rs.getDate(31).toLocalDate(),
						rs.getDate(32).toLocalDate(), rs.getString(33), rs.getDouble(34), rs.getDouble(35),
						rs.getDouble(36));
				list.add(cVo);
				
			}
			
		} catch (SQLException se) {
			
			System.err.println(se);
			
		} catch (Exception e) {
			
			System.err.println(e);
			
		} finally {
			
			try {
				
				if (rs != null)
					rs.close();
				
				if (pstmt != null)
					pstmt.close();
				
				if (con != null)
					con.close();
				
			} catch (Exception e) {
				
				System.err.println(e);
				
			}
			
		}
		
		return list;
		
	}
	
	public int updateNegoCs(int c_no, String negocs) {
		
		int i = 0;
		
		String sql = "update contract set c_negocs = ? where c_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int index = c_no + 1;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, negocs);
			pstmt.setInt(2, index);
			i = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			
			System.err.println(se);
			 
		} catch (Exception e) {
			
			System.err.println(e);
			
		} finally {
			
			try {
				
				if (pstmt != null)
					pstmt.close();
				
				if (con != null)
					con.close();
				
			} catch (Exception e) {
				
				System.err.println(e);
				
			}
			
		}
		
		return i;
		
	}
	
	public int deleteContractInfo(int C_no) {
		
		int i = 0;
		
		String sql = "delete from contract where c_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, C_no);
			i = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			
			System.err.println(se);
			
		} catch (Exception e) {
			
			System.err.println(e);
			
		} finally {
			
			try {
				
				if (pstmt != null)
					pstmt.close();
				
				if (con != null)
					con.close();
				
			} catch (Exception e) {
				
				System.err.println(e);
				
			}
			
		}
		
		return i;
		
	}
	
	public int updateContractInfo(ContractInfoVO cVo) {
		
		int i = 0;
		int index = cVo.getC_no() + 1;
		String sql = "update contract set c_period = ?, c_wage = ?, c_wagecondition = ?,"
				+ " c_wicondition = ?, c_wirate = ?, c_scorecondition = ?, c_scoreprofit = ?,"
				+ " c_assistscondition = ?, c_assistsprofit = ?, c_appscondition = ?,"
				+ " c_appsprofit = ?, c_notappscondition = ?, c_notappsprofit = ?,"
				+ " c_carcondition = ?, c_cardivision = ?, c_housecondition = ?, c_housefee = ?,"
				+ " c_periodcondition = ?, c_picondition = ?, c_piyear = ?, c_buyoutcondition = ?,"
				+ " c_bocondition = ?, c_buyoutpay = ?, c_releasecondition = ?,"
				+ " c_rcondition = ?, c_releasepenalty = ?, c_condate = ?, c_cexpiredate = ?,"
				+ " c_negocs = ?, c_loyalty = ?, c_agentfee = ?, c_contractTotal = ? where c_no = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cVo.getC_period());
			pstmt.setDouble(2, cVo.getC_wage());
			pstmt.setString(3, cVo.getC_wagecondition());
			pstmt.setString(4, cVo.getC_wicondition());
			pstmt.setString(5, cVo.getC_wirate());
			pstmt.setString(6, cVo.getC_scorecondition());
			pstmt.setDouble(7, cVo.getC_scoreprofit());
			pstmt.setString(8, cVo.getC_assistscondition());
			pstmt.setDouble(9, cVo.getC_assistsprofit());
			pstmt.setString(10, cVo.getC_appscondition());
			pstmt.setDouble(11, cVo.getC_appsprofit());
			pstmt.setString(12, cVo.getC_notappscondition());
			pstmt.setDouble(13, cVo.getC_notappsprofit());
			pstmt.setString(14, cVo.getC_carcondition());
			pstmt.setString(15, cVo.getC_cardivision());
			pstmt.setString(16, cVo.getC_housecondition());
			pstmt.setDouble(17, cVo.getC_housefee());
			pstmt.setString(18, cVo.getC_periodcondition());
			pstmt.setString(19, cVo.getC_picondition());
			pstmt.setString(20, cVo.getC_piyear());
			pstmt.setString(21, cVo.getC_buyoutcondition());
			pstmt.setString(22, cVo.getC_bocondition());
			pstmt.setDouble(23, cVo.getC_buyoutpay());
			pstmt.setString(24, cVo.getC_releasecondition());
			pstmt.setString(25, cVo.getC_rcondition());
			pstmt.setDouble(26, cVo.getC_releasepenalty());
			pstmt.setString(27, cVo.getC_condate().toString());
			pstmt.setString(28, cVo.getC_cexpiredate().toString());
			pstmt.setString(29, cVo.getC_negocs());
			pstmt.setDouble(30, cVo.getC_loyalty());
			pstmt.setDouble(31, cVo.getC_agentfee());
			pstmt.setDouble(32, cVo.getC_contractTotal());
			pstmt.setInt(33, index);
			
			i = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			
			System.err.println(se);
			
		} catch (Exception e) {
			
			System.err.println(e);
			
		} finally {
			
			try {
				
				if (pstmt != null)
					pstmt.close();
				
				if (con != null)
					con.close();
				
			} catch (Exception e) {
				
				System.err.println(e);
				
			}
			
		}

		return i;
		
	}
	
	public int insertContractInfo(ContractInfoVO cVo) {
		
		int i = 0;
		
		String sql = "insert into contract values(contract_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cVo.getP_name());
			pstmt.setString(2, cVo.getF_name());
			pstmt.setString(3, cVo.getC_division());
			pstmt.setString(4, cVo.getC_period());
			pstmt.setDouble(5, cVo.getC_wage());
			pstmt.setString(6, cVo.getC_wagecondition());
			pstmt.setString(7, cVo.getC_wicondition());
			pstmt.setString(8, cVo.getC_wirate());
			pstmt.setString(9, cVo.getC_scorecondition());
			pstmt.setDouble(10, cVo.getC_scoreprofit());
			pstmt.setString(11, cVo.getC_assistscondition());
			pstmt.setDouble(12, cVo.getC_assistsprofit());
			pstmt.setString(13, cVo.getC_appscondition());
			pstmt.setDouble(14, cVo.getC_appsprofit());
			pstmt.setString(15, cVo.getC_notappscondition());
			pstmt.setDouble(16, cVo.getC_notappsprofit());
			pstmt.setString(17, cVo.getC_carcondition());
			pstmt.setString(18, cVo.getC_cardivision());
			pstmt.setString(19, cVo.getC_housecondition());
			pstmt.setDouble(20, cVo.getC_housefee());
			pstmt.setString(21, cVo.getC_periodcondition());
			pstmt.setString(22, cVo.getC_picondition());
			pstmt.setString(23, cVo.getC_piyear());
			pstmt.setString(24, cVo.getC_buyoutcondition());
			pstmt.setString(25, cVo.getC_bocondition());
			pstmt.setDouble(26, cVo.getC_buyoutpay());
			pstmt.setString(27, cVo.getC_releasecondition());
			pstmt.setString(28, cVo.getC_rcondition());
			pstmt.setDouble(29, cVo.getC_releasepenalty());
			pstmt.setString(30, cVo.getC_condate().toString());
			pstmt.setString(31, cVo.getC_cexpiredate().toString());
			pstmt.setString(32, cVo.getC_negocs());
			pstmt.setDouble(33, cVo.getC_loyalty());
			pstmt.setDouble(34, cVo.getC_agentfee());
			pstmt.setDouble(35, cVo.getC_contractTotal());
			i = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			
			System.err.println(se);
			
		} catch (Exception e) {
			
			System.err.println(e);
			
		} finally {
			
			try {
				
				if (pstmt != null)
					pstmt.close();
				
				if (con != null)
					con.close();
				
			} catch (Exception e) {
				
				System.err.println(e);
				
			}
			
		}
		
		return i;
		
	}
	
	public ArrayList<ContractInfoVO> getContractTotal() {

		ArrayList<ContractInfoVO> list = new ArrayList<ContractInfoVO>();
		String sql = "select * from contract order by c_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContractInfoVO cVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int index = rs.getInt(1) - 1;
				cVo = new ContractInfoVO(index, rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7),
						rs.getString(8), rs.getString(9), rs.getString(10), rs.getDouble(11),
						rs.getString(12), rs.getDouble(13), rs.getString(14), rs.getDouble(15),
						rs.getString(16), rs.getDouble(17), rs.getString(18), rs.getString(19),
						rs.getString(20), rs.getDouble(21), rs.getString(22), rs.getString(23),
						rs.getString(24), rs.getString(25), rs.getString(26), rs.getDouble(27),
						rs.getString(28), rs.getString(29), rs.getDouble(30), rs.getDate(31).toLocalDate(),
						rs.getDate(32).toLocalDate(), rs.getString(33), rs.getDouble(34), rs.getDouble(35),
						rs.getDouble(36));
				list.add(cVo);
				
			}

		} catch (SQLException se) {

			System.err.println(se);

		} catch (Exception e) {

			System.err.println(e);

		} finally {

			try {

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (SQLException se) {
			}

		}

		return list;

	}

	public ArrayList<String> getColumnName() {

		ArrayList<String> columnName = new ArrayList<String>();
		String sql = "select * from contract";
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

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (SQLException se) {
			}

		}

		return columnName;

	}

}
