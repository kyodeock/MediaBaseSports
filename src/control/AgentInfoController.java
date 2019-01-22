package control;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.AgentInfoVO;

public class AgentInfoController implements Initializable{

	@FXML
	private Button btnLogo;
	@FXML
	private Button btnRegist;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnExit;
	@FXML
	private Button btnPath;
	@FXML
	private Button btnSearch;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtPhone;
	@FXML
	private DatePicker dpBirth;
	@FXML
	private ComboBox<String> cbGender;
	@FXML
	private TextField txtLicenceNo;
	@FXML
	private DatePicker dpHireDate;
	@FXML
	private DatePicker dpQualifyDate;
	@FXML
	private DatePicker dpExpireDate;
	@FXML
	private TextField txtUniv;
	@FXML
	private TextField txtLang;
	@FXML
	private TextField txtNationality;
	@FXML
	private TextField txtImgPath;
	@FXML
	private TextField txtAddress;
	@FXML
	private ImageView imgAgent;
	@FXML
	private TableView<AgentInfoVO> tableAgent;
	
	ObservableList<AgentInfoVO> data = FXCollections.observableArrayList();
	AgentInfoVO aVo = new AgentInfoVO();
	AgentInfoDAO aDao = new AgentInfoDAO();
	public static String div;
	public static String id;
	private Stage primaryStage;
	String selectFileName = "";
	String localUrl = "";
	Image localImage;
	File selectedFile = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		localUrl = "/view/NoImg.png";
		localImage = new Image(localUrl);
		
		btnRegist.autosize();
		btnEdit.autosize();
		btnDelete.autosize();
		btnSearch.autosize();
		btnPath.autosize();
		btnExit.autosize();
		
		imgAgent.setFitWidth(250.0);
		imgAgent.setFitHeight(200.0);
		
		dpBirth.setValue(LocalDate.now());
		dpBirth.setEditable(false);
		dpHireDate.setEditable(false);
		dpQualifyDate.setEditable(false);
		dpExpireDate.setEditable(false);
		dpHireDate.setValue(LocalDate.now());
		dpQualifyDate.setValue(LocalDate.now());
		dpExpireDate.setValue(LocalDate.now());
		
		btnRegist.setDisable(true);
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		txtImgPath.setEditable(false);
		
		cbGender.setItems(FXCollections.observableArrayList("����", "����"));
		
		TableColumn colrNo = new TableColumn<>("��ȣ");
		colrNo.setPrefWidth(50);
		colrNo.setStyle("-fx-alignment:CENTER");
		colrNo.setCellValueFactory(new PropertyValueFactory<>("a_rNo"));
		
