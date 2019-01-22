package control;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.ClubInfoVO;

public class ClubInfoController implements Initializable{

	@FXML
	private Button btnLogo;
	@FXML
	private ImageView imgLogo;
	@FXML
	private TextField txtf_name;
	@FXML
	private TextField txtf_ownername;
	@FXML
	private TextField txtf_directorname;
	@FXML
	private TextField txtf_coachname;
	@FXML
	private TextField txtf_wagebudget;
	@FXML
	private TextField txtf_transferbudget;
	@FXML
	private TextField txtf_address;
	@FXML
	private TextField txtf_imgpath;
	@FXML
	private Button btnRegist;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnPath;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnExit;
	@FXML
	private TableView<ClubInfoVO> tableView;
	@FXML
	private ImageView imgClub;
	
	ObservableList<ClubInfoVO> data = FXCollections.observableArrayList();
	ClubInfoDAO cDao = new ClubInfoDAO();
	ClubInfoVO cVo = new ClubInfoVO();
	PlayerInfoDAO pDao = new PlayerInfoDAO();
	private Stage primaryStage;
	String selectFileName = "";
	String localUrl = "";
	Image localImage;
	File selectedFile = null;
	
	public static String div = null;
	public static String id = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		imgClub.setFitWidth(220);
		imgClub.setFitHeight(250);
		
		btnRegist.setDisable(true);
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		txtf_imgpath.setEditable(false);
		
		DecimalFormat df = new DecimalFormat("###");
		txtf_wagebudget.setTextFormatter(new TextFormatter<>(event -> {

			if (event.getControlNewText().isEmpty()) {

				return event;

			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = df.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 13) {

				return null;

			} else {

				return event;

			}

		}));
		
		txtf_transferbudget.setTextFormatter(new TextFormatter<>(event -> {

			if (event.getControlNewText().isEmpty()) {

				return event;

			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = df.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 13) {

				return null;

			} else {

				return event;

			}

		}));
		
		TableColumn colf_name = new TableColumn("구단명");
		colf_name.setPrefWidth(150);
		colf_name.setStyle("-fx-alignment:CENTER");
		colf_name.setCellValueFactory(new PropertyValueFactory<>("f_name"));
		
		TableColumn colf_oname = new TableColumn("구단주명");
		colf_oname.setPrefWidth(130);
		colf_oname.setStyle("-fx-alignment:CENTER");
		colf_oname.setCellValueFactory(new PropertyValueFactory<>("f_ownername"));
		
		TableColumn colf_dname = new TableColumn("디렉터명");
		colf_dname.setPrefWidth(110);
		colf_dname.setStyle("-fx-alignment:CENTER");
		colf_dname.setCellValueFactory(new PropertyValueFactory<>("f_directorname"));
		
		TableColumn colf_cname = new TableColumn("감독명");
		colf_cname.setPrefWidth(110);
		colf_cname.setStyle("-fx-alignment:CENTER");
		colf_cname.setCellValueFactory(new PropertyValueFactory<>("f_coachname"));
		
		TableColumn colf_wage = new TableColumn("급료예산");
		colf_wage.setPrefWidth(150);
		colf_wage.setStyle("-fx-alignment:CENTER");
		colf_wage.setCellValueFactory(new PropertyValueFactory<>("f_wagebudget"));
		
		TableColumn colf_transfer = new TableColumn("이적료예산");
		colf_transfer.setPrefWidth(150);
		colf_transfer.setStyle("-fx-alignment:CENTER");
		colf_transfer.setCellValueFactory(new PropertyValueFactory<>("f_transferbudget"));
		
		TableColumn colf_add = new TableColumn("구단소재지");
		colf_add.setPrefWidth(300);
		colf_add.setStyle("-fx-alignment:CENTER");
		colf_add.setCellValueFactory(new PropertyValueFactory<>("f_address"));
		
		TableColumn colf_img = new TableColumn("구단 이미지");
		colf_img.setPrefWidth(300);
		colf_img.setStyle("-fx-alignment:CENTER");
		colf_img.setCellValueFactory(new PropertyValueFactory<>("f_imgpath"));
		
		tableView.getColumns().addAll(colf_name, colf_oname, colf_dname, colf_cname, colf_wage, colf_transfer, colf_add, colf_img);
		
		tableView.setItems(data);
		totalList();
		
