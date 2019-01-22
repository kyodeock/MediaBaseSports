package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ClubInfoVO;
import oracle.sql.DATE;

public class ClubInfoDAO {
	
	public int deleteClubInfo(String f_name) {
		
		int i = 0;
		
		String sql = "delete from footballclub where f_name = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, f_name);
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
				
			} catch (SQLException se) {
				
				System.err.println(se);
				
			}
			
		}
		
		return i;
		
	}
	
	public int updateClubInfo(ClubInfoVO cVo) {
		
		int i = 0;
		
		String sql = "update footballclub set f_ownername = ?, f_directorname = ?, f_coachname = ?, f_wagebudget = ?, f_transferbudget = ?, f_address = ? where f_name = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cVo.getF_ownername());
			pstmt.setString(2, cVo.getF_directorname());
			pstmt.setString(3, cVo.getF_coachname());
			pstmt.setLong(4, cVo.getF_wagebudget());
			pstmt.setLong(5, cVo.getF_transferbudget());
			pstmt.setString(6, cVo.getF_address());
			pstmt.setString(7, cVo.getF_name());
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
				
			} catch (SQLException se) {
				
				System.err.println(se);
				
			}
			
		}
		
		return i;
		
	}
	
	public int insertClubInfo(ClubInfoVO cVo) {
		
		int i = 0;
		
		String sql = "insert into footballclub values(?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cVo.getF_name());
			pstmt.setString(2, cVo.getF_ownername());
			pstmt.setString(3, cVo.getF_directorname());
			pstmt.setString(4, cVo.getF_coachname());
			pstmt.setLong(5, cVo.getF_wagebudget());
			pstmt.setLong(6, cVo.getF_transferbudget());
			pstmt.setString(7, cVo.getF_address());
			pstmt.setString(8, cVo.getF_imgpath());
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
				
			} catch (SQLException se) {
				
				System.err.println(se);
				
			}
			
		}
		
		return i;
		
	}
	
	public ClubInfoVO getSearchData(String f_name) {
		
		ClubInfoVO cVo = new ClubInfoVO();
		String sql = "select * from footballclub where f_name = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, f_name);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				cVo = new ClubInfoVO(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getLong(5), rs.getLong(6), rs.getString(7), rs.getString(8));
				
			}
			
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
				
			} catch (SQLException se) {
				
				System.err.println(se);
				
			}
			
		}
		
		return cVo;
		
	}
	
	public ArrayList<ClubInfoVO> getClubTotal() {

		ArrayList<ClubInfoVO> list = new ArrayList<ClubInfoVO>();
		String sql = "select * from footballclub";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ClubInfoVO cVo = new ClubInfoVO();

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				cVo = new ClubInfoVO(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getLong(5), rs.getLong(6), rs.getString(7), rs.getString(8));

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
		String sql = "select * from footballclub";
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