		TableColumn colName = new TableColumn<>("����");
		colName.setPrefWidth(80);
		colName.setStyle("-fx-alignment:CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("a_name"));
		
		TableColumn colPhone = new TableColumn<>("����ó");
		colPhone.setPrefWidth(150);
		colPhone.setStyle("-fx-alignment:CENTER");
		colPhone.setCellValueFactory(new PropertyValueFactory<>("a_phone"));
		
		TableColumn colBirth = new TableColumn<>("�������");
		colBirth.setPrefWidth(100);
		colBirth.setStyle("-fx-alignment:CENTER");
		colBirth.setCellValueFactory(new PropertyValueFactory<>("a_birth"));
		
		TableColumn colGender = new TableColumn<>("����");
		colGender.setPrefWidth(80);
		colGender.setStyle("-fx-alignment:CENTER");
		colGender.setCellValueFactory(new PropertyValueFactory<>("a_gender"));
		
		TableColumn colQdate = new TableColumn<>("�ڰ������");
		colQdate.setPrefWidth(100);
		colQdate.setStyle("-fx-alignment:CENTER");
		colQdate.setCellValueFactory(new PropertyValueFactory<>("a_qualifyDate"));
		
		TableColumn colEdate = new TableColumn<>("�ڰݸ�����");
		colEdate.setPrefWidth(100);
		colEdate.setStyle("-fx-alignment:CENTER");
		colEdate.setCellValueFactory(new PropertyValueFactory<>("a_expireDate"));
		
		TableColumn colUniv = new TableColumn<>("��Ŵ��и�");
		colUniv.setPrefWidth(80);
		colUniv.setStyle("-fx-alignment:CENTER");
		colUniv.setCellValueFactory(new PropertyValueFactory<>("a_univ"));
		
		TableColumn colLang = new TableColumn<>("������");
		colLang.setPrefWidth(155);
		colLang.setStyle("-fx-alignment:CENTER");
		colLang.setCellValueFactory(new PropertyValueFactory<>("a_language"));
		
		TableColumn colAdd = new TableColumn<>("�ּ�");
		colAdd.setPrefWidth(170);
		colAdd.setStyle("-fx-alignment:CENTER");
		colAdd.setCellValueFactory(new PropertyValueFactory<>("a_address"));
		
		TableColumn colSal = new TableColumn<>("�޷�");
		colSal.setPrefWidth(80);
		colSal.setStyle("-fx-alignment:CENTER");
		colSal.setCellValueFactory(new PropertyValueFactory<>("a_sal"));
		
		TableColumn colHdate = new TableColumn<>("�Ի���");
		colHdate.setPrefWidth(80);
		colHdate.setStyle("-fx-alignment:CENTER");
		colHdate.setCellValueFactory(new PropertyValueFactory<>("a_hireDate"));
		
		TableColumn colNat = new TableColumn<>("����");
		colNat.setPrefWidth(80);
		colNat.setStyle("-fx-alignment:CENTER");
		colNat.setCellValueFactory(new PropertyValueFactory<>("a_nationality"));
		
		TableColumn colImg = new TableColumn<>("������Ʈ �̹���");
		colImg.setPrefWidth(175);
		colImg.setStyle("-fx-alignment:CENTER");
		colImg.setCellValueFactory(new PropertyValueFactory<>("a_imgPath"));
		
		tableAgent.setItems(data);
		tableAgent.getColumns().addAll(colrNo, colName, colPhone, colBirth,
				colGender, colQdate, colEdate, colUniv, colLang, colAdd,
				colSal, colHdate, colNat, colImg);
		
		totalList();
	
		btnRegist.setOnAction(event -> handlerBtnRegistAction(event));
		btnLogo.setOnAction(event -> handlerBtnLogoAction(event));
		btnEdit.setOnAction(event -> handlerBtnEditAction(event));
		btnPath.setOnAction(event -> handlerBtnPathAction(event));
		btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));
		btnExit.setOnAction(event -> handlerBtnExitAction(event));
		tableAgent.setOnMouseClicked(event -> handlerTableAgentMouseClicked(event));
		
	}

	public void handlerBtnDeleteAction(ActionEvent event) {
		
		int i = 0;
		
		i = aDao.deleteAgentInfo(tableAgent.getSelectionModel().getSelectedItem().getA_rNo());
		
		if (i == 1) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("������Ʈ ���� ����");
			alert.setHeaderText(txtName.getText() + " ������Ʈ�� ������ �����Ǿ����ϴ�!");
			alert.setContentText("���� ���� �Ϸ�!");
			alert.showAndWait();
			
		} else {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("������Ʈ ���� ����");
			alert.setHeaderText(txtName.getText() + " ������Ʈ�� ������ ������ �����߽��ϴ�!");
			alert.setContentText("���� ���� ����!");
			alert.showAndWait();
			
		}
		
		handlerBtnLogoAction(event);
		
	}
	
	public void handlerTableAgentMouseClicked(MouseEvent event) {
		
		if (event.getClickCount() == 1) {
			
			try {
			
				if (tableAgent.getSelectionModel() == null) {
				
				} else {
				
					txtName.setText(tableAgent.getSelectionModel().getSelectedItem().getA_name());
					txtName.setDisable(true);
					txtPhone.setText(tableAgent.getSelectionModel().getSelectedItem().getA_phone());
					dpBirth.setValue(tableAgent.getSelectionModel().getSelectedItem().getA_birth());
					dpBirth.setDisable(true);
					cbGender.getSelectionModel().select(tableAgent.getSelectionModel().getSelectedItem().getA_gender());
					cbGender.setDisable(true);
					txtLicenceNo.setText(tableAgent.getSelectionModel().getSelectedItem().getA_rNo());
					txtLicenceNo.setDisable(true);
					dpQualifyDate.setValue(tableAgent.getSelectionModel().getSelectedItem().getA_qualifyDate());
					dpQualifyDate.setDisable(true);
					dpExpireDate.setValue(tableAgent.getSelectionModel().getSelectedItem().getA_expireDate());
					txtUniv.setText(tableAgent.getSelectionModel().getSelectedItem().getA_univ());
					txtNationality.setText(tableAgent.getSelectionModel().getSelectedItem().getA_nationality());
					txtNationality.setDisable(true);
					dpHireDate.setValue(tableAgent.getSelectionModel().getSelectedItem().getA_hireDate());
					txtAddress.setText(tableAgent.getSelectionModel().getSelectedItem().getA_address());
					txtLang.setText(tableAgent.getSelectionModel().getSelectedItem().getA_language());
					txtImgPath.setText(tableAgent.getSelectionModel().getSelectedItem().getA_imgPath());
					Image img = new Image(tableAgent.getSelectionModel().getSelectedItem().getA_imgPath());
					imgAgent.setImage(img);
				
					btnEdit.setDisable(false);
					btnDelete.setDisable(false);
					btnSearch.setDisable(true);
				
				}
			
			} catch (Exception e) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("��ϵ� ������Ʈ ���� ����");
				alert.setHeaderText("��ϵ� ������Ʈ ������ �����ϴ�!\n���� ������Ʈ ������ ����Ͻ� �� �ٽ� �õ����ּ���!");
				alert.setContentText("������Ʈ ���� ����!!");
				alert.showAndWait();
				
				txtName.requestFocus();
				
			}
			
	} else {
		
		
		
	}
			
}
	
	public void handlerBtnEditAction(ActionEvent event) {
		
		int i = 0;
		
		aVo = new AgentInfoVO(tableAgent.getSelectionModel().getSelectedItem().getA_rNo(), txtName.getText(),
				txtPhone.getText(), dpBirth.getValue(), cbGender.getSelectionModel().getSelectedItem(),
				dpQualifyDate.getValue(), dpExpireDate.getValue(), txtUniv.getText(),
				txtAddress.getText(), txtLang.getText(), tableAgent.getSelectionModel().getSelectedItem().getA_sal(),
				dpHireDate.getValue(), txtNationality.getText(), txtImgPath.getText());
		
		i = aDao.updateAgentInfo(aVo);
		
		if (i == 1) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("������Ʈ ���� ����");
			alert.setHeaderText(aVo.getA_name() + " ������Ʈ�� ������ �����Ͽ����ϴ�!");
			alert.setContentText("���� ���� �Ϸ�!");
			alert.showAndWait();
			
		} else {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("������Ʈ ���� ����");
			alert.setHeaderText(aVo.getA_name() + " ������Ʈ�� ������ �������� ���߽��ϴ�!");
			alert.setContentText("���� ���� ����!");
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
		
		txtImgPath.setText(localUrl);
		localImage = new Image(localUrl, false);
		imgAgent.setImage(localImage);
		imgAgent.setFitHeight(200);
		imgAgent.setFitWidth(250);
		btnRegist.setDisable(false);
		
		if (selectedFile != null) {
			
			selectFileName = selectedFile.getName();
			
		}
		
	}
	
	public void handlerBtnExitAction(ActionEvent event) {
		
		try {
			Parent main = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
			Scene mainScene = new Scene(main);
			Stage mainStage = new Stage();
			mainStage.setScene(mainScene);
			mainStage.setTitle("���θ޴�");
			mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
			mainStage.setResizable(false);
			Stage oldStage = (Stage) btnExit.getScene().getWindow();
			oldStage.close();
			mainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void handlerBtnLogoAction(ActionEvent event) {
		
		data.removeAll(data);
		
		btnRegist.setDisable(true);
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		txtName.clear();
		txtName.setEditable(true);
		txtName.setDisable(false);
		txtPhone.clear();
		txtPhone.setEditable(true);
		txtPhone.setDisable(false);
		dpBirth.setDisable(false);
		dpBirth.setValue(LocalDate.now());
		cbGender.getSelectionModel().clearSelection();
		cbGender.setDisable(false);
		dpQualifyDate.setDisable(false);
		dpQualifyDate.setValue(LocalDate.now());
		dpExpireDate.setDisable(false);
		dpExpireDate.setDisable(false);
		txtUniv.clear();
		txtUniv.setEditable(true);
		txtUniv.setDisable(false);
		txtAddress.clear();
		txtAddress.setEditable(true);
		txtAddress.setDisable(false);
		txtLang.clear();
		txtLang.setEditable(true);
		txtLang.setDisable(false);
		dpHireDate.setValue(LocalDate.now());
		dpHireDate.setDisable(false);
		txtNationality.clear();
		txtNationality.setEditable(true);
		txtNationality.setDisable(false);
		txtImgPath.setDisable(false);
		txtImgPath.clear();
		
		imgAgent.setImage(localImage);
		
		totalList();
		
	}
	
	public void handlerBtnRegistAction(ActionEvent event) {
		
		int sal = 3000;
		int i = 0;
		String univ = "";
		String lang = "";
		
		AgentInfoVO avo = new AgentInfoVO();
		AgentInfoDAO adao = new AgentInfoDAO();
		
		try {
			
			if (txtName.getText().equals("") || txtName.getText().length() < 4 || txtName.getText().length() > 30) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������Ʈ ���� �Է�");
				alert.setHeaderText("������Ʈ�� �̸��� ��Ȯ�� �Է����ּ���\n(*4 ~ 30�� �̳�)");
				alert.setContentText("������Ʈ ���� �Է� ����!!");
				alert.showAndWait();
				
			} else if (txtPhone.getText().equals("")) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������Ʈ ���� �Է�");
				alert.setHeaderText("������Ʈ�� ����ó�� ��Ȯ�� �Է����ּ���\n(*�޴���ȭ��ȣ 13�� �ʼ��Է�)");
				alert.setContentText("������Ʈ ���� �Է� ����!!");
				alert.showAndWait();
				
			} else if (dpBirth.getValue() == LocalDate.now()) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������Ʈ ���� �Է�");
				alert.setHeaderText("������Ʈ�� ��������� ��Ȯ�� �Է����ּ���");
				alert.setContentText("������Ʈ ���� �Է� ����!!");
				alert.showAndWait();
				
			} else if (cbGender.getSelectionModel().getSelectedItem().equals("")) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������Ʈ ���� �Է�");
				alert.setHeaderText("������Ʈ�� ������ ��Ȯ�� �������ּ���");
				alert.setContentText("������Ʈ ���� �Է� ����!!");
				alert.showAndWait();
				
			} else if (dpQualifyDate.getValue().toString().equals("")) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������Ʈ ���� �Է�");
				alert.setHeaderText("������Ʈ�� �ڰ�������� ��Ȯ�� �Է����ּ���");
				alert.setContentText("������Ʈ ���� �Է� ����!!");
				alert.showAndWait();
				 
			} else if (dpExpireDate.getValue() == LocalDate.now()) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������Ʈ ���� �Է�");
				alert.setHeaderText("������Ʈ�� �ڰݸ������� ��Ȯ�� �Է����ּ���");
				alert.setContentText("������Ʈ ���� �Է� ����!!");
				alert.showAndWait();
				
			} else if (txtAddress.getText().equals("") || txtAddress.getText().length() < 4 || txtAddress.getText().length() > 50) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������Ʈ ���� �Է�");
				alert.setHeaderText("������Ʈ�� ������ �ּҸ� ��Ȯ�� �Է����ּ���\n(*50�� �̳� �ʼ��Է�)");
				alert.setContentText("������Ʈ ���� �Է� ����!!");
				alert.showAndWait();
				
			} else if (txtNationality.getText().equals("") || txtNationality.getText().length() > 50) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������Ʈ ���� �Է�");
				alert.setHeaderText("������Ʈ�� ������ ��Ȯ�� �Է����ּ���\n(*50�� �̳� �ʼ��Է�)");
				alert.setContentText("������Ʈ ���� �Է� ����!!");
				alert.showAndWait();
				
			} else if (txtImgPath.getText().equals("")){
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������Ʈ ���� �Է�");
				alert.setHeaderText("������Ʈ�� �̹��� ��θ� ��Ȯ�� �Է����ּ���\n(*50�� �̳� �ʼ��Է�)");
				alert.setContentText("������Ʈ ���� �Է� ����!!");
				alert.showAndWait();
				
			} else {
				
				if (txtUniv.getText().equals("")) {
					
					univ = "";
					
				} else {
					
					univ = txtUniv.getText();
					
				}
				
				if (txtLang.getText().equals("")) {
					
					lang = "";
					
				} else {
					
					lang = txtLang.getText();
					
				}
				
				avo = new AgentInfoVO(txtLicenceNo.getText(), txtName.getText(), txtPhone.getText(),
						dpBirth.getValue(), cbGender.getSelectionModel().getSelectedItem(),
						dpQualifyDate.getValue(), dpExpireDate.getValue(),
						univ, txtAddress.getText(), lang, sal,
						dpHireDate.getValue(), txtNationality.getText(), txtImgPath.getText());
				
				i = adao.insertAgentInfo(avo);
				
				if (i == 1) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("������Ʈ ���� ���");
					alert.setHeaderText(txtName.getText() + " ������Ʈ ���� ��� ����!");
					alert.setContentText("��� ����!");
					alert.showAndWait();
					
					
				} else {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("������Ʈ ���� ���");
					alert.setHeaderText(txtName.getText() + " ������Ʈ ���� ��� ����!");
					alert.setContentText("��� ����!");
					alert.showAndWait();
					
					
				}
				
			}

			handlerBtnLogoAction(event);
			
		} catch (Exception e) {
			
			System.err.println(e);
			
		}
		
	}
	
	public void totalList() {
		
		Object[][] totalData;
		AgentInfoDAO aDao = new AgentInfoDAO();
		AgentInfoVO aVo = null;
		ArrayList<String> title;
		ArrayList<AgentInfoVO> list;

		title = aDao.getColumnName();
		int columnCount = title.size();

		list = aDao.getAgentTotal();
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {

			aVo = list.get(index);
			data.add(aVo);

		}
		
	}
	
}
