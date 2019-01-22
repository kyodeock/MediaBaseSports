package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.PlayerRecordVO;

public class PlayerRecordDAO {

	public int insertPlayerRecord(PlayerRecordVO pVo) {
		
		int i = 0;
		
		String sql = "insert into playerrecord values(record_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pVo.getP_name());
			pstmt.setString(2, pVo.getF_name());
			pstmt.setString(3, pVo.getR_appstime());
			pstmt.setDouble(4, pVo.getR_appsrate());
			pstmt.setInt(5, pVo.getR_balltouch());
			pstmt.setInt(6, pVo.getR_trypass());
			pstmt.setInt(7, pVo.getR_keypass());
			pstmt.setInt(8, pVo.getR_successpass());
			pstmt.setDouble(9, pVo.getR_sucpassrate());
			pstmt.setInt(10, pVo.getR_assists());
			pstmt.setInt(11, pVo.getR_trydribble());
			pstmt.setInt(12, pVo.getR_sucdribble());
			pstmt.setDouble(13, pVo.getR_sucdribblerate());
			pstmt.setInt(14, pVo.getR_tryshots());
			pstmt.setInt(15, pVo.getR_shotontarget());
			pstmt.setInt(16, pVo.getR_scores());
			pstmt.setInt(17, pVo.getR_arieldual());
			pstmt.setInt(18, pVo.getR_ariwons());
			pstmt.setInt(19, pVo.getR_trycut());
			pstmt.setInt(20, pVo.getR_succut());
			pstmt.setInt(21, pVo.getR_trytackle());
			pstmt.setInt(22, pVo.getR_suctackle());
			pstmt.setDouble(23, pVo.getR_suctacklerate());
			pstmt.setInt(24, pVo.getR_save());
			pstmt.setInt(25, pVo.getR_gainsgoal());
			pstmt.setString(26, pVo.getR_gamedate().toString());
			pstmt.setString(27, pVo.getR_haa());
			
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
	
	public ArrayList<PlayerRecordVO> getRecordTotal(String p_name) {

		ArrayList<PlayerRecordVO> list = new ArrayList<PlayerRecordVO>();
		String sql = "select * from playerrecord where p_name = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PlayerRecordVO pVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_name);
			rs = pstmt.executeQuery();

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd");
			
			while (rs.next()) {

				String ldate1 = rs.getDate(27).toString().substring(2, 10);
				LocalDate ldate = LocalDate.parse(ldate1, dtf);
				
				pVo = new PlayerRecordVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDouble(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
						rs.getDouble(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getDouble(14),
						rs.getInt(15), rs.getInt(16), rs.getInt(17), rs.getInt(18), rs.getInt(19),
						rs.getInt(20), rs.getInt(21), rs.getInt(22), rs.getInt(23), rs.getDouble(24),
						rs.getInt(25), rs.getInt(26), ldate, rs.getString(28));

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

	public ArrayList<String> getColumnName() {

		ArrayList<String> columnName = new ArrayList<String>();
		String sql = "select * from playerrecord";
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
