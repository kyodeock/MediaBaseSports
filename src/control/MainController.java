package control;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import model.ClubInfoVO;

public class MainController implements Initializable {

	@FXML
	private Label lbDiv;
	@FXML
	private ImageView imgLogo;
	@FXML
	private Button btnPlayer;
	@FXML
	private Button btnClub;
	@FXML
	private Button btnContract;
	@FXML
	private Button btnProfitSpending;
	@FXML
	private Button btnExit;

	AccountDAO aDao = new AccountDAO();
	LoginController log = new LoginController();
	static String div = null;
	static String ID = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		int i = 0;

		try {

			if (div.equals("관리자")) {

				i = 1;

			} else {

				i = 2;

			}

			lbDiv.setText("사용자 구분 : " + i + " / 사용자 ID : " + ID);
			lbDiv.setDisable(true);

			if (!div.equals("관리자")) {

				btnProfitSpending.setDisable(true);

			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("로그인");
			alert.setHeaderText("등록되지 않은 사용자 ID 로그인 시도 감지!!");
			alert.setContentText("프로그램이 종료됩니다!!");
			alert.showAndWait();
			Platform.exit();

		}

		btnPlayer.setOnAction(event -> handlerBtnPlayerAction(event));
		btnClub.setOnAction(event -> handlerBtnClubAction(event));
		btnContract.setOnAction(event -> handlerBtnContractAction(event));
		btnProfitSpending.setOnAction(event -> handlerBtnProfitSpendingAction(event));
		btnExit.setOnAction(event -> handlerBtnExitAction(event));

	}

	// 계약 정보확인 버튼 메소드
	public void handlerBtnContractAction(ActionEvent event) {

		PlayerInfoDAO pdao = new PlayerInfoDAO();
		ClubInfoDAO cdao = new ClubInfoDAO();

		if (pdao.getPlayerTotal().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("계약정보 등록");
			alert.setHeaderText("등록된 선수의 정보가 없습니다!\n먼저 선수정보를 등록하신 후 다시시도해주세요!");
			alert.setContentText("등록된 선수정보 없음!");
			alert.showAndWait();

			btnPlayer.requestFocus();

		} else if (cdao.getClubTotal().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("계약정보 등록");
			alert.setHeaderText("등록된 구단정보가 없습니다!\n먼저 구단정보를 등록하신 후 다시시도해주세요!");
			alert.setContentText("등록된 구단정보 없음!");
			alert.showAndWait();

			btnClub.requestFocus();

		} else {

			try {
				Parent contract = FXMLLoader.load(getClass().getResource("/view/contract.fxml"));
				Scene mainScene = new Scene(contract);
				Stage mainStage = new Stage();
				mainStage.setScene(mainScene);
				mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
				mainStage.setTitle("협상현황 정보");
				mainStage.setResizable(false);
				Stage oldStage = (Stage) btnExit.getScene().getWindow();
				oldStage.close();
				mainStage.show();
				
				ContractInfoController.div = div;
				ContractInfoController.id = ID;
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// 구단 정보확인 버튼 메소드
	public void handlerBtnClubAction(ActionEvent event) {

		try {
			Parent club = FXMLLoader.load(getClass().getResource("/view/club.fxml"));
			Scene mainScene = new Scene(club);
			Stage mainStage = new Stage();
			mainStage.setScene(mainScene);
			mainStage.setTitle("계약구단 정보");
			mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
			mainStage.setResizable(false);
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainStage.show();
			
			ClubInfoController.div = div;
			ClubInfoController.id = ID;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 선수 정보확인 버튼 메소드
	public void handlerBtnPlayerAction(ActionEvent event) {

		ClubInfoDAO cdao = new ClubInfoDAO();
		ArrayList<ClubInfoVO> cvo = new ArrayList<ClubInfoVO>();
		cvo = cdao.getClubTotal();

		if (cvo.isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("계약선수정보 확인");
			alert.setHeaderText("등록된 구단정보가 없습니다!\n먼저 구단정보를 등록하고 다시 시도해주세요!");
			alert.setContentText("");
			alert.showAndWait();

			btnClub.requestFocus();

		} else {

			try {
				Parent player = FXMLLoader.load(getClass().getResource("/view/player.fxml"));
				Scene mainScene = new Scene(player);
				Stage mainStage = new Stage();
				mainStage.setScene(mainScene);
				mainStage.setTitle("계약선수 정보");
				mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
				mainStage.setResizable(false);
				Stage oldStage = (Stage) btnExit.getScene().getWindow();
				oldStage.close();
				mainStage.show();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// 종료 버튼 메소드
	public void handlerBtnExitAction(ActionEvent event) {

		try {
			Parent logIn = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
			Scene mainScene = new Scene(logIn);
			Stage mainStage = new Stage();
			mainStage.setScene(mainScene);
			mainStage.setTitle("로그인");
			mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
			mainStage.setResizable(false);
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 수입/지출 확인 버튼 메소드
	public void handlerBtnProfitSpendingAction(ActionEvent event) {

		try {
			Parent ps = FXMLLoader.load(getClass().getResource("/view/ps.fxml"));
			Scene mainScene = new Scene(ps);
			try {
				
				WritableImage snapShot = mainScene.snapshot(null);
				ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("C:\\Users\\alfo\\Desktop\\contractBarChart.png"));
				
				} catch (Exception e) {
					
					System.err.println(e);
					
				}
			Stage mainStage = new Stage();
			mainStage.setScene(mainScene);
			mainStage.setTitle("수수료 수익");
			mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
			mainStage.setResizable(false);
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainStage.show();
			
			PsController.div = div;
			PsController.id = ID;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
