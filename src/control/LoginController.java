package control;

import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Now;

import com.itextpdf.awt.geom.misc.RenderingHints.Key;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AccountVO;

public class LoginController implements Initializable {

	@FXML
	private ImageView imgLogo;
	@FXML
	private TextField txtID;
	@FXML
	private PasswordField txtPW;
	@FXML
	private Button btnSignIn;
	@FXML
	private Button btnLogIn;
	@FXML
	private Button btnExit;
	@FXML
	private Button btnFind;

	AccountVO aVo = new AccountVO();
	AccountDAO aDao = new AccountDAO();

	private static Logger logger = Logger.getLogger(LoginController.class);

	int index = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		txtID.setOnKeyPressed(event -> handlerTxtIDKeyPressed(event));
		txtPW.setOnKeyPressed(event -> handlerTxtPWKeyPressed(event));
		btnSignIn.setOnAction(event -> handlerBtnSignInAction(event));
		btnLogIn.setOnAction(event -> handlerBtnLogInAction(event));
		btnExit.setOnAction(event -> handlerBtnExitAction(event));
		btnFind.setOnAction(event -> handlerBtnFindAction(event));

	}

	public void handlerBtnFindAction(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/idfind.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnFind.getScene().getWindow());
			dialog.getIcons().add(new Image("/view/backgroundImg.png"));
			dialog.setTitle("����� ID ã��");
			Parent idFind = (Parent) loader.load();

			TextField txtEmail = (TextField) idFind.lookup("#txtEmail");
			ComboBox<String> cbEmail = (ComboBox<String>) idFind.lookup("#cbEmail");
			Button btnMail = (Button) idFind.lookup("#btnMail");
			TextField txtCode = (TextField) idFind.lookup("#txtCode");
			Button btnCode = (Button) idFind.lookup("#btnCode");
			TextField txtID = (TextField) idFind.lookup("#txtID");
			TextField txtPW = (TextField) idFind.lookup("#txtPW");
			Button btnCancel = (Button) idFind.lookup("#btnCancel");

			txtID.setEditable(false);
			txtPW.setEditable(false);
			cbEmail.setItems(FXCollections.observableArrayList("@naver.com", "@gmail.com"));

			btnMail.setOnAction(e -> {

				long beginTime = System.currentTimeMillis();

				MultiPartEmail email = new MultiPartEmail();

				email.setHostName("smtp.gmail.com");
				email.setSmtpPort(587);
				email.setAuthentication("kyodeock@gmail.com", "986359rz!");

				if (txtEmail.getText().equals("") || cbEmail.getSelectionModel().isEmpty()) {

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("�������� �߼�");
					alert.setHeaderText("�̸����� �Է����ּ���!");
					alert.setContentText("�̸��� ���Է�!");
					alert.showAndWait();

					txtEmail.requestFocus();

				} else {

					String rt = "Failure";
					String code = System.currentTimeMillis() + "";

					AccountDAO aDao = new AccountDAO();
					String email1 = txtEmail.getText() + cbEmail.getSelectionModel().getSelectedItem();
					aDao.insertCode(code, email1);

					try {

						email.setSSL(true);
						email.setTLS(true);
						email.addTo(txtEmail.getText() + cbEmail.getSelectionModel().getSelectedItem(),
								"MediaBase Sports", "utf-8");
						email.setFrom("kyodeock@gmail.com", "������", "utf-8");
						email.setSubject("MediaBase Sports ���������Դϴ�");
						email.setMsg("�����ڵ��Դϴ�.\n�ڵ��Է�â�� �Է��Ͻ� �� ��ư�� Ŭ�����ּ���.\n" + code);
						rt = email.send();

					} catch (EmailException e1) {

						logger.warn("Error", e1);

					} finally {

						long execTime = System.currentTimeMillis() - beginTime;
						logger.info("execTime : " + execTime + "ms");
						logger.info("RT Msg : " + rt);

					}

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("�������� �߼�");
					alert.setHeaderText("���������� �߼۵Ǿ����ϴ�.\n���ŵ� ���Ͽ��� �����ڵ带 Ȯ���Ͻ� �� �ڵ带 �Է����ּ���!");
					alert.setContentText("1 ~ 2�� ���� �ҿ�� �� �ֽ��ϴ�.\n������ ���ŵ��� �ʾ��� ��� �������� Ȯ�����ּ���!");
					alert.showAndWait();

					txtEmail.setDisable(true);
					cbEmail.setDisable(true);

				}
			});

			btnCode.setOnAction(e -> {

				txtEmail.setDisable(true);
				cbEmail.setDisable(true);

				try {

					if (txtCode.getText().isEmpty()) {

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("����� IDã��");
						alert.setHeaderText("�����ڵ带 �Է����ּ���!");
						alert.setContentText("���Ź����� �����ڵ带 �Է����ּ���!!");
						alert.showAndWait();

						txtCode.requestFocus();

					} else {

						String codes = txtCode.getText();
						String email = txtEmail.getText() + cbEmail.getSelectionModel().getSelectedItem();

						ArrayList<String> idpw = new ArrayList<String>();
						idpw = aDao.getIdPW(codes);

						if (idpw.isEmpty()) {

							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("����� IDã��");
							alert.setHeaderText("�ش� �̸��Ϸ� ��ϵ� ID�� �����ϴ�!\n�̸����� Ȯ���Ͻ� �� �ٽ� �õ����ּ���!");
							alert.setContentText("��Ȯ���� ���� �̸��� �Է�!!");
							alert.showAndWait();

							txtEmail.requestFocus();

						} else {

							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("����� IDã��");
							alert.setHeaderText("IDã�� ����!!");
							alert.setContentText("�˸�â�� �����ø� ID�� PW�� ǥ�õ˴ϴ�!");
							alert.showAndWait();

							txtID.setText(idpw.get(0));
							txtPW.setText(idpw.get(1));

						}

						int i = aDao.deleteCodes(email);

						if (i == 1) {

							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("�����ڵ� ����");
							alert.setHeaderText("���� �����ڵ尡 �����˴ϴ�!");
							alert.setContentText("���� �����ڵ� �����Ϸ�!");
							alert.showAndWait();

						} else {

						}

					}

				} catch (Exception e1) {

					System.err.println(e1);

				}

			});

			btnCancel.setOnAction(e -> {

				this.txtID.setText(txtID.getText());
				this.txtPW.setText(txtPW.getText());
				dialog.close();

			});

			Scene scene = new Scene(idFind);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();

		} catch (Exception e) {

			System.err.println(e);

		}

	}

	public void handlerTxtIDKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER && !txtID.getText().isEmpty()) {

			txtPW.requestFocus();

		}

	}

	public void handlerTxtPWKeyPressed(KeyEvent event) {

		ActionEvent event1 = new ActionEvent();

		if (event.getCode() == KeyCode.ENTER && !txtPW.getText().isEmpty()) {

			handlerBtnLogInAction(event1);

		}

	}

	public void handlerBtnExitAction(ActionEvent event) {

		Platform.exit();

	}

	public void handlerBtnSignInAction(ActionEvent event) {

		try {
			Parent signIn = FXMLLoader.load(getClass().getResource("/view/signin.fxml"));
			Scene mainScene = new Scene(signIn);
			Stage mainStage = new Stage();
			mainStage.setScene(mainScene);
			mainStage.setTitle("����� ����");
			mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
			mainStage.setResizable(false);
			Stage oldStage = (Stage) btnLogIn.getScene().getWindow();
			oldStage.close();
			mainStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void handlerBtnLogInAction(ActionEvent event) {

		boolean i = false;

		if (txtID.getText().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�α���");
			alert.setHeaderText("�α��� ����!");
			alert.setContentText("ID�� �Է����ּ���!");
			alert.showAndWait();

			txtID.requestFocus();

		} else if (txtPW.getText().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�α���");
			alert.setHeaderText("�α��� ����!");
			alert.setContentText("PW�� �Է����ּ���!");
			alert.showAndWait();

			txtPW.requestFocus();

		} else {

			i = aDao.logIn(txtID.getText(), txtPW.getText());

			MainController.div = aDao.getUserDivision(txtID.getText());
			MainController.ID = txtID.getText();
			PlayerInfoController.div = aDao.getUserDivision(txtID.getText());
			PlayerInfoController.id = txtID.getText();
			
			if (i) {

				try {
					Parent main = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
					Scene mainScene = new Scene(main);
					Stage mainStage = new Stage();
					mainStage.setScene(mainScene);
					mainStage.setTitle("���θ޴�");
					mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
					mainStage.setResizable(false);
					Stage oldStage = (Stage) btnLogIn.getScene().getWindow();
					oldStage.close();
					mainStage.show();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			} else {

				index++;

				if (index < 5) {

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("�α���");
					alert.setHeaderText("�α��� ���� " + index + "ȸ!");
					alert.setContentText("5ȸ ���� �� ���α׷��� ����˴ϴ�!\nID�� PW�� Ȯ���Ͻ� �� �ٽ� �õ����ּ���!");
					alert.showAndWait();

				} else {

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("�α���");
					alert.setHeaderText("�α��� 5ȸ ���з� ���α׷��� ����˴ϴ�!");
					alert.setContentText("���α׷��� ����˴ϴ�!");
					alert.showAndWait();

					Platform.exit();

				}

			}

		}

	}

}
