package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AccountDAO {

	public int deleteCodes(String email) {
		
		String sql = "delete from codes where a_email = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
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
	
	public ArrayList<String> getIdPW(String codes) {
		
		ArrayList<String> idpw = new ArrayList<String>();
		String sql = "select a.userid, a.pw from account a, codes c where a.email = (select c.a_email from codes where codes = ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, codes);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				idpw.add(rs.getString(1));
				idpw.add(rs.getString(2));
				
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
		
		return idpw;
		
	}
	
	public void insertCode(String email, String code) {
		
		String sql = "insert into codes values(?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, code);

			pstmt.executeUpdate();
			
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
		
	}
	
	public String getUserID(String id) {
		
		String userId = null;
		
		String sql = "select userid from account where userid = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				userId = rs.getString(1);
				
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
				
				System.out.println(se);
				
			}
			
		}
		 
		return userId;
		
	}
	
	public String getUserDivision(String id) {
		
		String div = null;
		String sql = "select division from account where userid = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				div = rs.getString(1);
				
			}
			
		} catch (SQLException se) {
		} catch (Exception e) {
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
		
		return div;
		
	}
	
	// 사용자 정보 등록
	public int insertAccount(String division, String name, String id, String pw, String email) {
		
		int i = 0;
		String sql = "insert into account values(?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, division);
			pstmt.setString(2, name);
			pstmt.setString(3, id);
			pstmt.setString(4, pw);
			pstmt.setString(5, email);
			i = pstmt.executeUpdate();
			
			if (i < 0) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사용자 생성");
				alert.setHeaderText("사용자 생성 성공!");
				alert.setContentText("생성하신 사용자로 로그인해주세요!");
				alert.showAndWait();
				
			}
			
		} catch (SQLException se) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("사용자 생성");
			alert.setHeaderText("사용자 생성 실패!");
			alert.setContentText(se + "");
			alert.showAndWait();
			
		} catch (Exception e) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("사용자 생성");
			alert.setHeaderText("사용자 생성 실패!");
			alert.setContentText(e + "");
			alert.showAndWait();
			
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
	
	// 사용자 구분 체크
	public boolean getDivision(String division) {
		
		String ceo = "관리자";
		boolean i = false;
		String sql = "select division from account where division = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ceo);
			rs = pstmt.executeQuery();
			
			if (rs.next() && rs.getString(1).equals(division)) {
				
				i = false;
					
			} else {
				
				i = true;
				
			}
			
		} catch (SQLException se) {
		} catch (Exception e) {
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
		
		return i;
		
	}
	
	// 로그인
	public Boolean logIn(String id, String pw) {
		
		Boolean i = true;
		String sql = "select userid, pw from account where userid = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				if (rs.getString(2).equals(pw)) {
					
					i = true;
					
				} else {
					
					i = false;
					
				}
				
			}
			
		} catch (SQLException se) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("로그인");
			alert.setHeaderText("로그인 실패!\nID와 PW를 확인하신 후 다시 시도해주세요!");
			alert.setContentText("로그인 실패!!");
			alert.showAndWait();
			
		} catch (Exception e) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("로그인");
			alert.setHeaderText("로그인 실패!\nID와 PW를 확인하신 후 다시 시도해주세요!");
			alert.setContentText("로그인 실패!!");
			alert.showAndWait();
			
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
		
		return i;
		
	}
	
	// ID 중복체크
	public Boolean checkID(String id) {
		
		Boolean i = true;
		String sql = "select userid from account where userid = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				i = false;
				
			}
			
		} catch (SQLException se) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("중복확인");
			alert.setHeaderText("중복확인 실패");
			alert.setContentText("중복확인에 실패하였습니다!");
			alert.showAndWait();
			
		} catch (Exception e) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("DB연결 실패");
			alert.setHeaderText("DB연결 실패!!!");
			alert.setContentText("DB연결에 실패하였습니다!");
			alert.showAndWait();
			
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
		
		return i;
		
	}
	
}
