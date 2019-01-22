package control;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.ResourceBundle;

import com.sun.javafx.binding.StringFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.PlayerInfoVO;
import model.PlayerRecordVO;

public class PlayerInfoController implements Initializable{

	@FXML
	private Button btnLogo;
	@FXML
	private Label lbDiv;
	@FXML
	private TextField txtName;
	@FXML
	private ComboBox<String> cbGender;
	@FXML
	private ComboBox<String> cbDivision;
	@FXML
	private DatePicker dpBirth;
	@FXML
	private TextField txtHeight;
	@FXML
	private TextField txtWeight;
	@FXML
	private TextField txtPhone;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtNation;
	@FXML
	private ComboBox<String> cbVisa;
	@FXML
	private ComboBox<String> cbPosition;
	@FXML
	private ComboBox<String> cbPfoot;
	@FXML
	private ComboBox<String> cbAttached;
	@FXML
	private TextField txtTotApps;
	@FXML
	private TextField txtTotScores;
	@FXML
	private TextField txtTotAssists;
	@FXML
	private TextField txtAvgRate;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtHtA;
	@FXML
	private ImageView imgPlayer;
	@FXML
	private Button btnRegist;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnImgPath;
	@FXML
	private Button btnExit;
	@FXML
	private TableView<PlayerInfoVO> tableView;
	@FXML
	private TextField txtPath;
	
	PlayerInfoVO pVo = null;
	PlayerInfoDAO pDao = new PlayerInfoDAO();
	String i = "1000";
	static String div = null;
	static String id = null;
	private Stage primaryStage;
	String selectFileName = "";
	String localUrl = "";
	Image localImage;
	File selectedFile = null;
	int selectedindex = 0;
	
	ObservableList<PlayerInfoVO> data = FXCollections.observableArrayList();
	ArrayList<String> agent = new ArrayList<String>();
	ObservableList<PlayerRecordVO> recordData = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		int i = 0;
		
		// 사용자 구분이 CEO일 경우
		if (div.equals("CEO")) {
			
			// i 값이 1이됨
			i = 1;
			
		} else {
			
			// i 값이 2가됨
			i = 2;
			
		}
		
		// 등록된 에이전트의 이름을 불러옴
		// 사용자 구분과 로그인한 ID를 보여줌
		lbDiv.setText("사용자 구분 : " + i + "/ 사용자 ID : " + id);
		lbDiv.setDisable(true);
		
		btnRegist.setDisable(true);
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		
		cbAttached.setItems(FXCollections.observableArrayList(pDao.getf_name()));
		
		// 실행 시 초기 설정
		cbGender.setItems(FXCollections.observableArrayList("남성", "여성"));
		cbDivision.setItems(FXCollections.observableArrayList("프로", "유소년"));
		dpBirth.setValue(LocalDate.now());
		cbVisa.setItems(FXCollections.observableArrayList("Y", "N"));
		cbPosition.setItems(FXCollections.observableArrayList("공격수", "미드필더", "수비수", "골키퍼"));
		cbPfoot.setItems(FXCollections.observableArrayList("오른발", "왼발", "양발"));
		tableView.setEditable(false);
		txtPath.setEditable(false);
		dpBirth.setEditable(false);
		
		// 테이블 컬럼 지정
		TableColumn colNo = new TableColumn<>("번호");
		colNo.setPrefWidth(50);
		colNo.setStyle("-fx-alignment:CENTER");
		colNo.setCellValueFactory(new PropertyValueFactory<>("p_rNo"));
		
		TableColumn colDiv = new TableColumn<>("구분");
		colDiv.setPrefWidth(80);
		colDiv.setStyle("-fx-alignment:CENTER");
		colDiv.setCellValueFactory(new PropertyValueFactory<>("p_division"));
		
