package control;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ContractInfoVO;
import model.PlayerInfoVO;

public class ContractInfoController implements Initializable {

	@FXML
	private Button btnLogo;
	@FXML
	private TextField txtP_name;
	@FXML
	private TextField txtF_name;
	@FXML
	private ComboBox<String> cbConDivision;
	@FXML
	private ComboBox<String> cbConPeriod;
	@FXML
	private TextField txtWage;
	@FXML
	private ComboBox<String> cbWageIncrese;
	@FXML
	private ComboBox<String> cbWICondition;
	@FXML
	private ComboBox<String> cbWIRate;
	@FXML
	private ComboBox<String> cbScores;
	@FXML
	private TextField txtScores;
	@FXML
	private ComboBox<String> cbAssists;
	@FXML
	private TextField txtAssists;
	@FXML
	private ComboBox<String> cbApps;
	@FXML
	private TextField txtApps;
	@FXML
	private ComboBox<String> cbNotApps;
	@FXML
	private TextField txtNotApps;
	@FXML
	private ComboBox<String> cbCar;
	@FXML
	private TextField txtCarDivision;
	@FXML
	private ComboBox<String> cbHouse;
	@FXML
	private TextField txtHouse;
	@FXML
	private ComboBox<String> cbCPIncrese;
	@FXML
	private ComboBox<String> cbCPICondition;
	@FXML
	private ComboBox<String> cbCPIYear;
	@FXML
	private ComboBox<String> cbBuyOut;
	@FXML
	private ComboBox<String> cbBuyOutCondition;
	@FXML
	private TextField txtBuyOutPay;
	@FXML
	private ComboBox<String> cbRelease;
	@FXML
	private ComboBox<String> cbReleaseCondition;
	@FXML
	private TextField txtReleasePenalty;
	@FXML
	private DatePicker dpConDate;
	@FXML
	private DatePicker dpExpireDate;
	@FXML
	private ComboBox<String> cbContract;
	@FXML
	private Button btnContractEdit;
	@FXML
	private TextField txtContractFee;
	@FXML
	private TextField txtAgentFee;
	@FXML
	private DatePicker dpSdate;
	@FXML
	private DatePicker dpQdate;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnRegist;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private TableView<ContractInfoVO> tableView;
	@FXML
	private TextField txtContractTotal;
	@FXML
	private Button btnCalc;

	ContractInfoVO cVo = new ContractInfoVO();
	ContractInfoDAO cDao = new ContractInfoDAO();
	ObservableList<ContractInfoVO> data = FXCollections.observableArrayList();

	boolean regist = true;
	
	public static String div = null;
	public static String id = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		ArrayList<ContractInfoVO> list = new ArrayList<ContractInfoVO>();
		list = cDao.getContractTotal();

		btnRegist.setDisable(true);
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		btnContractEdit.setDisable(true);

		if (list.isEmpty()) {

			btnSearch.setDisable(true);

		} else {

			btnSearch.setDisable(false);

		}

