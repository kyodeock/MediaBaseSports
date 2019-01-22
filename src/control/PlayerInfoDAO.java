package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.PlayerInfoVO;
import model.PlayerRecordVO;

public class PlayerInfoDAO {
	
	public int updatePlayerRecord(PlayerRecordVO pVo) {
		
		int i = 0;
		
		String sql = "update player set p_totalscores = p_totalscores + ?, p_totalassists = p_totalassists + ?, p_totalapps = p_totalapps + 1, p_avgrate = (p_avgrate + ?) / 2 where p_name = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pVo.getR_scores());
			pstmt.setInt(2, pVo.getR_assists());
			pstmt.setDouble(3, pVo.getR_appsrate());
			pstmt.setString(4, pVo.getP_name());
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
	
	public ArrayList<String> getf_name() {
		
		ArrayList<String> f_name = new ArrayList<String>();
		
		String sql = "select f_name from footballclub";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				f_name.add(rs.getString(1));
				
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
		
		return f_name;
		
	}
	
	public int deletePlayerInfo(String no) {
		
		int i = 0;
		
		no = (Integer.parseInt(no) + 1) + "";
		
		String sql = "delete from player where p_registNo = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
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
			}
			
		}
		
		return i;
		
	}
	
	public PlayerInfoVO getSearchData(String name) {
		
		String dml = "select * from player where p_name = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PlayerInfoVO retval = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				String date = rs.getString(5).substring(0, 10);
				LocalDate ldate = LocalDate.parse(date);
				int index = (Integer.parseInt(rs.getString(1)) - 1);
				
				retval = new PlayerInfoVO(index, rs.getString(2), rs.getString(3), rs.getString(4),
						ldate, rs.getDouble(6), rs.getDouble(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getInt(14), rs.getInt(15),
						rs.getInt(16), rs.getDouble(17), rs.getString(18), rs.getString(19),
						rs.getString(20), rs.getString(21));
				
			}
			
		} catch (SQLException se) {
			
			System.out.println(se);
			
		} catch (Exception e) {
			
			System.out.println(e);
			
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
		
		return retval;
		
	}
	
	public ArrayList<String> getColumnName() {
		
		ArrayList<String> columnName = new ArrayList<String>();
		String sql = "select * from player";
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
	
	public ArrayList<PlayerInfoVO> getPlayerTotal() {
		
		ArrayList<PlayerInfoVO> list = new ArrayList<PlayerInfoVO>();
		String sql = "select * from player";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PlayerInfoVO pVo = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String date = rs.getString(5).substring(0, 10);
				String no = rs.getString(1);
				int n = Integer.parseInt(no) - 1;
				
				LocalDate ldate = LocalDate.parse(date);

				pVo = new PlayerInfoVO(n, rs.getString(2), rs.getString(3), rs.getString(4),
						ldate, rs.getDouble(6), rs.getDouble(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getInt(14), rs.getInt(15),
						rs.getInt(16), rs.getDouble(17), rs.getString(18), rs.getString(19),
						rs.getString(20), rs.getString(21));
				
				list.add(pVo);
				
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
	
	public int updatePlayerInfo(PlayerInfoVO pVo) {
		
		String sql = "update player set p_division = ?, p_height = ?, p_weight = ?, "
				+ "p_position = ?, p_visa = ?, p_phone = ?, p_address = ?, "
				+ "p_email = ?, f_name = ?, p_totalscores = ?, p_totalassists = ?, "
				+ "p_totalapps = ?, p_avgrate = ?, p_preparedfoot = ?, "
				+ "p_imgpath = ? "
				+ "where p_registNo = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		
		int no = pVo.getP_rNo() + 1;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pVo.getP_division());
			pstmt.setDouble(2, pVo.getP_height());
			pstmt.setDouble(3, pVo.getP_weight());
			pstmt.setString(4, pVo.getP_position());
			pstmt.setString(5, pVo.getP_visa());
			pstmt.setString(6, pVo.getP_phone());
			pstmt.setString(7, pVo.getP_address());
			pstmt.setString(8, pVo.getP_email());
			pstmt.setString(9, pVo.getP_attached());
			pstmt.setInt(10, pVo.getP_totalscores());
			pstmt.setInt(11, pVo.getP_totalassists());
			pstmt.setInt(12, pVo.getP_totalapps());
			pstmt.setDouble(13, pVo.getP_avgrate());
			pstmt.setString(14, pVo.getP_pfoot());
			pstmt.setString(15, pVo.getP_imgpath());
			pstmt.setInt(16, no);
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
			}
			
		}
		
		return i;
	}
	
	public int insertPlayerInfo(PlayerInfoVO pVo) {
		
		String birth = pVo.getP_birth().toString();
		
		String sql = "insert into player values(player_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pVo.getP_division());
			pstmt.setString(2, pVo.getP_attached());
			pstmt.setString(3, pVo.getP_name());
			pstmt.setString(4, birth);
			pstmt.setDouble(5, pVo.getP_height());
			pstmt.setDouble(6, pVo.getP_weight());
			pstmt.setString(7, pVo.getP_position());
			pstmt.setString(8, pVo.getP_nationality());
			pstmt.setString(9, pVo.getP_visa());
			pstmt.setString(10, pVo.getP_phone());
			pstmt.setString(11, pVo.getP_address());
			pstmt.setString(12, pVo.getP_email());
			pstmt.setInt(13, pVo.getP_totalscores());
			pstmt.setInt(14, pVo.getP_totalassists());
			pstmt.setInt(15, pVo.getP_totalapps());
			pstmt.setDouble(16, pVo.getP_avgrate());
			pstmt.setString(17, pVo.getP_pfoot());
			pstmt.setString(18, pVo.getP_hta());
			pstmt.setString(19, pVo.getP_gender());
			pstmt.setString(20, pVo.getP_imgpath());
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
			}
			
		}
		
		return i;
		
	}
	
}