		TableColumn colName = new TableColumn<>("성명");
		colName.setPrefWidth(150);
		colName.setStyle("-fx-alignment:CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("p_name"));
		
		TableColumn colBirth = new TableColumn<>("생년월일");
		colBirth.setPrefWidth(122);
		colBirth.setStyle("-fx-alignment:CENTER");
		colBirth.setCellValueFactory(new PropertyValueFactory<>("p_birth"));
		
		TableColumn colHeight = new TableColumn<>("신장");
		colHeight.setPrefWidth(80);
		colHeight.setStyle("-fx-alignment:CENTER");
		colHeight.setCellValueFactory(new PropertyValueFactory<>("p_height"));
		
		TableColumn colWeight = new TableColumn<>("체중");
		colWeight.setPrefWidth(80);
		colWeight.setStyle("-fx-alignment:CENTER");
		colWeight.setCellValueFactory(new PropertyValueFactory<>("p_weight"));
		
		TableColumn colPosition = new TableColumn<>("포지션");
		colPosition.setPrefWidth(80);
		colPosition.setStyle("-fx-alignment:CENTER");
		colPosition.setCellValueFactory(new PropertyValueFactory<>("p_position"));
		
		TableColumn colNat = new TableColumn<>("국적");
		colNat.setPrefWidth(80);
		colNat.setStyle("-fx-alignment:CENTER");
		colNat.setCellValueFactory(new PropertyValueFactory<>("p_nationality"));
		
		TableColumn colVisa = new TableColumn<>("비자여부");
		colVisa.setPrefWidth(50);
		colVisa.setStyle("-fx-alignment:CENTER");
		colVisa.setCellValueFactory(new PropertyValueFactory<>("p_visa"));
		
		TableColumn colPhone = new TableColumn<>("연락처");
		colPhone.setPrefWidth(150);
		colPhone.setStyle("-fx-alignment:CENTER");
		colPhone.setCellValueFactory(new PropertyValueFactory<>("p_phone"));
		
		TableColumn colAd = new TableColumn<>("주소");
		colAd.setPrefWidth(200);
		colAd.setStyle("-fx-alignment:CENTER");
		colAd.setCellValueFactory(new PropertyValueFactory<>("p_address"));
		
		TableColumn colemail = new TableColumn<>("이메일");
		colemail.setPrefWidth(145);
		colemail.setStyle("-fx-alignment:CENTER");
		colemail.setCellValueFactory(new PropertyValueFactory<>("p_email"));
		
		TableColumn colAt = new TableColumn<>("소속구단명");
		colAt.setPrefWidth(145);
		colAt.setStyle("-fx-alignment:CENTER");
		colAt.setCellValueFactory(new PropertyValueFactory<>("p_attached"));
		
		TableColumn colScore = new TableColumn<>("통산득점");
		colScore.setPrefWidth(80);
		colScore.setStyle("-fx-alignment:CENTER");
		colScore.setCellValueFactory(new PropertyValueFactory<>("p_totalscores"));
		
		TableColumn colAs = new TableColumn<>("통산도움");
		colAs.setPrefWidth(80);
		colAs.setStyle("-fx-alignment:CENTER");
		colAs.setCellValueFactory(new PropertyValueFactory<>("p_totalassists"));
		
		TableColumn colApps = new TableColumn<>("통산출장");
		colApps.setPrefWidth(80);
		colApps.setStyle("-fx-alignment:CENTER");
		colApps.setCellValueFactory(new PropertyValueFactory<>("p_totalapps"));
		
		TableColumn colRate = new TableColumn<>("통산평점");
		colRate.setPrefWidth(80);
		colRate.setStyle("-fx-alignment:CENTER");
		colRate.setCellValueFactory(new PropertyValueFactory<>("p_avgrate"));
		
		TableColumn colPf = new TableColumn<>("주발");
		colPf.setPrefWidth(80);
		colPf.setStyle("-fx-alignment:CENTER");
		colPf.setCellValueFactory(new PropertyValueFactory<>("p_pfoot"));
		
		TableColumn colHtA = new TableColumn<>("희망사항");
		colHtA.setPrefWidth(80);
		colHtA.setStyle("-fx-alignment:CENTER");
		colHtA.setCellValueFactory(new PropertyValueFactory<>("p_hta"));
		
		TableColumn colGen = new TableColumn<>("성별");
		colGen.setPrefWidth(80);
		colGen.setStyle("-fx-alignment:CENTER");
		colGen.setCellValueFactory(new PropertyValueFactory<>("p_gender"));
		
		TableColumn colImg = new TableColumn<>("선수이미지");
		colImg.setPrefWidth(300);
		colImg.setStyle("-fx-alignment:CENTER");
		colImg.setCellValueFactory(new PropertyValueFactory<>("p_imgpath"));
	
		// 테이블 데이터 설정
		tableView.setItems(data);
		
		// 테이블 컬럼 설정
		tableView.getColumns().addAll(colNo, colDiv, colName, colBirth,
				colHeight, colWeight, colPosition, colNat, colVisa,
				colPhone, colAd, colemail, colScore, colAs, colApps, colRate,
				colPf, colHtA, colGen, colImg);
		
		// 데이터베이스에서 테이블에 값을 불러옴
		totalList();
		
		// 컨트롤 이벤트
		btnRegist.setOnAction(event -> handlerBtnRegistAction(event));
		btnEdit.setOnAction(event -> handlerBtnEditAction(event));
		btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));
		btnExit.setOnAction(event -> handlerBtnExitAction(event));
		btnSearch.setOnAction(event -> handlerBtnSearchAction(event));
		btnImgPath.setOnAction(event -> handlerBtnImgPathAction(event));
		tableView.setOnMouseClicked(event -> handlerTableViewMouseClicked(event));
		btnLogo.setOnAction(event -> handlerBtnLogoAction(event));
		
	}

	// 초기화 버튼 메소드
	public void handlerBtnLogoAction(ActionEvent event) {
		
		btnRegist.setDisable(true);
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		txtName.clear();
		txtName.setEditable(true);
		txtName.setDisable(false);
		cbGender.getSelectionModel().clearSelection();
		cbGender.setDisable(false);
		cbDivision.getSelectionModel().clearSelection();
		cbDivision.setDisable(false);
		dpBirth.setValue(LocalDate.now());
		dpBirth.setDisable(false);
		txtHeight.clear();
		txtHeight.setEditable(true);
		txtHeight.setDisable(false);
		txtWeight.clear();
		txtWeight.setEditable(true);
		txtWeight.setDisable(false);
		txtPhone.clear();
		txtPhone.setEditable(true);
		txtPhone.setDisable(false);
		txtEmail.clear();
		txtEmail.setEditable(true);
		txtEmail.setDisable(false);
		txtNation.clear();
		txtNation.setEditable(true);
		txtNation.setDisable(false);
		cbVisa.getSelectionModel().clearSelection();
		cbVisa.setDisable(false);
		cbPosition.getSelectionModel().clearSelection();
		cbPosition.setDisable(false);
		cbPfoot.getSelectionModel().clearSelection();
		cbPfoot.setDisable(false);
		cbAttached.getSelectionModel().clearSelection();
		txtTotApps.clear();
		txtTotApps.setEditable(true);
		txtTotApps.setDisable(false);
		txtTotScores.clear();
		txtTotScores.setEditable(true);
		txtTotScores.setDisable(false);
		txtTotAssists.clear();
		txtTotAssists.setEditable(true);
		txtTotAssists.setDisable(false);
		txtAvgRate.clear();
		txtAvgRate.setEditable(true);
		txtAvgRate.setDisable(false);
		txtAddress.clear();
		txtAddress.setEditable(true);
		txtAddress.setDisable(false);
		txtHtA.clear();
		txtHtA.setEditable(true);
		txtHtA.setDisable(false);
		Image img = new Image("/view/NoImg.png");
		imgPlayer.setImage(img);
		data.removeAll(data);
		totalList();
		txtPath.clear();
		txtPath.setDisable(false);
		
	}
	
	// 검색버튼 메소드
	public void handlerBtnSearchAction(ActionEvent event) {
		
		PlayerInfoVO pvo = new PlayerInfoVO();
		PlayerInfoDAO pdao = null;

		Object[][] totalData = null;

		String searchName = "";
		String alertName = "";
		String searchDate = "";
		boolean searchResult = false;

		try {

			searchName = txtName.getText();
			alertName = txtName.getText();
			pdao = new PlayerInfoDAO();
			pvo = pdao.getSearchData(searchName);

			if (searchName.equals("")) {

				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("선수 정보 검색");
				alert.setHeaderText("선수의 이름을 입력해주세요!");
				alert.setContentText("다음에는 주의해주세요!");
				alert.showAndWait();

			} 
			
			if ((!searchName.equals("") && pvo != null)) {

				ArrayList<String> title;
				ArrayList<PlayerInfoVO> list;

				title = pdao.getColumnName();
				int columnCount = title.size();

				list = pdao.getPlayerTotal();
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];

				if (pvo.getP_name().equals(searchName)) {

					txtName.clear();
					data.removeAll(data);
					for (int index = 0; index < rowCount; index++) {

						pvo = list.get(index);
						if (pvo.getP_name().equals(searchName)) {

							data.add(pvo);
							searchResult = true;

						}

					}

				}

			}
			
			if (!searchResult) {

				txtName.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("선수 정보 검색");
				alert.setHeaderText(alertName + " 선수가 리스트에 없습니다");
				alert.setContentText("다시 검색해주세요!");
				alert.showAndWait();

			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("선수 정보 검색");
			alert.setHeaderText("선수 정보 검색에 오류가 발생하였습니다!");
			alert.setContentText("다시 검색해주세요!");
			alert.showAndWait();

		}
		
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		
	}
	
	// 이미지 경로 버튼 메소드
	public void handlerBtnImgPathAction(ActionEvent event) {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
		
		try {
			
			selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				
				localUrl = selectedFile.toURI().toURL().toString();
				
			}
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			
		}
		
		txtPath.setText(localUrl);
		localImage = new Image(localUrl, false);
		imgPlayer.setImage(localImage);
		imgPlayer.setFitHeight(200);
		imgPlayer.setFitWidth(220);
		btnRegist.setDisable(false);
		
		if (selectedFile != null) {
			
			selectFileName = selectedFile.getName();
			
		}
		
	}
	
	// 닫기버튼 메소드
	public void handlerBtnExitAction(ActionEvent event) {
		
		try {
			Parent main = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
			Scene mainScene = new Scene(main);
			Stage mainStage = new Stage();
			mainStage.setScene(mainScene);
			mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
			mainStage.setTitle("메인메뉴");
			mainStage.setResizable(false);
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 수정버튼 메소드
	public void handlerBtnEditAction(ActionEvent event) {
		
		int i = 0;
		
		pVo = new PlayerInfoVO(tableView.getSelectionModel().getSelectedItem().getP_rNo(), 
				cbDivision.getSelectionModel().getSelectedItem(), cbAttached.getSelectionModel().getSelectedItem(),
				txtName.getText(), dpBirth.getValue(), Double.parseDouble(txtHeight.getText()), Double.parseDouble(txtWeight.getText()),
				cbPosition.getSelectionModel().getSelectedItem(), txtNation.getText(), cbVisa.getSelectionModel().getSelectedItem(),
				txtPhone.getText(), txtAddress.getText(), txtEmail.getText(), Integer.parseInt(txtTotScores.getText()),
				Integer.parseInt(txtTotAssists.getText()), Integer.parseInt(txtTotApps.getText()),
				Double.parseDouble(txtAvgRate.getText()), cbPfoot.getSelectionModel().getSelectedItem(),
				txtHtA.getText(), cbGender.getSelectionModel().getSelectedItem(), txtPath.getText());
		
		// sql문이 제대로 실행됐을 경우 i = 1, 제대로 실행되지 않았을 경우 1이 아닌 값을 가짐
		i = pDao.updatePlayerInfo(pVo);
		
		// i가 1일 경우 수정 성공 알림창
		if (i == 1) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 수정");
			alert.setHeaderText(pVo.getP_name() + "선수의 정보가 수정되었습니다!");
			alert.setContentText("선수 정보 수정 완료!");
			alert.showAndWait();
		
		// i가 1이 아닐 경우 수정 실패 알림창
		} else {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 수정");
			alert.setHeaderText(pVo.getP_name() + "선수의 정보를 수정하지 못했습니다!");
			alert.setContentText("선수 정보 수정 실패!");
			alert.showAndWait();
			
		}
		
		// 초기화 버튼 메소드 호출
		handlerBtnLogoAction(event);
		
	}
	
	// 삭제 버튼 메소드
	public void handlerBtnDeleteAction(ActionEvent event) {
		
		int i = 0;
		
		// sql문이 제대로 실행됐을 경우 i = 1, 아닐 경우 i는 1이 아닌 값을 가짐
		i = pDao.deletePlayerInfo((tableView.getSelectionModel().getSelectedIndex() + 1) + "");
		
		// i가 1일 경우 정보 삭제 성공 알림창
		if (i == 1) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 삭제");
			alert.setHeaderText(tableView.getSelectionModel().getSelectedItem().getP_name() + " 선수의 정보가 삭제되었습니다!");
			alert.setContentText("선수 정보 삭제 완료!");
			alert.showAndWait();
		
		// 아닐 경우 정보 삭제 실패 알림창
		} else {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 삭제");
			alert.setHeaderText("선수 정보 삭제 실패!");
			alert.setContentText("선수 정보 삭제를 진행할 수 없습니다!");
			alert.showAndWait();
			
		}
		
		// 초기화 버튼 메소드 호출
		handlerBtnLogoAction(event);
		
	}
	
	// 데이터베이스에 등록된 전체 선수의 정보를 불러와서 data에 저장
	public void totalList() {
		
		Object[][] totalData;
		PlayerInfoDAO pDao = new PlayerInfoDAO();
		PlayerInfoVO pVo = null;
		ArrayList<String> title;
		ArrayList<PlayerInfoVO> list;

		title = pDao.getColumnName();
		int columnCount = title.size();

		list = pDao.getPlayerTotal();
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {

			pVo = list.get(index);
			data.add(pVo);

		}
		
	}
	
	// 테이블 뷰 메소드
	public void handlerTableViewMouseClicked(MouseEvent event) {
		
		// 1번 클릭, 테이블뷰에 표시된 선수 정보가 없을 경우 선수 정보 없음 알림창
		if (event.getClickCount() == 1 && tableView.getSelectionModel().isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 등록");
			alert.setHeaderText("등록된 선수 정보가 없습니다!\n먼저 선수를 등록하신 후 다시 시도해주세요!");
			alert.setContentText("선수 정보 없음!");
			alert.showAndWait();
			
		// 1번 클릭, 테이블 뷰에 표시된 선수 정보 있을 경우
		} else if (event.getClickCount() == 1 && !tableView.getSelectionModel().isEmpty()){
			
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
			btnRegist.setDisable(true);
			
			txtName.setDisable(true);
			dpBirth.setDisable(true);
			txtNation.setDisable(true);
			cbGender.setDisable(true);
			
			txtName.setText(tableView.getSelectionModel().getSelectedItem().getP_name());
			cbDivision.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getP_division());
			dpBirth.setValue(tableView.getSelectionModel().getSelectedItem().getP_birth());
			txtHeight.setText(tableView.getSelectionModel().getSelectedItem().getP_height() + "");
			txtWeight.setText(tableView.getSelectionModel().getSelectedItem().getP_weight() + "");
			cbPosition.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getP_position());
			txtNation.setText(tableView.getSelectionModel().getSelectedItem().getP_nationality());
			cbVisa.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getP_visa());
			txtPhone.setText(tableView.getSelectionModel().getSelectedItem().getP_phone());
			txtAddress.setText(tableView.getSelectionModel().getSelectedItem().getP_address());
			txtTotScores.setText(tableView.getSelectionModel().getSelectedItem().getP_totalscores() + "");
			txtTotAssists.setText(tableView.getSelectionModel().getSelectedItem().getP_totalassists() + "");
			txtTotApps.setText(tableView.getSelectionModel().getSelectedItem().getP_totalapps() + "");
			txtAvgRate.setText(tableView.getSelectionModel().getSelectedItem().getP_avgrate() + "");
			cbPfoot.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getP_pfoot());
			txtHtA.setText(tableView.getSelectionModel().getSelectedItem().getP_hta());
			cbGender.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getP_gender());
			txtPath.setText(tableView.getSelectionModel().getSelectedItem().getP_imgpath());
			cbAttached.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getP_attached());
			txtEmail.setText(tableView.getSelectionModel().getSelectedItem().getP_email());
			
			String imgUrl = txtPath.getText();
			Image playerImg = new Image(imgUrl);
			imgPlayer.setImage(playerImg);
			imgPlayer.setFitHeight(200);
			imgPlayer.setFitWidth(220);
			
		// 더블클릭, 테이블 뷰에 표시된 선수 정보 있을 경우
		} else if (event.getClickCount() == 2){
			
			try {

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/playerRecentlyRecord.fxml"));

				Stage dialog = new Stage(StageStyle.UTILITY);
				dialog.initModality(Modality.WINDOW_MODAL);
				dialog.initOwner(btnEdit.getScene().getWindow());
				dialog.getIcons().add(new Image("/view/backgroundImg.png"));
				dialog.setTitle("선수 최근 경기기록");

				Parent parentRecentlyRecord = (Parent) loader.load();
				PlayerInfoVO player = tableView.getSelectionModel().getSelectedItem();
				TextField txtP_name = (TextField) parentRecentlyRecord.lookup("#txtP_name");
				TextField txtV_Club = (TextField) parentRecentlyRecord.lookup("#txtV_Club");
				TextField txtAppsTime = (TextField) parentRecentlyRecord.lookup("#txtAppsTime");
				TextField txtAppsRate = (TextField) parentRecentlyRecord.lookup("#txtAppsRate");
				TextField txtBallTouch = (TextField) parentRecentlyRecord.lookup("#txtBallTouch");
				TextField txtTryPass = (TextField) parentRecentlyRecord.lookup("#txtTryPass");
				TextField txtKeyPass = (TextField) parentRecentlyRecord.lookup("#txtKeyPass");
				TextField txtSuccessPass = (TextField) parentRecentlyRecord.lookup("#txtSuccessPass");
				TextField txtPSuccessRate = (TextField) parentRecentlyRecord.lookup("#txtPSuccessRate");
				TextField txtAssists = (TextField) parentRecentlyRecord.lookup("#txtAssists");
				TextField txtTryDribble = (TextField) parentRecentlyRecord.lookup("#txtTryDribble");
				TextField txtSuccessDribble = (TextField) parentRecentlyRecord.lookup("#txtSuccessDribble");
				TextField txtDSuccessRate = (TextField) parentRecentlyRecord.lookup("#txtDSuccessRate");
				TextField txtTryShots = (TextField) parentRecentlyRecord.lookup("#txtTryShots");
				TextField txtShotOnTarget = (TextField) parentRecentlyRecord.lookup("#txtShotOnTarget");
				TextField txtGoal = (TextField) parentRecentlyRecord.lookup("#txtGoal");
				TextField txtTryArielDual = (TextField) parentRecentlyRecord.lookup("#txtTryArielDual");
				TextField txtSuccessArielDual = (TextField) parentRecentlyRecord.lookup("#txtSuccessArielDual");
				TextField txtTryCutPass = (TextField) parentRecentlyRecord.lookup("#txtTryCutPass");
				TextField txtSuccessCutPass = (TextField) parentRecentlyRecord.lookup("#txtSuccessCutPass");
				TextField txtTryTackle = (TextField) parentRecentlyRecord.lookup("#txtTryTackle");
				TextField txtSuccessTackle = (TextField) parentRecentlyRecord.lookup("#txtSuccessTackle");
				TextField txtTSuccessRate = (TextField) parentRecentlyRecord.lookup("#txtTSuccessRate");
				TextField txtTrySave = (TextField) parentRecentlyRecord.lookup("#txtTrySave");
				TextField txtGainsGoal = (TextField) parentRecentlyRecord.lookup("#txtGainsGoal");
				DatePicker dpGameDate = (DatePicker) parentRecentlyRecord.lookup("#dpGameDate");
				ComboBox<String> cbHandAway = (ComboBox<String>) parentRecentlyRecord.lookup("#cbHandAway");
				TableView<PlayerRecordVO> tableView = (TableView<PlayerRecordVO>) parentRecentlyRecord.lookup("#tableView");
				Button btnRegist = (Button) parentRecentlyRecord.lookup("#btnRegist");
				Button btnLogo = (Button) parentRecentlyRecord.lookup("#btnLogo");
				Button btnCancel = (Button) parentRecentlyRecord.lookup("#btnCancel");
				Button btnCalc = (Button) parentRecentlyRecord.lookup("#btnCalc");
				
				TableColumn colG_no = new TableColumn("경기번호");
				colG_no.setPrefWidth(80);
				colG_no.setStyle("-fx-alignment:CENTER");
				colG_no.setCellValueFactory(new PropertyValueFactory<>("g_no"));
				
				TableColumn colP_name = new TableColumn("선수명");
				colP_name.setPrefWidth(80);
				colP_name.setStyle("-fx-alignment:CENTER");
				colP_name.setCellValueFactory(new PropertyValueFactory<>("p_name"));

				TableColumn colF_name = new TableColumn("상대구단명");
				colF_name.setPrefWidth(80);
				colF_name.setStyle("-fx-alignment:CENTER");
				colF_name.setCellValueFactory(new PropertyValueFactory<>("f_name"));
				
				TableColumn colA_time = new TableColumn("출장시간");
				colA_time.setPrefWidth(80);
				colA_time.setStyle("-fx-alignment:CENTER");
				colA_time.setCellValueFactory(new PropertyValueFactory<>("r_appstime"));
				
				TableColumn colA_rate = new TableColumn("경기평점");
				colA_rate.setPrefWidth(80);
				colA_rate.setStyle("-fx-alignment:CENTER");
				colA_rate.setCellValueFactory(new PropertyValueFactory<>("r_appsrate"));
				
				TableColumn colB_touch = new TableColumn("볼터치횟수");
				colB_touch.setPrefWidth(80);
				colB_touch.setStyle("-fx-alignment:CENTER");
				colB_touch.setCellValueFactory(new PropertyValueFactory<>("r_balltouch"));
				
				TableColumn colT_pass = new TableColumn("패스시도횟수");
				colT_pass.setPrefWidth(80);
				colT_pass.setStyle("-fx-alignment:CENTER");
				colT_pass.setCellValueFactory(new PropertyValueFactory<>("r_trypass"));
				
				TableColumn colK_pass = new TableColumn("키패스횟수");
				colK_pass.setPrefWidth(80);
				colK_pass.setStyle("-fx-alignment:CENTER");
				colK_pass.setCellValueFactory(new PropertyValueFactory<>("r_keypass"));
				
				TableColumn coS_pass = new TableColumn("패스성공횟수");
				coS_pass.setPrefWidth(80);
				coS_pass.setStyle("-fx-alignment:CENTER");
				coS_pass.setCellValueFactory(new PropertyValueFactory<>("r_successpass"));
				
				TableColumn colS_passrate = new TableColumn("패스성공률");
				colS_passrate.setPrefWidth(80);
				colS_passrate.setStyle("-fx-alignment:CENTER");
				colS_passrate.setCellValueFactory(new PropertyValueFactory<>("r_sucpassrate"));
				
				TableColumn colR_assists = new TableColumn("도움횟수");
				colR_assists.setPrefWidth(80);
				colR_assists.setStyle("-fx-alignment:CENTER");
				colR_assists.setCellValueFactory(new PropertyValueFactory<>("r_assists"));
				
				TableColumn colT_dribble = new TableColumn("드리블시도횟수");
				colT_dribble.setPrefWidth(80);
				colT_dribble.setStyle("-fx-alignment:CENTER");
				colT_dribble.setCellValueFactory(new PropertyValueFactory<>("r_trydribble"));
				
				TableColumn colS_dribble = new TableColumn("드리블성공횟수");
				colS_dribble.setPrefWidth(80);
				colS_dribble.setStyle("-fx-alignment:CENTER");
				colS_dribble.setCellValueFactory(new PropertyValueFactory<>("r_sucdribble"));
				
				TableColumn colS_dribblerate = new TableColumn("드리블성공률");
				colS_dribblerate.setPrefWidth(80);
				colS_dribblerate.setStyle("-fx-alignment:CENTER");
				colS_dribblerate.setCellValueFactory(new PropertyValueFactory<>("r_sucdribblerate"));
				
				TableColumn colr_tryshots = new TableColumn("슈팅시도횟수");
				colr_tryshots.setPrefWidth(80);
				colr_tryshots.setStyle("-fx-alignment:CENTER");
				colr_tryshots.setCellValueFactory(new PropertyValueFactory<>("r_tryshots"));
				
				TableColumn colr_shotontarget = new TableColumn("유효슈팅횟수");
				colr_shotontarget.setPrefWidth(80);
				colr_shotontarget.setStyle("-fx-alignment:CENTER");
				colr_shotontarget.setCellValueFactory(new PropertyValueFactory<>("r_shotontarget"));
				
				TableColumn colr_scores = new TableColumn("득점 수");
				colr_scores.setPrefWidth(80);
				colr_scores.setStyle("-fx-alignment:CENTER");
				colr_scores.setCellValueFactory(new PropertyValueFactory<>("r_scores"));
				
				TableColumn colr_arieldual = new TableColumn("공중볼경합횟수");
				colr_arieldual.setPrefWidth(80);
				colr_arieldual.setStyle("-fx-alignment:CENTER");
				colr_arieldual.setCellValueFactory(new PropertyValueFactory<>("r_arieldual"));
				
				TableColumn colr_ariwons = new TableColumn("공중볼경합승리횟수");
				colr_ariwons.setPrefWidth(80);
				colr_ariwons.setStyle("-fx-alignment:CENTER");
				colr_ariwons.setCellValueFactory(new PropertyValueFactory<>("r_ariwons"));
				
				TableColumn colr_trycut = new TableColumn("패스커팅시도");
				colr_trycut.setPrefWidth(80);
				colr_trycut.setStyle("-fx-alignment:CENTER");
				colr_trycut.setCellValueFactory(new PropertyValueFactory<>("r_trycut"));
				
				TableColumn colr_succut = new TableColumn("패스커팅성공");
				colr_succut.setPrefWidth(80);
				colr_succut.setStyle("-fx-alignment:CENTER");
				colr_succut.setCellValueFactory(new PropertyValueFactory<>("r_succut"));
				
				TableColumn colr_trytackle = new TableColumn("태클시도");
				colr_trytackle.setPrefWidth(80);
				colr_trytackle.setStyle("-fx-alignment:CENTER");
				colr_trytackle.setCellValueFactory(new PropertyValueFactory<>("r_trytackle"));
				
				TableColumn colr_suctackle = new TableColumn("태클성공");
				colr_suctackle.setPrefWidth(80);
				colr_suctackle.setStyle("-fx-alignment:CENTER");
				colr_suctackle.setCellValueFactory(new PropertyValueFactory<>("r_suctackle"));
				
				TableColumn colr_suctacklerate = new TableColumn("태클성공률");
				colr_suctacklerate.setPrefWidth(80);
				colr_suctacklerate.setStyle("-fx-alignment:CENTER");
				colr_suctacklerate.setCellValueFactory(new PropertyValueFactory<>("r_suctacklerate"));
				
				TableColumn colr_save = new TableColumn("선방횟수");
				colr_save.setPrefWidth(80);
				colr_save.setStyle("-fx-alignment:CENTER");
				colr_save.setCellValueFactory(new PropertyValueFactory<>("r_save"));
				
				TableColumn colr_gainsgoal = new TableColumn("실점 수");
				colr_gainsgoal.setPrefWidth(80);
				colr_gainsgoal.setStyle("-fx-alignment:CENTER");
				colr_gainsgoal.setCellValueFactory(new PropertyValueFactory<>("r_gainsgoal"));
				
				TableColumn colr_gamedate = new TableColumn("경기일자");
				colr_gamedate.setPrefWidth(80);
				colr_gamedate.setStyle("-fx-alignment:CENTER");
				colr_gamedate.setCellValueFactory(new PropertyValueFactory<>("r_gamedate"));
				
				TableColumn colr_haa = new TableColumn("홈 / 원정");
				colr_haa.setPrefWidth(80);
				colr_haa.setStyle("-fx-alignment:CENTER");
				colr_haa.setCellValueFactory(new PropertyValueFactory<>("r_haa"));
				
				tableView.getColumns().addAll(colG_no, colP_name, colF_name, colA_time, colA_rate, colB_touch, 
						colT_pass, colK_pass, coS_pass, colS_passrate, colR_assists, colT_dribble,
						colS_dribble, colS_dribblerate, colr_tryshots, colr_shotontarget, colr_scores, colr_arieldual,
						colr_ariwons, colr_trycut, colr_succut, colr_trytackle, colr_suctackle,
						colr_suctacklerate, colr_save, colr_gainsgoal, colr_gamedate, colr_haa);
				tableView.setItems(recordData);
				
				recordTotalList(player.getP_name());
				
				txtP_name.setText(player.getP_name());
				txtP_name.setDisable(true);
				txtPSuccessRate.setEditable(false);
				txtDSuccessRate.setEditable(false);
				txtTSuccessRate.setEditable(false);
				
				dpGameDate.setValue(LocalDate.now());
				dpGameDate.setEditable(false);
				
				cbHandAway.setItems(FXCollections.observableArrayList("홈", "원정"));
				
				btnRegist.setDisable(true);
				
				btnCalc.setOnAction(e -> {
					
					try {
					
					txtPSuccessRate.setText(Double.parseDouble(txtSuccessPass.getText()) / Double.parseDouble(txtTryPass.getText()) * 100 + "");
					txtDSuccessRate.setText(Double.parseDouble(txtSuccessDribble.getText()) / Double.parseDouble(txtTryDribble.getText()) * 100 + "");
					txtTSuccessRate.setText(Double.parseDouble(txtSuccessTackle.getText()) / Double.parseDouble(txtTryTackle.getText()) * 100 + "");
					
					btnRegist.setDisable(false);
					
					} catch (Exception e1) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("선수의 경기기록을 모두 입력하신 후 성공률계산버튼을 클릭해주세요!!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						btnRegist.setDisable(true);
						
					}
					
				});
				
				btnRegist.setOnAction(e -> {
					
					if (txtV_Club.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("상대구단명을 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtV_Club.requestFocus();
						
					} else if (txtAppsTime.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("출장시간을 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtAppsTime.requestFocus();
						
					} else if (txtAppsRate.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("경기평점을 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtAppsRate.requestFocus();
						
					} else if (txtBallTouch.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("볼터치횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtBallTouch.requestFocus();
						
					} else if (txtTryPass.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("패스시도횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtTryPass.requestFocus();
						
					} else if (txtSuccessPass.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("패스성공횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtSuccessPass.requestFocus();
						
					} else if (txtPSuccessRate.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("패스성공률을 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtPSuccessRate.requestFocus();
						
					} else if (txtKeyPass.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("키패스횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtKeyPass.requestFocus();
						
					} else if (txtTryDribble.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("드리블시도횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtTryDribble.requestFocus();
						
					} else if (txtSuccessDribble.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("드리블성공횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtSuccessDribble.requestFocus();
						
					} else if (txtDSuccessRate.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("드리블성공률을 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtDSuccessRate.requestFocus();
						
					} else if (txtTryShots.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("슈팅시도횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtTryShots.requestFocus();
						
					} else if (txtShotOnTarget.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("유효슈팅횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtShotOnTarget.requestFocus();
						
					} else if (txtGoal.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("득점 수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtGoal.requestFocus();
						
					} else if (txtAssists.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("도움 수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtAssists.requestFocus();
						
					} else if (txtTryCutPass.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("패스차단시도횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtTryCutPass.requestFocus();
						
					} else if (txtSuccessCutPass.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("패스차단성공횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtSuccessCutPass.requestFocus();
						
					} else if (txtTryTackle.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("태클시도횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtTryTackle.requestFocus();
						
					} else if (txtSuccessTackle.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("태클성공횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtSuccessTackle.requestFocus();
						
					} else if (txtTSuccessRate.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("태클성공률을 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtTSuccessRate.requestFocus();
						
					} else if (txtTryArielDual.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("공중볼경합횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtTryArielDual.requestFocus();
						
					} else if (txtSuccessArielDual.getText().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("공중볼경합승리횟수를 입력해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						txtSuccessArielDual.requestFocus();
						
					} else if (cbHandAway.getSelectionModel().isEmpty()) {
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("선수 경기기록 등록");
						alert.setHeaderText("홈 / 원정 여부를 선택해주세요!!");
						alert.setContentText("선수 경기기록 등록 실패!!");
						alert.showAndWait();
						
						cbHandAway.requestFocus();
						
					} else {
						
						recordData.removeAll(recordData);
						PlayerRecordVO pVo = new PlayerRecordVO();
						pVo = new PlayerRecordVO(txtP_name.getText(), txtV_Club.getText(), txtAppsTime.getText(),
								Double.parseDouble(txtAppsRate.getText()), Integer.parseInt(txtBallTouch.getText()),
								Integer.parseInt(txtTryPass.getText()), Integer.parseInt(txtKeyPass.getText()),
								Integer.parseInt(txtSuccessPass.getText()), Double.parseDouble(txtPSuccessRate.getText()),
								Integer.parseInt(txtAssists.getText()), Integer.parseInt(txtTryDribble.getText()),
								Integer.parseInt(txtSuccessDribble.getText()), Double.parseDouble(txtDSuccessRate.getText()),
								Integer.parseInt(txtTryShots.getText()), Integer.parseInt(txtShotOnTarget.getText()),
								Integer.parseInt(txtGoal.getText()), Integer.parseInt(txtTryArielDual.getText()),
								Integer.parseInt(txtSuccessArielDual.getText()), Integer.parseInt(txtTryCutPass.getText()),
								Integer.parseInt(txtSuccessCutPass.getText()), Integer.parseInt(txtTryTackle.getText()),
								Integer.parseInt(txtSuccessTackle.getText()), Double.parseDouble(txtTSuccessRate.getText()),
								Integer.parseInt(txtTrySave.getText()), Integer.parseInt(txtGainsGoal.getText()),
								dpGameDate.getValue(), cbHandAway.getSelectionModel().getSelectedItem());
						
						PlayerRecordDAO pDao = new PlayerRecordDAO();
						PlayerInfoDAO pdao = new PlayerInfoDAO();
						int i = pDao.insertPlayerRecord(pVo);
						pdao.updatePlayerRecord(pVo);
						if (i == 1) {
							
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("선수 경기기록 등록");
							alert.setHeaderText(txtP_name.getText() + " 선수의 경기기록이 등록되었습니다!");
							alert.setContentText("경기기록 등록 성공!!");
							alert.showAndWait();
							
						} else {
							
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("선수 경기기록 등록");
							alert.setHeaderText(txtP_name.getText() + " 선수의 경기기록 등록에 실패했습니다!");
							alert.setContentText("경기기록 등록 실패!!");
							alert.showAndWait();
							
						}
						
					}
					
					recordTotalList(txtP_name.getText());
					data.removeAll(data);
					totalList();
					
				});
				
				btnLogo.setOnAction(e -> {
					
					txtP_name.setEditable(false);
					txtP_name.setDisable(true);
					txtV_Club.clear();
					txtV_Club.setEditable(true);
					txtV_Club.setDisable(false);
					txtAppsTime.clear();
					txtAppsTime.setEditable(true);
					txtAppsTime.setDisable(false);
					txtAppsRate.clear();
					txtAppsRate.setEditable(false);
					txtAppsRate.setDisable(false);
					txtBallTouch.clear();
					txtBallTouch.setEditable(true);
					txtBallTouch.setDisable(false);
					txtTryPass.clear();
					txtTryPass.setEditable(true);
					txtTryPass.setDisable(false);
					txtKeyPass.clear();
					txtKeyPass.setEditable(true);
					txtKeyPass.setDisable(false);
					txtSuccessPass.clear();
					txtSuccessPass.setEditable(true);
					txtSuccessPass.setDisable(false);
					txtPSuccessRate.clear();
					txtPSuccessRate.setEditable(false);
					txtAssists.clear();
					txtAssists.setEditable(true);
					txtAssists.setDisable(false);
					txtTryDribble.clear();
					txtTryDribble.setEditable(true);
					txtTryDribble.setDisable(false);
					txtSuccessDribble.clear();
					txtSuccessDribble.setEditable(true);
					txtSuccessDribble.setDisable(false);
					txtDSuccessRate.clear();
					txtDSuccessRate.setEditable(false);
					txtDSuccessRate.setDisable(false);
					txtTryShots.clear();
					txtTryShots.setEditable(true);
					txtTryShots.setDisable(false);
					txtShotOnTarget.clear();
					txtShotOnTarget.setEditable(true);
					txtShotOnTarget.setDisable(false);
					txtGoal.clear();
					txtGoal.setEditable(true);
					txtGoal.setDisable(false);
					txtTryArielDual.clear();
					txtTryArielDual.setEditable(true);
					txtTryArielDual.setDisable(false);
					txtSuccessArielDual.clear();
					txtSuccessArielDual.setEditable(true);
					txtSuccessArielDual.setDisable(false);
					txtTryCutPass.clear();
					txtTryCutPass.setEditable(true);
					txtTryCutPass.setDisable(false);
					txtSuccessCutPass.clear();
					txtSuccessCutPass.setEditable(true);
					txtSuccessCutPass.setDisable(false);
					txtTryTackle.clear();
					txtTryTackle.setEditable(true);
					txtTryTackle.setDisable(false);
					txtSuccessTackle.clear();
					txtSuccessTackle.setEditable(true);
					txtSuccessTackle.setDisable(false);
					txtTSuccessRate.clear();
					txtTSuccessRate.setEditable(false);
					txtTSuccessRate.setDisable(false);
					txtTrySave.clear();
					txtTrySave.setEditable(true);
					txtTrySave.setDisable(false);
					txtGainsGoal.clear();
					txtGainsGoal.setEditable(true);
					txtGainsGoal.setDisable(false);
					dpGameDate.setValue(LocalDate.now());
					cbHandAway.getSelectionModel().clearSelection();
					btnRegist.setDisable(true);
					
				});
				
				btnCancel.setOnAction(e -> {
					
					dialog.close();
					
				});
				
				Scene scene = new Scene(parentRecentlyRecord);
				dialog.setScene(scene);
				dialog.setResizable(false);
				dialog.show();
				
			} catch (Exception e) {
				
				System.err.println(e);
				
			}
			
		} 
		
	}
	
	public void recordTotalList(String p_name) {
		
		Object[][] totalData;
		PlayerRecordDAO pDao = new PlayerRecordDAO();
		PlayerRecordVO pVo = null;
		ArrayList<String> title;
		ArrayList<PlayerRecordVO> list;

		title = pDao.getColumnName();
		int columnCount = title.size();

		list = pDao.getRecordTotal(p_name);
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		recordData.removeAll(recordData);
		
		for (int index = 0; index < rowCount; index++) {

			pVo = list.get(index);
			recordData.add(pVo);

		}
		
	}
	
	public void handlerBtnRegistAction(ActionEvent event) {
		
		int j = 0;
		
		if(txtName.getText().equals("") || txtName.getText().length() < 4 || txtName.getText().length() > 30) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 이름은 4 ~ 30자 이내로 입력해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			txtName.clear();
			txtName.requestFocus();
			
		} else if (cbGender.getSelectionModel().isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 성별을 선택해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			cbGender.requestFocus();
			
		} else if (cbDivision.getSelectionModel().isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 구분을 선택해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			cbDivision.requestFocus();
			
		} else if (dpBirth.getValue() == LocalDate.now()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 생년월일을 정확히 선택해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			dpBirth.setValue(LocalDate.now());
			dpBirth.requestFocus();
			
		} else if (txtHeight.getText().equals("") || Double.parseDouble(txtHeight.getText()) < 0 || Double.parseDouble(txtHeight.getText()) > 250) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 신장을 정확히 입력해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			txtHeight.clear();
			txtHeight.requestFocus();
			
		} else if (txtWeight.getText().equals("") || Double.parseDouble(txtWeight.getText()) < 0 || Double.parseDouble(txtWeight.getText()) > 250) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 체중을 정확히 입력해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			txtWeight.clear();
			txtWeight.requestFocus();
			
		} else if (txtPhone.getText().equals("") || txtPhone.getText().length() > 13) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 연락처를 정확히 입력해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			txtPhone.clear();
			txtPhone.requestFocus();
			
		} else if (txtEmail.getText().equals("") || txtEmail.getText().length() > 40) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 이메일을 정확히 입력해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			txtEmail.clear();
			txtEmail.requestFocus();
			
		} else if (txtNation.getText().equals("") || txtNation.getText().length() > 40) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 국적을 정확히 입력해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			txtNation.clear();
			txtNation.requestFocus();
			
		} else if (cbVisa.getSelectionModel().isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 비자발급가능여부를 선택해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			cbVisa.requestFocus();
			
		} else if (cbPosition.getSelectionModel().isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 포지션을 선택해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			cbPosition.requestFocus();
			
		} else if (cbPfoot.getSelectionModel().isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 주발을 선택해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			cbPfoot.requestFocus();
			
		} else if (cbAttached.getSelectionModel().isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 현소속구단명을 정확히 선택해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			cbAttached.getSelectionModel().clearSelection();
			cbAttached.requestFocus();
			
		} else if (txtTotApps.getText().equals("") || txtTotApps.getText().length() > i.length() || Integer.parseInt(txtTotApps.getText()) <= 0) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 통산출장횟수를 정확히 입력해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			txtTotApps.clear();
			txtTotApps.requestFocus();
			
		} else if (txtTotScores.getText().equals("") || txtTotScores.getText().length() > i.length() || Integer.parseInt(txtTotScores.getText()) < 0) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 통산득점수를 정확히 입력해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			txtTotScores.clear();
			txtTotScores.requestFocus();
			
		} else if (txtTotAssists.getText().equals("") || txtTotAssists.getText().length() > i.length() || Integer.parseInt(txtTotAssists.getText()) < 0) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 통산도움수를 정확히 입력해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			txtTotAssists.clear();
			txtTotAssists.requestFocus();
			
		} else if (txtAvgRate.getText().equals("") || txtAvgRate.getText().length() > i.length() || Double.parseDouble(txtAvgRate.getText()) < 0) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 평균평점을 정확히 입력해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			txtAvgRate.clear();
			txtAvgRate.requestFocus();
			
		} else if (txtAddress.getText().equals("") || txtAddress.getText().length() > 60 || txtAddress.getText().length() < 10) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 거주지를 정확히 입력해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			txtAddress.clear();
			txtAddress.requestFocus();
			
		} else if (txtPath.getText().equals("")) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("선수 정보 입력");
			alert.setHeaderText("선수의 이미지를 등록해주세요!");
			alert.setContentText("선수 정보 등록 실패!");
			alert.showAndWait();
			
			btnImgPath.requestFocus();
			
		} else {
			
			pVo = new PlayerInfoVO(cbDivision.getSelectionModel().getSelectedItem(), cbAttached.getSelectionModel().getSelectedItem(),
					txtName.getText(), dpBirth.getValue(), Double.parseDouble(txtHeight.getText()), Double.parseDouble(txtWeight.getText()),
					cbPosition.getSelectionModel().getSelectedItem(), txtNation.getText(), cbVisa.getSelectionModel().getSelectedItem(),
					txtPhone.getText(), txtAddress.getText(), txtEmail.getText(), Integer.parseInt(txtTotScores.getText()), Integer.parseInt(txtTotAssists.getText()),
					Integer.parseInt(txtTotApps.getText()), Double.parseDouble(txtAvgRate.getText()), cbPfoot.getSelectionModel().getSelectedItem(),
					txtHtA.getText(), cbGender.getSelectionModel().getSelectedItem(), txtPath.getText());
			
			j = pDao.insertPlayerInfo(pVo);
			
			if (j == 1) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("선수 정보 등록");
				alert.setHeaderText("선수 정보 등록 성공!");
				alert.setContentText("");
				alert.showAndWait();
				
				cbDivision.getSelectionModel().clearSelection();
				txtName.clear();
				dpBirth.setValue(LocalDate.now());
				txtHeight.clear();
				txtWeight.clear();
				txtPhone.clear();
				txtAddress.clear();
				txtTotScores.clear();
				cbPosition.getSelectionModel().clearSelection();
				txtNation.clear();
				cbVisa.getSelectionModel().clearSelection();
				txtTotAssists.clear();
				txtTotApps.clear();
				txtAvgRate.clear();
				cbPfoot.getSelectionModel().clearSelection();
				txtHtA.clear();
				cbGender.getSelectionModel().clearSelection();
				txtPath.clear();
				
				Image noImg = new Image("/view/NoImg.png");
				imgPlayer.setImage(noImg);
				
				txtName.requestFocus();
				
			} else {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("선수 정보 등록");
				alert.setHeaderText("선수 정보 등록 실패!");
				alert.setContentText("");
				alert.showAndWait();
				
			}
			
		}
		
		handlerBtnLogoAction(event);
		
	}
	
}