		cbContract.setItems(FXCollections.observableArrayList("������", "����ü��", "������"));
		cbConDivision.setItems(FXCollections.observableArrayList("����", "���ҳ�"));
		cbConPeriod.setItems(FXCollections.observableArrayList("6����", "1��", "1�� 6����", "2��", "2�� 6����", "3��", "3�� 6����",
				"4��", "4�� 6����", "5��", "5�� 6����", "6��"));
		cbWageIncrese.setItems(FXCollections.observableArrayList("���׻���", "��������"));
		cbWICondition.setItems(
				FXCollections.observableArrayList("����", "10���� �̻� ��� ��", "20��� �̻� ���� ��(��ü����)", "20��� �̻� ���� ��(��ü������)"));
		cbWICondition.setDisable(true);
		cbWIRate.setItems(FXCollections.observableArrayList("����", "5%", "10%", "15%", "20%", "25%"));
		cbWIRate.setDisable(true);
		cbScores.setItems(FXCollections.observableArrayList("���׻���", "��������"));
		txtScores.setDisable(true);
		cbAssists.setItems(FXCollections.observableArrayList("���׻���", "��������"));
		txtAssists.setDisable(true);
		cbApps.setItems(FXCollections.observableArrayList("���׻���", "��������"));
		txtApps.setDisable(true);
		cbNotApps.setItems(FXCollections.observableArrayList("���׻���", "��������"));
		txtNotApps.setDisable(true);
		cbCar.setItems(FXCollections.observableArrayList("���׻���", "��������"));
		txtCarDivision.setDisable(true);
		cbHouse.setItems(FXCollections.observableArrayList("���׻���", "��������"));
		txtHouse.setDisable(true);
		cbCPIncrese.setItems(FXCollections.observableArrayList("���׻���", "��������"));
		cbCPICondition.setItems(FXCollections.observableArrayList("����", "5���� �̻� ��� ��", "5���� �̻� ��� ��",
				"20��� �̻� ���� ��(��ü����)", "20��� �̻� ���� ��(��ü������)"));
		cbCPICondition.setDisable(true);
		cbCPIYear.setItems(FXCollections.observableArrayList("����", "6����", "1��", "1�� 6����", "2��", "2�� 6����", "3��",
				"3�� 6����", "4��", "4�� 6����", "5��", "5�� 6����", "6��"));
		cbCPIYear.setDisable(true);
		cbBuyOut.setItems(FXCollections.observableArrayList("���׻���", "��������"));
		cbBuyOutCondition.setItems(
				FXCollections.observableArrayList("����", "�������� �������� ��", "è�Ǿ𽺸��� ���� ���� ��", "�����ĸ��� ���� ���� ��", "�� ���� ��"));
		cbBuyOutCondition.setDisable(true);
		txtBuyOutPay.setDisable(true);
		cbRelease.setItems(FXCollections.observableArrayList("���׻���", "��������"));
		cbReleaseCondition
				.setItems(FXCollections.observableArrayList("����", "�� ���� ��", "è�Ǿ𽺸��� ���� ���� ��", "�����ĸ��� ���� ���� ��"));
		cbReleaseCondition.setDisable(true);
		txtReleasePenalty.setDisable(true);
		dpConDate.setValue(LocalDate.now());
		dpConDate.setEditable(false);
		dpSdate.setValue(LocalDate.now());
		dpSdate.setEditable(false);
		dpQdate.setValue(LocalDate.now());
		dpQdate.setEditable(false);
		dpExpireDate.setEditable(false);
		dpExpireDate.setValue(LocalDate.now());
		txtContractTotal.setEditable(false);

		TableColumn colC_no = new TableColumn("�����ȣ");
		colC_no.setPrefWidth(145);
		colC_no.setStyle("-fx-alignment:CENTER");
		colC_no.setCellValueFactory(new PropertyValueFactory("c_no"));
		
		TableColumn colP_name = new TableColumn("������");
		colP_name.setPrefWidth(145);
		colP_name.setStyle("-fx-alignment:CENTER");
		colP_name.setCellValueFactory(new PropertyValueFactory("p_name"));
		
		TableColumn colF_name = new TableColumn("���ܸ�");
		colF_name.setPrefWidth(145);
		colF_name.setStyle("-fx-alignment:CENTER");
		colF_name.setCellValueFactory(new PropertyValueFactory("f_name"));
		
		TableColumn colC_division = new TableColumn("��౸��");
		colC_division.setPrefWidth(145);
		colC_division.setStyle("-fx-alignment:CENTER");
		colC_division.setCellValueFactory(new PropertyValueFactory("c_division"));

		TableColumn colC_period = new TableColumn("���Ⱓ");
		colC_period.setPrefWidth(145);
		colC_period.setStyle("-fx-alignment:CENTER");
		colC_period.setCellValueFactory(new PropertyValueFactory("c_period"));
		
		TableColumn colC_wage = new TableColumn("�ֱ�");
		colC_wage.setPrefWidth(145);
		colC_wage.setStyle("-fx-alignment:CENTER");
		colC_wage.setCellValueFactory(new PropertyValueFactory("c_wage"));
		
		TableColumn colC_loyalty = new TableColumn("����");
		colC_loyalty.setPrefWidth(145);
		colC_loyalty.setStyle("-fx-alignment:CENTER");
		colC_loyalty.setCellValueFactory(new PropertyValueFactory("c_loyalty"));
		
		TableColumn colC_AgentFee = new TableColumn("������");
		colC_AgentFee.setPrefWidth(145);
		colC_AgentFee.setStyle("-fx-alignment:CENTER");
		colC_AgentFee.setCellValueFactory(new PropertyValueFactory("c_agentfee"));
		