		btnLogo.setOnAction(event -> handlerBtnLogoAction(event));
		btnRegist.setOnAction(event -> handlerBtnRegistAction(event));
		btnEdit.setOnAction(event -> handlerBtnEditAction(event));
		btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));
		btnPath.setOnAction(event -> handlerBtnPathAction(event));
		btnSearch.setOnAction(event -> handlerBtnSearchAction(event));
		btnExit.setOnAction(event -> handlerBtnExitAction(event));
		
		tableView.setOnMouseClicked(event ->handlerTableViewMouseClicked(event));
		
	}

	public void handlerBtnLogoAction(ActionEvent event) {
		
		data.removeAll(data);
		txtf_name.clear();
		txtf_name.setEditable(true);
		txtf_name.setDisable(false);
		txtf_ownername.clear();
		txtf_ownername.setEditable(true);
		txtf_ownername.setDisable(false);
		txtf_directorname.clear();
		txtf_directorname.setEditable(true);
		txtf_directorname.setDisable(false);
		txtf_coachname.clear();
		txtf_coachname.setEditable(true);
		txtf_coachname.setDisable(false);
		txtf_wagebudget.clear();
		txtf_wagebudget.setEditable(true);
		txtf_wagebudget.setDisable(false);
		txtf_transferbudget.clear();
		txtf_transferbudget.setEditable(true);
		txtf_transferbudget.setDisable(false);
		txtf_address.clear();
		txtf_address.setEditable(true);
		txtf_address.setDisable(false);
		txtf_imgpath.clear();
		txtf_imgpath.setEditable(false);
		txtf_imgpath.setDisable(false);
		btnRegist.setDisable(true);
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		localUrl = "/view/NoImg.png";
		localImage = new Image(localUrl);
		imgClub.setImage(localImage);
		totalList();
		
	}
	
	public void handlerTableViewMouseClicked(MouseEvent event) {
		
		ActionEvent event1 = new ActionEvent();
		
		if (event.getClickCount() == 1 && tableView.getSelectionModel().isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단 정보 등록");
			alert.setHeaderText("등록된 구단 정보가 없습니다!\n먼저 구단정보를 등록하신 후 다시 시도해주세요!");
			alert.setContentText("등록된 구단 정보가 없습니다!");
			alert.showAndWait();
			
		} else if (event.getClickCount() == 1 && !tableView.getSelectionModel().isEmpty()) {
			
			txtf_name.setText(tableView.getSelectionModel().getSelectedItem().getF_name());
			txtf_name.setDisable(true);
			txtf_ownername.setText(tableView.getSelectionModel().getSelectedItem().getF_ownername());
			txtf_directorname.setText(tableView.getSelectionModel().getSelectedItem().getF_directorname());
			txtf_coachname.setText(tableView.getSelectionModel().getSelectedItem().getF_coachname());
			txtf_wagebudget.setText(tableView.getSelectionModel().getSelectedItem().getF_wagebudget() + "");
			txtf_transferbudget.setText(tableView.getSelectionModel().getSelectedItem().getF_transferbudget() + "");
			txtf_address.setText(tableView.getSelectionModel().getSelectedItem().getF_address());
			txtf_imgpath.setText(tableView.getSelectionModel().getSelectedItem().getF_imgpath());
			localUrl = tableView.getSelectionModel().getSelectedItem().getF_imgpath();
			localImage = new Image(localUrl);
			imgClub.setImage(localImage);
			
			btnRegist.setDisable(true);
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
		} else if (event.getClickCount() == 2) {
			
			
			
		} else {
			
			
			
		}
		
	}
	
	public void handlerBtnRegistAction(ActionEvent event) {
		
		int i = 0;
		
		if (txtf_name.getText().equals("") || txtf_name.getText().length() > 30 || txtf_name.getText().length() < 4) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단의 이름은 4 ~ 30자 이내로 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_name.requestFocus();
			
		} else if (txtf_ownername.getText().equals("") || txtf_ownername.getText().length() > 30 || txtf_ownername.getText().length() < 4) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단주의 이름은 4 ~ 30자 이내로 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_ownername.requestFocus();
			
		} else if (txtf_directorname.getText().equals("") || txtf_directorname.getText().length() > 30 || txtf_directorname.getText().length() < 4) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("디렉터의 이름은 4 ~ 30자 이내로 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_directorname.requestFocus();
			
		} else if (txtf_coachname.getText().equals("") || txtf_coachname.getText().length() > 30 || txtf_coachname.getText().length() < 4) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("감독의 이름은 4 ~ 30자 이내로 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_coachname.requestFocus();
			
		} else if (txtf_wagebudget.getText().equals("") || txtf_wagebudget.getText().length() > 12 || Long.parseLong(txtf_wagebudget.getText()) < 0) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단의 급료예산은 0이상의 정수, 12자리 이내로만 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_wagebudget.requestFocus();
			
		} else if (txtf_transferbudget.getText().equals("") || txtf_transferbudget.getText().length() > 12 || Long.parseLong(txtf_transferbudget.getText()) < 0) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단의 이적료예산은 0이상의 정수, 12자리 이내로만 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_transferbudget.requestFocus();
			
		} else if (txtf_address.getText().equals("") || txtf_address.getText().length() > 50 || txtf_address.getText().length() < 4) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단의 소재지 주소는 4 ~ 50자 이내로 입력해주세요!!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_address.requestFocus();
			
		} else if (txtf_imgpath.getText().equals("")) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단의 이미지를 등록해주세요!!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_imgpath.requestFocus();
			
		} else {
			
			cVo = new ClubInfoVO(txtf_name.getText(), txtf_ownername.getText(), txtf_directorname.getText(),
					txtf_coachname.getText(), Long.parseLong(txtf_wagebudget.getText()), Long.parseLong(txtf_transferbudget.getText()),
					txtf_address.getText(), txtf_imgpath.getText());
			
			i = cDao.insertClubInfo(cVo);
			
			if (i == 1) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("구단정보 등록");
				alert.setHeaderText(txtf_name.getText() + " 구단정보 등록 성공!");
				alert.setContentText("구단정보 등록 성공!");
				alert.showAndWait();
				
			} else {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("구단정보 등록");
				alert.setHeaderText(txtf_name.getText() + " 구단정보 등록 실패!");
				alert.setContentText("구단정보 등록 실패!");
				alert.showAndWait();
				
			}
		
			handlerBtnLogoAction(event);
			
		}
		
	}
	
	public void handlerBtnEditAction(ActionEvent event) {
		
		int i = 0;
		
		if (txtf_name.getText().equals("") || txtf_name.getText().length() > 30 || txtf_name.getText().length() < 4) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단의 이름은 4 ~ 30자 이내로 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_name.requestFocus();
			
		} else if (txtf_ownername.getText().equals("") || txtf_ownername.getText().length() > 30 || txtf_ownername.getText().length() < 4) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단주의 이름은 4 ~ 30자 이내로 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_ownername.requestFocus();
			
		} else if (txtf_directorname.getText().equals("") || txtf_directorname.getText().length() > 30 || txtf_directorname.getText().length() < 4) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("디렉터의 이름은 4 ~ 30자 이내로 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_directorname.requestFocus();
			
		} else if (txtf_coachname.getText().equals("") || txtf_coachname.getText().length() > 30 || txtf_coachname.getText().length() < 4) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("감독의 이름은 4 ~ 30자 이내로 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_coachname.requestFocus();
			
		} else if (txtf_wagebudget.getText().equals("") || txtf_wagebudget.getText().length() > 12 || Long.parseLong(txtf_wagebudget.getText()) < 0) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단의 급료예산은 0이상의 정수, 12자리 이내로만 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_wagebudget.requestFocus();
			
		} else if (txtf_transferbudget.getText().equals("") || txtf_transferbudget.getText().length() > 12 || Long.parseLong(txtf_transferbudget.getText()) < 0) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단의 이적료예산은 0이상의 정수, 12자리 이내로만 입력해주세요!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_transferbudget.requestFocus();
			
		} else if (txtf_address.getText().equals("") || txtf_address.getText().length() > 50 || txtf_address.getText().length() < 4) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단의 소재지 주소는 4 ~ 50자 이내로 입력해주세요!!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_address.requestFocus();
			
		} else if (txtf_imgpath.getText().equals("")) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 등록");
			alert.setHeaderText("구단의 이미지를 등록해주세요!!");
			alert.setContentText("구단정보 등록 실패!");
			alert.showAndWait();
			
			txtf_imgpath.requestFocus();
			
		} else {
			
			cVo = new ClubInfoVO(
					txtf_name.getText(), txtf_ownername.getText(), txtf_directorname.getText(),
					txtf_coachname.getText(), Long.parseLong(txtf_wagebudget.getText()), Long.parseLong(txtf_transferbudget.getText()),
					txtf_address.getText(), txtf_imgpath.getText());
			
			i = cDao.updateClubInfo(cVo);
			
			if (i == 1) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("구단정보 수정");
				alert.setHeaderText(txtf_name.getText() + " 구단정보 수정 성공!");
				alert.setContentText("구단정보 수정 성공!");
				alert.showAndWait();
				
				
			} else {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("구단정보 수정");
				alert.setHeaderText(txtf_name.getText() + " 구단정보 수정 실패!");
				alert.setContentText("구단정보 수정 실패!");
				alert.showAndWait();
				
			}
		
			handlerBtnLogoAction(event);
			
		}
		
	}
	
	public void handlerBtnDeleteAction(ActionEvent event) {
		
		int i = 0;
		
		i = cDao.deleteClubInfo(tableView.getSelectionModel().getSelectedItem().getF_name());
		
		if (i == 1) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 삭제");
			alert.setHeaderText(txtf_name.getText() + " 구단정보 삭제 성공!");
			alert.setHeaderText("구단정보 삭제 성공!");
			alert.showAndWait();
			
		} else {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("구단정보 삭제");
			alert.setHeaderText(txtf_name.getText() + " 구단정보 삭제 실패!");
			alert.setHeaderText("구단정보 삭제 실패!");
			alert.showAndWait();
			
		}
		
		handlerBtnLogoAction(event);
		
	}
	
	public void handlerBtnPathAction(ActionEvent event) {
		
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
		
		txtf_imgpath.setText(localUrl);
		localImage = new Image(localUrl, false);
		imgClub.setImage(localImage);
		btnRegist.setDisable(false);
		
		if (selectedFile != null) {
			
			selectFileName = selectedFile.getName();
			
		}
		
	}
	
	public void handlerBtnSearchAction(ActionEvent event) {
		
		ClubInfoVO cvo = new ClubInfoVO();
		ClubInfoDAO cdao = new ClubInfoDAO();

		Object[][] totalData = null;

		String searchName = "";
		String searchDate = "";
		boolean searchResult = false;

		try {

			searchName = txtf_name.getText();
			cvo = cdao.getSearchData(searchName);

			if (searchName.equals("")) {

				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("구단 정보 검색");
				alert.setHeaderText("구단의 이름을 입력해주세요!");
				alert.setContentText("다음에는 주의해주세요!");
				alert.showAndWait();

			} 
			
			if ((!searchName.equals("") && cvo != null)) {

				ArrayList<String> title;
				ArrayList<ClubInfoVO> list;

				title = cdao.getColumnName();
				int columnCount = title.size();

				list = cdao.getClubTotal();
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];

				if (cvo.getF_name().equals(searchName)) {

					txtf_name.clear();
					data.removeAll(data);
					for (int index = 0; index < rowCount; index++) {

						cvo = list.get(index);
						if (cvo.getF_name().equals(searchName)) {

							data.add(cvo);
							searchResult = true;

						}

					}

				}

			}
			
			if (!searchResult) {

				txtf_name.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("구단 정보 검색");
				alert.setHeaderText(searchName + " 구단이 리스트에 없습니다");
				alert.setContentText("다시 검색해주세요!");
				alert.showAndWait();

			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("구단 정보 검색");
			alert.setHeaderText("구단 정보 검색에 오류가 발생하였습니다!");
			alert.setContentText("다시 검색해주세요!");
			alert.showAndWait();

		}
		
	}
	
	public void handlerBtnExitAction(ActionEvent event) {
		
		try {
			Parent main = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
			Scene mainScene = new Scene(main);
			Stage mainStage = new Stage();
			mainStage.setScene(mainScene);
			mainStage.setTitle("메인메뉴");
			mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
			mainStage.setResizable(false);
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void totalList() {
		
		Object[][] totalData;
		ClubInfoDAO cDao = new ClubInfoDAO();
		ClubInfoVO cVo = null;
		ArrayList<String> title;
		ArrayList<ClubInfoVO> list;

		title = cDao.getColumnName();
		int columnCount = title.size();

		list = cDao.getClubTotal();
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {

			cVo = list.get(index);
			data.add(cVo);

		}
		
	}
	
}
