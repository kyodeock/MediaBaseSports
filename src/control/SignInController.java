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
		cbDivision.setItems(FXCollections.observableArrayList("������", "������Ʈ"));
		cbEmail.setItems(FXCollections.observableArrayList("@naver.com", "@gmail.com"));
		txtID.setOnKeyPressed(event -> handlerTxtIDKeyPressed(event));
		txtPW.setOnKeyPressed(event -> handlerTxtPWKeyPressed(event));
		txtPWCheck.setOnKeyPressed(event -> handlerTxtPWCheckKeyPressed(event));
		btnIDCheck.setOnAction(event -> handlerBtnIDCheckAction(event));
		btnRegist.setOnAction(event -> handlerBtnRegistAction(event));
		btnExit.setOnAction(event -> handlerBtnExitAction(event));
		
	}

	// �н����� Ȯ�� �ʵ忡�� ����Ű �Է� �� �޼ҵ�
	public void handlerTxtPWCheckKeyPressed(KeyEvent event) {
		
		ActionEvent event1 = new ActionEvent();
		
		if (event.getCode() == KeyCode.ENTER && !txtPWCheck.getText().equals("")) {
			
			handlerBtnRegistAction(event1);
			
		}
		
	}
	
	// �н����� �ʵ忡�� ����Ű �Է� �� �޼ҵ�
	public void handlerTxtPWKeyPressed(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER && !txtPW.getText().equals("")) {
			
			txtPWCheck.requestFocus();
			
		}
		
	}
	
	// ���̵� �ʵ忡�� ����Ű �Է� �� �޼ҵ�
	public void handlerTxtIDKeyPressed(KeyEvent event) {
		
		ActionEvent event1 = new ActionEvent();
		
		if (event.getCode() == KeyCode.ENTER && !txtID.getText().equals("")) {
			
			handlerBtnIDCheckAction(event1);
			txtPW.requestFocus();
			
		}
		
	}
	
	// ���̵� �ߺ�üũ ��ư �޼ҵ�
	public void handlerBtnIDCheckAction(ActionEvent event) {
		
		boolean i = false;
		
		if (cbDivision.getSelectionModel().isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("����� ����");
			alert.setHeaderText("����� ������ �������ּ���!");
			alert.setContentText("����� ������ �����ϰ� �ٽ� �õ����ּ���!");
			alert.showAndWait();
			
			cbDivision.requestFocus();
			
		} else if (txtName.getText().equals("")) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("����� ����");
			alert.setHeaderText("������ �Է����ּ���!");
			alert.setContentText("������ �Է��ϰ� �ٽ� �õ����ּ���!");
			alert.showAndWait();
			
			txtName.requestFocus();
			
		} else {
			
			if (txtID.getText().equals("")) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����� ����");
				alert.setHeaderText("����Ͻ� ID�� �Է����ּ���!!");
				alert.setContentText("ID ���Է�!!");
				alert.showAndWait();
				
			}
			
			else if ((txtID.getText().length() >= 4) && (txtID.getText().length() <= 20)) {
				
				i = aDao.checkID(txtID.getText());
				
				if (i) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("����� ����");
					alert.setHeaderText("��밡���� ID!");
					alert.setContentText("��밡���� ID�Դϴ�! ��� �������ּ���!");
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
					alert.setTitle("����� ����");
					alert.setHeaderText("���Ұ����� ID!");
					alert.setContentText("���Ұ����� ID�Դϴ�!\n�ٸ� ID�� �Է��ϰ� �ٽ� �õ����ּ���!");
					alert.showAndWait();
					
					txtID.clear();
					txtID.requestFocus();
					
				}
				
			} else {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����� ����");
				alert.setHeaderText("���Ұ����� ID!");
				alert.setContentText("���Ұ����� ID�Դϴ�!\n�ٸ� ID�� �Է��ϰ� �ٽ� �õ����ּ���!");
				alert.showAndWait();
				
				txtID.clear();
				txtID.requestFocus();
				
			}
			
		}
		
	}
	
	// ��� ��ư �޼ҵ�
	public void handlerBtnRegistAction(ActionEvent evnet) {
		
		boolean i = false;
		
		i = aDao.getDivision(cbDivision.getSelectionModel().getSelectedItem());
		
		if ((txtPW.getText().length() >= 8 && txtPW.getText().length() <= 20)
				&& (txtPW.getText().equals(txtPWCheck.getText()))) {
			
			if (!i) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����� ����");
				alert.setHeaderText("����� ���� ����!");
				alert.setContentText("CEO ����ڴ� 1���� ������ �� �ֽ��ϴ�!\n����� ���� ���� �� �ٽ� �õ����ּ���!");
				alert.showAndWait();
				
				cbDivision.requestFocus();
				
			} else {
				
				String email = null;
				
				if (txtEmail.getText().equals("") || cbEmail.getSelectionModel().isEmpty()) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("����� ���");
					alert.setHeaderText("����� �̸����� ��Ȯ�ϰ� �Է����ּ���!");
					alert.setContentText("��Ȯ���� ���� ����� �̸���!");
					alert.showAndWait();
					
				}
				
				email = txtEmail.getText() + cbEmail.getSelectionModel().getSelectedItem();
				
				int j = aDao.insertAccount(cbDivision.getSelectionModel().getSelectedItem(), txtName.getText(), txtID.getText(), txtPW.getText(), email);
				
				if (j == 1) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("����� ���");
					alert.setHeaderText(txtName.getText() + " ����� ��� ����!!");
					alert.setContentText("����� ��� ����!");
					alert.showAndWait();
					
					try {
						Parent logIn = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
						Scene mainScene = new Scene(logIn);
						Stage mainStage = new Stage();
						mainStage.setScene(mainScene);
						mainStage.setTitle("�α���");
						mainStage.setResizable(false);
						Stage oldStage = (Stage) btnExit.getScene().getWindow();
						oldStage.close();
						mainStage.show();
						
					} catch(Exception e) {
						e.printStackTrace();
					}
					
				} else {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("����� ����");
					alert.setHeaderText("����� ���� ����!");
					alert.setContentText("");
					alert.showAndWait();
					
				}
				
			}
			
		} else {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("����� ����");
			alert.setHeaderText("");
			alert.setContentText("����� ���� ����!");
			alert.showAndWait();
			
			txtPWCheck.clear();
			txtPWCheck.requestFocus();
			
		}
		
	}
	
	// ���� ��ư �޼ҵ�
	public void handlerBtnExitAction(ActionEvent event) {
		
		try {
			Parent logIn = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
			Scene mainScene = new Scene(logIn);
			Stage mainStage = new Stage();
			mainStage.setScene(mainScene);
			mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
			mainStage.setTitle("�α���");
			mainStage.setResizable(false);
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