		TableColumn colC_ConTotal = new TableColumn("����Ѿ�");
		colC_ConTotal.setPrefWidth(145);
		colC_ConTotal.setStyle("-fx-alignment:CENTER");
		colC_ConTotal.setCellValueFactory(new PropertyValueFactory("c_contractTotal"));
		
		TableColumn colC_negocs = new TableColumn("������Ȳ");
		colC_negocs.setPrefWidth(145);
		colC_negocs.setStyle("-fx-alignment:CENTER");
		colC_negocs.setCellValueFactory(new PropertyValueFactory("c_negocs"));
		
		tableView.getColumns().addAll(colC_no, colP_name, colF_name,
				colC_division, colC_period, colC_wage, colC_loyalty,
				colC_AgentFee, colC_ConTotal, colC_negocs);

		tableView.setItems(data);
		
		totalList();
		
		btnLogo.setOnAction(event -> handlerBtnLogoAction(event));
		btnContractEdit.setOnAction(event -> handlerBtnContractEditAction(event));
		btnRegist.setOnAction(event -> handlerBtnRegistAction(event));
		btnEdit.setOnAction(event -> handlerBtnEditAction(event));
		btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));
		btnCancel.setOnAction(event -> handlerBtnCancelAction(event));
		cbConPeriod.setOnAction(event -> handlerCbConPeriodAction(event));
		cbConDivision.setOnAction(event -> handlerCbConDivisionAction(event));
		cbWageIncrese.setOnAction(event -> handlerCbWIAction(event));
		cbScores.setOnAction(event -> handlerCbScoresAction(event));
		cbAssists.setOnAction(event -> handlerCbAssistsAction(event));
		cbApps.setOnAction(event -> handlerCbAppsAction(event));
		cbNotApps.setOnAction(event -> handlerCbNotAppsAction(event));
		cbCar.setOnAction(event -> handlerCbCarAction(event));
		cbHouse.setOnAction(event -> handlerCbHouseAction(event));
		cbCPIncrese.setOnAction(event -> handlerCbCPIAction(event));
		cbBuyOut.setOnAction(event -> handlerCbBOAction(event));
		cbRelease.setOnAction(event -> handlerCbRelAction(event));
		btnCalc.setOnAction(event -> handlerBtnCalcAction(event));
		tableView.setOnMousePressed(event -> handlerTableViewMousePressed(event));
		btnSearch.setOnAction(event -> handlerBtnSearchAction(event));

	}

	public void handlerBtnSearchAction(ActionEvent event) {
		
		data.removeAll(data);
		data.addAll(cDao.getSearchData(dpSdate.getValue(), dpQdate.getValue()));
		
	}
	
	public void handlerTableViewMousePressed(MouseEvent event) {
		
		regist = false;
		
		if (tableView.getSelectionModel().isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("��ϵ� ���������� �����ϴ�!!\n���������� ����Ͻ� �� �ٽ� �õ����ּ���!");
			alert.setContentText("�������� ��ȸ ����!!");
			alert.showAndWait();
			
		} else {
			
			txtP_name.setText(tableView.getSelectionModel().getSelectedItem().getP_name());
			txtP_name.setDisable(true);
			txtF_name.setText(tableView.getSelectionModel().getSelectedItem().getF_name());
			txtF_name.setDisable(true);
			cbConDivision.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_division());
			cbConPeriod.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_period());
			txtWage.setText(tableView.getSelectionModel().getSelectedItem().getC_wage() + "");
			cbWageIncrese.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_wagecondition());
			cbWICondition.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_wicondition());
			cbWIRate.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_wirate());
			cbScores.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_scorecondition());
			txtScores.setText(tableView.getSelectionModel().getSelectedItem().getC_scoreprofit() + "");
			cbAssists.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_assistscondition());
			txtAssists.setText(tableView.getSelectionModel().getSelectedItem().getC_assistsprofit() + "");
			cbApps.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_appscondition());
			txtApps.setText(tableView.getSelectionModel().getSelectedItem().getC_appsprofit() + "");
			cbNotApps.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_notappscondition());
			txtNotApps.setText(tableView.getSelectionModel().getSelectedItem().getC_notappsprofit() + "");
			cbCar.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_carcondition());
			txtCarDivision.setText(tableView.getSelectionModel().getSelectedItem().getC_cardivision());
			cbHouse.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_housecondition());
			txtHouse.setText(tableView.getSelectionModel().getSelectedItem().getC_housefee() + "");
			cbCPIncrese.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_periodcondition());
			cbCPICondition.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_picondition());
			cbCPIYear.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_piyear());
			cbBuyOut.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_buyoutcondition());
			cbBuyOutCondition.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_bocondition());
			txtBuyOutPay.setText(tableView.getSelectionModel().getSelectedItem().getC_buyoutpay() + "");
			cbRelease.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_releasecondition());
			cbReleaseCondition.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_rcondition());
			txtReleasePenalty.setText(tableView.getSelectionModel().getSelectedItem().getC_releasepenalty() + "");
			dpConDate.setValue(tableView.getSelectionModel().getSelectedItem().getC_condate());
			dpExpireDate.setValue(tableView.getSelectionModel().getSelectedItem().getC_cexpiredate());
			cbContract.getSelectionModel().select(tableView.getSelectionModel().getSelectedItem().getC_negocs());
			txtContractFee.setText(tableView.getSelectionModel().getSelectedItem().getC_loyalty() + "");
			txtAgentFee.setText(tableView.getSelectionModel().getSelectedItem().getC_agentfee() + "");
			txtContractTotal.setText(tableView.getSelectionModel().getSelectedItem().getC_contractTotal() + "");
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
			
			if (tableView.getSelectionModel().getSelectedItem().getC_negocs().equals("������")) {
				
				btnContractEdit.setDisable(false);
				cbContract.setDisable(false);
				btnEdit.setDisable(false);
				btnDelete.setDisable(false);
				
			} else {
				
				btnContractEdit.setDisable(true);
				cbContract.setDisable(true);
				btnEdit.setDisable(true);
				btnDelete.setDisable(true);
				
			}
			
		}
		
	}
	
	public void handlerBtnCalcAction(ActionEvent event) {

		double contractTotal = 0;
		if (cbConPeriod.getSelectionModel().isEmpty() || txtWage.getText().equals("") || txtContractFee.getText().equals("")) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("����Ѿ� ���");
			alert.setHeaderText("����Ѿ� ��� ����");
			alert.setContentText("�ֱް� ������ ��� �Է��Ͻ� �� �ٽ� �õ����ּ���!");
			alert.showAndWait();
			
			txtWage.requestFocus();
			regist = false;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("6����")) {

			contractTotal = (Double.parseDouble(txtWage.getText()) * 2.6) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("1��")) {

			contractTotal = (Double.parseDouble(txtWage.getText()) * 5.2) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("1�� 6����")) {
			
			contractTotal = (Double.parseDouble(txtWage.getText()) * 7.8) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("2��")) {

			contractTotal = (Double.parseDouble(txtWage.getText()) * 10.4) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("2�� 6����")) {

			contractTotal = (Double.parseDouble(txtWage.getText()) * 13.0) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("3��")) {

			contractTotal = (Double.parseDouble(txtWage.getText()) * 15.6) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("3�� 6����")) {

			contractTotal = (Double.parseDouble(txtWage.getText()) * 18.2) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("4��")) {

			contractTotal = (Double.parseDouble(txtWage.getText()) * 20.8) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("4�� 6����")) {

			contractTotal = (Double.parseDouble(txtWage.getText()) * 23.4) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("5��")) {

			contractTotal = (Double.parseDouble(txtWage.getText()) * 26.0) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("5�� 6����")) {

			contractTotal = (Double.parseDouble(txtWage.getText()) * 28.6) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("6��")) {

			contractTotal = (Double.parseDouble(txtWage.getText()) * 31.2) + Double.parseDouble(txtContractFee.getText());
			txtContractTotal.setText(contractTotal + "");
			regist = true;
			
		}

		if (regist) {
			
			btnRegist.setDisable(false);
			
		} else {
			
			btnRegist.setDisable(true);
			
		}
		
	}

	public void handlerCbRelAction(ActionEvent event) {

		if (cbRelease.getSelectionModel().isEmpty()) {

		} else if (cbRelease.getSelectionModel().getSelectedItem().equals("���׻���")) {

			cbReleaseCondition.setDisable(false);
			txtReleasePenalty.setDisable(false);

		} else {

			cbReleaseCondition.setDisable(true);
			txtReleasePenalty.setDisable(true);
			cbReleaseCondition.getSelectionModel().select("����");
			txtReleasePenalty.setText("0");

		}

	}

	public void handlerCbBOAction(ActionEvent event) {

		if (cbBuyOut.getSelectionModel().isEmpty()) {

		} else if (cbBuyOut.getSelectionModel().getSelectedItem().equals("���׻���")) {

			cbBuyOutCondition.setDisable(false);
			txtBuyOutPay.setDisable(false);

		} else {

			cbBuyOutCondition.setDisable(true);
			txtBuyOutPay.setDisable(true);
			cbBuyOutCondition.getSelectionModel().select("����");
			txtBuyOutPay.setText("0");

		}

	}

	public void handlerCbCPIAction(ActionEvent event) {

		if (cbCPIncrese.getSelectionModel().isEmpty()) {

		} else if (cbCPIncrese.getSelectionModel().getSelectedItem().equals("���׻���")) {

			cbCPICondition.setDisable(false);
			cbCPIYear.setDisable(false);

		} else {

			cbCPICondition.setDisable(true);
			cbCPIYear.setDisable(true);
			cbCPICondition.getSelectionModel().select("����");
			cbCPIYear.getSelectionModel().select("����");

		}

	}

	public void handlerCbHouseAction(ActionEvent event) {

		if (cbHouse.getSelectionModel().isEmpty()) {

		} else if (cbHouse.getSelectionModel().getSelectedItem().equals("���׻���")) {

			txtHouse.setDisable(false);

		} else {

			txtHouse.setDisable(true);
			txtHouse.setText("0");

		}

	}

	public void handlerCbCarAction(ActionEvent event) {

		if (cbCar.getSelectionModel().isEmpty()) {

		} else if (cbCar.getSelectionModel().getSelectedItem().equals("���׻���")) {

			txtCarDivision.setDisable(false);

		} else {

			txtCarDivision.setDisable(true);
			txtCarDivision.setText("����");

		}

	}

	public void handlerCbNotAppsAction(ActionEvent event) {

		if (cbNotApps.getSelectionModel().isEmpty()) {

		} else if (cbNotApps.getSelectionModel().getSelectedItem().equals("���׻���")) {

			txtNotApps.setDisable(false);

		} else {

			txtNotApps.setDisable(true);
			txtNotApps.setText("0");

		}

	}

	public void handlerCbAppsAction(ActionEvent event) {

		if (cbApps.getSelectionModel().isEmpty()) {

		} else if (cbApps.getSelectionModel().getSelectedItem().equals("���׻���")) {

			txtApps.setDisable(false);

		} else {

			txtApps.setDisable(true);
			txtApps.setText("0");

		}

	}

	public void handlerCbAssistsAction(ActionEvent event) {

		if (cbAssists.getSelectionModel().isEmpty()) {

		} else if (cbAssists.getSelectionModel().getSelectedItem().equals("���׻���")) {

			txtAssists.setDisable(false);

		} else {

			txtAssists.setDisable(true);
			txtAssists.setText("0");

		}

	}

	public void handlerCbScoresAction(ActionEvent event) {

		if (cbScores.getSelectionModel().isEmpty()) {

		} else if (cbScores.getSelectionModel().getSelectedItem().equals("���׻���")) {

			txtScores.setDisable(false);

		} else {

			txtScores.setDisable(true);
			txtScores.setText("0");

		}

	}

	public void handlerCbWIAction(ActionEvent event) {

		if (cbWageIncrese.getSelectionModel().isEmpty()) {

		} else if (cbWageIncrese.getSelectionModel().getSelectedItem().equals("���׻���")) {

			cbWICondition.setDisable(false);
			cbWIRate.setDisable(false);

		} else {

			cbWICondition.setDisable(true);
			cbWIRate.setDisable(true);
			cbWICondition.getSelectionModel().select("����");
			cbWIRate.getSelectionModel().select("����");

		}

	}

	public void handlerCbConDivisionAction(ActionEvent event) {

		if (cbConDivision.getSelectionModel().isEmpty()) {

		} else if (cbConDivision.getSelectionModel().getSelectedItem().equals("����")) {

			cbConPeriod.setDisable(false);
			cbConPeriod.setItems(FXCollections.observableArrayList("6����", "1��", "1�� 6����", "2��", "2�� 6����", "3��", "3�� 6����",
					"4��", "4�� 6����", "5��", "5�� 6����", "6��"));

		} else {

			cbConPeriod.setDisable(false);
			cbConPeriod.setItems(FXCollections.observableArrayList("6����", "1��", "1�� 6����", "2��", "2�� 6����", "3��"));

		}

	}

	public void handlerCbConPeriodAction(ActionEvent event) {

		if (cbConPeriod.getSelectionModel().isEmpty()) {

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("6����")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(6));

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("1��")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(12));

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("1�� 6����")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(18));

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("2��")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(24));

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("2�� 6����")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(30));

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("3��")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(36));

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("3�� 6����")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(42));

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("4��")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(48));

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("4�� 6����")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(54));

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("5��")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(60));

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("5�� 6����")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(66));

		} else if (cbConPeriod.getSelectionModel().getSelectedItem().equals("6��")) {

			dpExpireDate.setValue(dpConDate.getValue().plusMonths(72));

		}

	}

	public void handlerBtnCancelAction(ActionEvent event) {

		try {
			Parent main = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
			Scene mainScene = new Scene(main);
			Stage mainStage = new Stage();
			mainStage.setScene(mainScene);
			mainStage.setTitle("���θ޴�");
			mainStage.getIcons().add(new Image("/view/backgroundImg.png"));
			mainStage.setResizable(false);
			Stage oldStage = (Stage) btnCancel.getScene().getWindow();
			oldStage.close();
			mainStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void handlerBtnDeleteAction(ActionEvent event) {

		int i = cDao.deleteContractInfo(tableView.getSelectionModel().getSelectedItem().getC_no());

		if (i == 1) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("�������� ���� ����!!");
			alert.setContentText("");
			alert.showAndWait();

		} else {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("�������� ���� ����!!");
			alert.setContentText("");
			alert.showAndWait();

		}
		
		handlerBtnLogoAction(event);
		
	}

	public void handlerBtnEditAction(ActionEvent event) {

		cVo = new ContractInfoVO(tableView.getSelectionModel().getSelectedItem().getC_no(),
				txtP_name.getText(), txtF_name.getText(),
				cbConDivision.getSelectionModel().getSelectedItem(),
				cbConPeriod.getSelectionModel().getSelectedItem(), Double.parseDouble(txtWage.getText()),
				cbWageIncrese.getSelectionModel().getSelectedItem(),
				cbWICondition.getSelectionModel().getSelectedItem(), cbWIRate.getSelectionModel().getSelectedItem(),
				cbScores.getSelectionModel().getSelectedItem(), Double.parseDouble(txtScores.getText()),
				cbAssists.getSelectionModel().getSelectedItem(), Double.parseDouble(txtAssists.getText()),
				cbApps.getSelectionModel().getSelectedItem(), Double.parseDouble(txtApps.getText()),
				cbNotApps.getSelectionModel().getSelectedItem(), Double.parseDouble(txtNotApps.getText()),
				cbCar.getSelectionModel().getSelectedItem(), txtCarDivision.getText(),
				cbHouse.getSelectionModel().getSelectedItem(), Double.parseDouble(txtHouse.getText()),
				cbCPIncrese.getSelectionModel().getSelectedItem(),
				cbCPICondition.getSelectionModel().getSelectedItem(),
				cbCPIYear.getSelectionModel().getSelectedItem(), cbBuyOut.getSelectionModel().getSelectedItem(),
				cbBuyOutCondition.getSelectionModel().getSelectedItem(), Double.parseDouble(txtBuyOutPay.getText()),
				cbRelease.getSelectionModel().getSelectedItem(),
				cbReleaseCondition.getSelectionModel().getSelectedItem(),
				Double.parseDouble(txtReleasePenalty.getText()), dpConDate.getValue(), dpExpireDate.getValue(),
				cbContract.getSelectionModel().getSelectedItem(), Double.parseDouble(txtContractFee.getText()),
				Double.parseDouble(txtAgentFee.getText()),
				Double.parseDouble(txtContractTotal.getText()));
		
		int i = cDao.updateContractInfo(cVo);

		if (i == 1) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("�������� ���� ����!!");
			alert.setContentText("");
			alert.showAndWait();

		} else {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("�������� ���� ����!!");
			alert.setContentText("");
			alert.showAndWait();

		}
		
	}

	public void handlerBtnRegistAction(ActionEvent event) {

		ContractInfoDAO cDao = new ContractInfoDAO();

		if (txtP_name.getText().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("������ ������ �Է����ּ���!");
			alert.setContentText("�������� ��� ����!!");
			alert.showAndWait();

			txtP_name.requestFocus();

		} else if (txtF_name.getText().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("���ܸ��� �Է����ּ���!");
			alert.setContentText("�������� ��� ����!!");
			alert.showAndWait();

			txtF_name.requestFocus();

		} else if (cbConDivision.getSelectionModel().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("��౸���� �������ּ���!");
			alert.setContentText("�������� ��� ����!!");
			alert.showAndWait();

			cbConDivision.requestFocus();

		} else if (cbConPeriod.getSelectionModel().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("���Ⱓ�� �������ּ���!");
			alert.setContentText("�������� ��� ����!!");
			alert.showAndWait();

			cbConPeriod.requestFocus();

		} else if (txtWage.getText().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("�ֱ��� �Է����ּ���!");
			alert.setContentText("�������� ��� ����!!");
			alert.showAndWait();

			txtWage.requestFocus();

		} else if (cbWageIncrese.getSelectionModel().isEmpty() || cbScores.getSelectionModel().isEmpty()
				|| cbAssists.getSelectionModel().isEmpty() || cbCar.getSelectionModel().isEmpty()
				|| cbHouse.getSelectionModel().isEmpty() || cbApps.getSelectionModel().isEmpty()
				|| cbNotApps.getSelectionModel().isEmpty() || cbCPIncrese.getSelectionModel().isEmpty()
				|| cbBuyOut.getSelectionModel().isEmpty() || cbRelease.getSelectionModel().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("�߰����׻��Կ��θ� �������ּ���!");
			alert.setContentText("�������� ��� ����!!");
			alert.showAndWait();

			cbWageIncrese.requestFocus();

		} else if (cbContract.getSelectionModel().isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�������� ���");
			alert.setHeaderText("���������Ȳ�� �������ּ���!");
			alert.setContentText("�������� ��� ����!!");
			alert.showAndWait();

			cbContract.requestFocus();

		} else {

			cVo = new ContractInfoVO(txtP_name.getText(), txtF_name.getText(),
					cbConDivision.getSelectionModel().getSelectedItem(),
					cbConPeriod.getSelectionModel().getSelectedItem(), Double.parseDouble(txtWage.getText()),
					cbWageIncrese.getSelectionModel().getSelectedItem(),
					cbWICondition.getSelectionModel().getSelectedItem(), cbWIRate.getSelectionModel().getSelectedItem(),
					cbScores.getSelectionModel().getSelectedItem(), Double.parseDouble(txtScores.getText()),
					cbAssists.getSelectionModel().getSelectedItem(), Double.parseDouble(txtAssists.getText()),
					cbApps.getSelectionModel().getSelectedItem(), Double.parseDouble(txtApps.getText()),
					cbNotApps.getSelectionModel().getSelectedItem(), Double.parseDouble(txtNotApps.getText()),
					cbCar.getSelectionModel().getSelectedItem(), txtCarDivision.getText(),
					cbHouse.getSelectionModel().getSelectedItem(), Double.parseDouble(txtHouse.getText()),
					cbCPIncrese.getSelectionModel().getSelectedItem(),
					cbCPICondition.getSelectionModel().getSelectedItem(),
					cbCPIYear.getSelectionModel().getSelectedItem(), cbBuyOut.getSelectionModel().getSelectedItem(),
					cbBuyOutCondition.getSelectionModel().getSelectedItem(), Double.parseDouble(txtBuyOutPay.getText()),
					cbRelease.getSelectionModel().getSelectedItem(),
					cbReleaseCondition.getSelectionModel().getSelectedItem(),
					Double.parseDouble(txtReleasePenalty.getText()), dpConDate.getValue(), dpExpireDate.getValue(),
					cbContract.getSelectionModel().getSelectedItem(), Double.parseDouble(txtContractFee.getText()),
					Double.parseDouble(txtAgentFee.getText()),
					Double.parseDouble(txtContractTotal.getText()));

			int i = cDao.insertContractInfo(cVo);

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�������� ���");
				alert.setHeaderText("�������� ��� ����!!");
				alert.setContentText("");
				alert.showAndWait();

			} else {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�������� ���");
				alert.setHeaderText("�������� ��� ����!!");
				alert.setContentText("");
				alert.showAndWait();

			}

		}

		handlerBtnLogoAction(event);
		
	}

	public void handlerBtnContractEditAction(ActionEvent event) {

		int i = cDao.updateNegoCs(tableView.getSelectionModel().getSelectedItem().getC_no(), cbContract.getSelectionModel().getSelectedItem());
		
		if (i == 1) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("���������Ȳ ����");
			alert.setHeaderText("������Ȳ ���� ����!");
			alert.setContentText("������Ȳ ���� ����!");
			alert.showAndWait();
			
		} else {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("���������Ȳ ����");
			alert.setHeaderText("������Ȳ ���� ����!");
			alert.setContentText("������Ȳ ���� ����!");
			alert.showAndWait();
			
		}
		
		handlerBtnLogoAction(event);
		
	}

	public void handlerBtnLogoAction(ActionEvent event) {

		data.removeAll(data);
		regist = true;
		txtP_name.clear();
		txtP_name.setDisable(false);
		txtF_name.clear();
		txtF_name.setDisable(false);
		cbConDivision.getSelectionModel().clearSelection();
		cbConPeriod.getSelectionModel().clearSelection();
		txtWage.clear();
		cbWageIncrese.getSelectionModel().clearSelection();
		cbWICondition.getSelectionModel().clearSelection();
		cbWICondition.setDisable(true);
		cbWIRate.getSelectionModel().clearSelection();
		cbWIRate.setDisable(true);
		cbScores.getSelectionModel().clearSelection();
		txtScores.clear();
		txtScores.setDisable(true);
		cbAssists.getSelectionModel().clearSelection();
		txtAssists.clear();
		txtAssists.setDisable(true);
		cbApps.getSelectionModel().clearSelection();
		txtApps.clear();
		txtApps.setDisable(true);
		cbNotApps.getSelectionModel().clearSelection();
		txtNotApps.clear();
		txtNotApps.setDisable(true);
		cbCar.getSelectionModel().clearSelection();
		txtCarDivision.clear();
		txtCarDivision.setDisable(true);
		cbHouse.getSelectionModel().clearSelection();
		txtHouse.clear();
		txtHouse.setDisable(true);
		cbCPIncrese.getSelectionModel().clearSelection();
		cbCPICondition.getSelectionModel().clearSelection();
		cbCPICondition.setDisable(true);
		cbCPIYear.getSelectionModel().clearSelection();
		cbCPIYear.setDisable(true);
		cbBuyOut.getSelectionModel().clearSelection();
		cbBuyOutCondition.getSelectionModel().clearSelection();
		cbBuyOutCondition.setDisable(true);
		txtBuyOutPay.clear();
		txtBuyOutPay.setDisable(true);
		cbRelease.getSelectionModel().clearSelection();
		cbReleaseCondition.getSelectionModel().clearSelection();
		cbReleaseCondition.setDisable(true);
		txtReleasePenalty.clear();
		txtReleasePenalty.setDisable(true);
		dpConDate.setValue(LocalDate.now());
		dpExpireDate.setValue(LocalDate.now());
		cbContract.getSelectionModel().clearSelection();
		btnContractEdit.setDisable(true);
		txtContractFee.clear();
		txtAgentFee.clear();
		dpSdate.setValue(LocalDate.now());
		dpQdate.setValue(LocalDate.now());
		btnRegist.setDisable(true);
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
		txtContractTotal.clear();
		cbContract.setDisable(false);
		totalList();

	}

	public void totalList() {

		Object[][] totalData;
		ContractInfoDAO cDao = new ContractInfoDAO();
		ContractInfoVO cVo = null;
		ArrayList<String> title;
		ArrayList<ContractInfoVO> list;

		title = cDao.getColumnName();
		int columnCount = title.size();

		list = cDao.getContractTotal();
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {

			cVo = list.get(index);
			data.add(cVo);

		}

	}

}
