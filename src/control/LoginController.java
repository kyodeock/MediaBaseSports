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
			dialog.setTitle("사용자 ID 찾기");
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
					alert.setTitle("인증메일 발송");
					alert.setHeaderText("이메일을 입력해주세요!");
					alert.setContentText("이메일 미입력!");
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
						email.setFrom("kyodeock@gmail.com", "진교덕", "utf-8");
						email.setSubject("MediaBase Sports 인증메일입니다");
						email.setMsg("인증코드입니다.\n코드입력창에 입력하신 후 버튼을 클릭해주세요.\n" + code);
						rt = email.send();

					} catch (EmailException e1) {

						logger.warn("Error", e1);

					} finally {

						long execTime = System.currentTimeMillis() - beginTime;
						logger.info("execTime : " + execTime + "ms");
						logger.info("RT Msg : " + rt);

					}

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("인증메일 발송");
					alert.setHeaderText("인증메일이 발송되었습니다.\n수신된 메일에서 인증코드를 확인하신 후 코드를 입력해주세요!");
					alert.setContentText("1 ~ 2분 가량 소요될 수 있습니다.\n메일이 수신되지 않았을 경우 스팸함을 확인해주세요!");
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
						alert.setTitle("사용자 ID찾기");
						alert.setHeaderText("인증코드를 입력해주세요!");
						alert.setContentText("수신받으신 인증코드를 입력해주세요!!");
						alert.showAndWait();

						txtCode.requestFocus();

					} else {

						String codes = txtCode.getText();
						String email = txtEmail.getText() + cbEmail.getSelectionModel().getSelectedItem();

						ArrayList<String> idpw = new ArrayList<String>();
						idpw = aDao.getIdPW(codes);

						if (idpw.isEmpty()) {

							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("사용자 ID찾기");
							alert.setHeaderText("해당 이메일로 등록된 ID가 없습니다!\n이메일을 확인하신 후 다시 시도해주세요!");
							alert.setContentText("정확하지 않은 이메일 입력!!");
							alert.showAndWait();

							txtEmail.requestFocus();

						} else {

							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("사용자 ID찾기");
							alert.setHeaderText("ID찾기 성공!!");
							alert.setContentText("알림창을 닫으시면 ID와 PW가 표시됩니다!");
							alert.showAndWait();

							txtID.setText(idpw.get(0));
							txtPW.setText(idpw.get(1));

						}

						int i = aDao.deleteCodes(email);

						if (i == 1) {

							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("인증코드 삭제");
							alert.setHeaderText("사용된 인증코드가 삭제됩니다!");
							alert.setContentText("사용된 인증코드 삭제완료!");
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
			mainStage.setTitle("사용자 생성");
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
			alert.setTitle("로그인");
			alert.setHeaderText("로그인 실패!");
			alert.setContentText("ID를 입력해주세요!");
			alert.showAndWait();

			txtID.requestFocus();

		} else if (txtPW.getText().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("로그인");
			alert.setHeaderText("로그인 실패!");
			alert.setContentText("PW를 입력해주세요!");
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
					mainStage.setTitle("메인메뉴");
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
					alert.setTitle("로그인");
					alert.setHeaderText("로그인 실패 " + index + "회!");
					alert.setContentText("5회 실패 시 프로그램이 종료됩니다!\nID와 PW를 확인하신 후 다시 시도해주세요!");
					alert.showAndWait();

				} else {

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("로그인");
					alert.setHeaderText("로그인 5회 실패로 프로그램이 종료됩니다!");
					alert.setContentText("프로그램이 종료됩니다!");
					alert.showAndWait();

					Platform.exit();

				}

			}

		}

	}

}
