package control;

import java.net.URL;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import com.sun.javafx.binding.StringFormatter;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class SignInController implements Initializable{

	@FXML
	private ImageView imgLogo;
	@FXML
	private ComboBox<String> cbDivision;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtID;
	@FXML
	private PasswordField txtPW;
	@FXML
	private PasswordField txtPWCheck;
	@FXML
	private Button btnIDCheck;
	@FXML
	private Button btnRegist;
	@FXML
	private Button btnExit;
	@FXML
	private ImageView imgCheck;
	@FXML
	private ImageView imgSignin;
	@FXML
	private ImageView imgExit;
	@FXML
	private Label lbID;
	@FXML
	private Label lbPW;
	@FXML
	private Label lbPWCheck;
	@FXML
	private ComboBox<String> cbEmail;
	@FXML
	private TextField txtEmail;
	
	AccountDAO aDao = new AccountDAO();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		lbID.setStyle("-fx-alignment:CENTER");
		lbPW.setStyle("-fx-alignment:CENTER");
		lbPWCheck.setStyle("-fx-alignment:CENTER");
		
		btnRegist.setDisable(true);
		txtPW.setDisable(true);
		txtPWCheck.setDisable(true);
		txtEmail.setDisable(true);
		cbEmail.setDisable(true);
		cbDivision.setItems(FXCollections.observableArrayList("관리자", "에이전트"));
		cbEmail.setItems(FXCollections.observableArrayList("@naver.com", "@gmail.com"));
		txtID.setOnKeyPressed(event -> handlerTxtIDKeyPressed(event));
		txtPW.setOnKeyPressed(event -> handlerTxtPWKeyPressed(event));
		txtPWCheck.setOnKeyPressed(event -> handlerTxtPWCheckKeyPressed(event));
		btnIDCheck.setOnAction(event -> handlerBtnIDCheckAction(event));
		btnRegist.setOnAction(event -> handlerBtnRegistAction(event));
		btnExit.setOnAction(event -> handlerBtnExitAction(event));
		
	}

	// 패스워드 확인 필드에서 엔터키 입력 시 메소드
	public void handlerTxtPWCheckKeyPressed(KeyEvent event) {
		
		ActionEvent event1 = new ActionEvent();
		
		if (event.getCode() == KeyCode.ENTER && !txtPWCheck.getText().equals("")) {
			
			handlerBtnRegistAction(event1);
			
		}
		
	}
	
	// 패스워드 필드에서 엔터키 입력 시 메소드
	public void handlerTxtPWKeyPressed(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER && !txtPW.getText().equals("")) {
			
			txtPWCheck.requestFocus();
			
		}
		
	}
	
	// 아이디 필드에서 엔터키 입력 시 메소드
	public void handlerTxtIDKeyPressed(KeyEvent event) {
		
		ActionEvent event1 = new ActionEvent();
		
		if (event.getCode() == KeyCode.ENTER && !txtID.getText().equals("")) {
			
			handlerBtnIDCheckAction(event1);
			txtPW.requestFocus();
			
		}
		
	}
	
	// 아이디 중복체크 버튼 메소드
	public void handlerBtnIDCheckAction(ActionEvent event) {
		
		boolean i = false;
		
		if (cbDivision.getSelectionModel().isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("사용자 생성");
			alert.setHeaderText("사용자 구분을 선택해주세요!");
			alert.setContentText("사용자 구분을 선택하고 다시 시도해주세요!");
			alert.showAndWait();
			
			cbDivision.requestFocus();
			
		} else if (txtName.getText().equals("")) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("사용자 생성");
			alert.setHeaderText("성명을 입력해주세요!");
			alert.setContentText("성명을 입력하고 다시 시도해주세요!");
			alert.showAndWait();
			
			txtName.requestFocus();
			
		} else {
			
			if (txtID.getText().equals("")) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사용자 생성");
				alert.setHeaderText("사용하실 ID를 입력해주세요!!");
				alert.setContentText("ID 미입력!!");
				alert.showAndWait();
				
			}
			
			else if ((txtID.getText().length() >= 4) && (txtID.getText().length() <= 20)) {
				
				i = aDao.checkID(txtID.getText());
				
				if (i) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("사용자 생성");
					alert.setHeaderText("사용가능한 ID!");
					alert.setContentText("사용가능한 ID입니다! 계속 진행해주세요!");
					alert.showAndWait();
					
					txtPW.setDisable(false);
					txtPW.requestFocus();
					txtPWCheck.setDisable(false);
					txtEmail.setDisable(false);
					cbEmail.setDisable(false);
					btnIDCheck.setDisable(true);
					btnRegist.setDisable(false);
					
				} else {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("사용자 생성");
					alert.setHeaderText("사용불가능한 ID!");
					alert.setContentText("사용불가능한 ID입니다!\n다른 ID를 입력하고 다시 시도해주세요!");
					alert.showAndWait();
					
					txtID.clear();
					txtID.requestFocus();
					
				}
				
			} else {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사용자 생성");
				alert.setHeaderText("사용불가능한 ID!");
				alert.setContentText("사용불가능한 ID입니다!\n다른 ID를 입력하고 다시 시도해주세요!");
				alert.showAndWait();
				
				txtID.clear();
				txtID.requestFocus();
				
			}
			
		}
		
	}
	
	// 등록 버튼 메소드
	public void handlerBtnRegistAction(ActionEvent evnet) {
		
		boolean i = false;
		
		i = aDao.getDivision(cbDivision.getSelectionModel().getSelectedItem());
		
		if ((txtPW.getText().length() >= 8 && txtPW.getText().length() <= 20)
				&& (txtPW.getText().equals(txtPWCheck.getText()))) {
			
			if (!i) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("사용자 생성");
				alert.setHeaderText("사용자 생성 실패!");
				alert.setContentText("CEO 사용자는 1개만 생성할 수 있습니다!\n사용자 구분 변경 후 다시 시도해주세요!");
				alert.showAndWait();
				
				cbDivision.requestFocus();
				
			} else {
				
				String email = null;
				
				if (txtEmail.getText().equals("") || cbEmail.getSelectionModel().isEmpty()) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("사용자 등록");
					alert.setHeaderText("사용자 이메일을 정확하게 입력해주세요!");
					alert.setContentText("정확하지 않은 사용자 이메일!");
					alert.showAndWait();
					
				}
				
				email = txtEmail.getText() + cbEmail.getSelectionModel().getSelectedItem();
				
				int j = aDao.insertAccount(cbDivision.getSelectionModel().getSelectedItem(), txtName.getText(), txtID.getText(), txtPW.getText(), email);
				
				if (j == 1) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("사용자 등록");
					alert.setHeaderText(txtName.getText() + " 사용자 등록 성공!!");
					alert.setContentText("사용자 등록 성공!");
					alert.showAndWait();
					
					try {
						Parent logIn = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
						Scene mainScene = new Scene(logIn);
						Stage mainStage = new Stage();
						mainStage.setScene(mainScene);
						mainStage.setTitle("로그인");
						mainStage.setResizable(false);
						Stage oldStage = (Stage) btnExit.getScene().getWindow();
						oldStage.close();
						mainStage.show();
						
					} catch(Exception e) {
						e.printStackTrace();
					}
					
				} else {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("사용자 생성");
					alert.setHeaderText("사용자 생성 실패!");
					alert.setContentText("");
					alert.showAndWait();
					
				}
				
			}
			
		} else {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("사용자 생성");
			alert.setHeaderText("");
			alert.setContentText("사용자 생성 실패!");
			alert.showAndWait();
			
			txtPWCheck.clear();
			txtPWCheck.requestFocus();
			
		}
		
	}
	
	// 종료 버튼 메소드
	public void handlerBtnExitAction(ActionEvent event) {
		
		try {
			Parent logIn = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
			Scene mainScene = new Scene(logIn);
			Stage mainStage = new Stage();
			mainStage.setScene(mainScene);
			mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
			mainStage.setTitle("로그인");
			mainStage.setResizable(false);
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
