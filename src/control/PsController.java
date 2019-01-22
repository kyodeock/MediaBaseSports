package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.MathContext;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import model.ContractInfoVO;

public class PsController implements Initializable{

	@FXML
	private BarChart barChart;
	@FXML
	private Button btnPDF;
	@FXML
	private Button btnExit;
	
	ObservableList<ContractInfoVO> data = FXCollections.observableArrayList();
	ContractInfoVO cVo = new ContractInfoVO();
	ContractInfoDAO cDao = new ContractInfoDAO();
	
	public static String div = null;
	public static String id = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		data.addAll(cDao.getContractTotal());
		
			XYChart.Series SalesJanuary = new XYChart.Series();
			SalesJanuary.setName("1월");
			ObservableList January = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("01")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				January.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesJanuary.setData(January);
			barChart.getData().add(SalesJanuary);

			XYChart.Series SalesFebruary = new XYChart.Series();
			SalesFebruary.setName("2월");
			ObservableList February = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("02")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				February.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesFebruary.setData(February);
			barChart.getData().add(SalesFebruary);
			
			XYChart.Series SalesMarch = new XYChart.Series();
			SalesMarch.setName("3월");
			ObservableList March = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("03")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				March.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesMarch.setData(March);
			barChart.getData().add(SalesMarch);
			
			XYChart.Series SalesApril = new XYChart.Series();
			SalesApril.setName("4월");
			ObservableList April = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("04")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				April.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesApril.setData(April);
			barChart.getData().add(SalesApril);
			
			XYChart.Series SalesMay = new XYChart.Series();
			SalesMay.setName("5월");
			ObservableList May = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("05")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				May.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesMay.setData(May);
			barChart.getData().add(SalesMay);
			
			XYChart.Series SalesJune = new XYChart.Series();
			SalesJune.setName("6월");
			ObservableList June = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("06")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				June.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesJune.setData(June);
			barChart.getData().add(SalesJune);
			
			XYChart.Series SalesJuly = new XYChart.Series();
			SalesJuly.setName("7월");
			ObservableList July = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("07")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				July.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesJuly.setData(July);
			barChart.getData().add(SalesJuly);
			
			XYChart.Series SalesAugust = new XYChart.Series();
			SalesAugust.setName("8월");
			ObservableList August = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("08")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				August.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesAugust.setData(August);
			barChart.getData().add(SalesAugust);
			
			XYChart.Series SalesSeptember = new XYChart.Series();
			SalesSeptember.setName("9월");
			ObservableList September = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("09")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				September.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesSeptember.setData(September);
			barChart.getData().add(SalesSeptember);
			
			XYChart.Series SalesOctober = new XYChart.Series();
			SalesOctober.setName("10월");
			ObservableList October = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("10")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				October.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesOctober.setData(October);
			barChart.getData().add(SalesOctober);
			
			XYChart.Series SalesNovember = new XYChart.Series();
			SalesNovember.setName("11월");
			ObservableList November = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("11")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				November.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesNovember.setData(November);
			barChart.getData().add(SalesNovember);
			
			XYChart.Series SalesDecember = new XYChart.Series();
			SalesDecember.setName("12월");
			ObservableList December = FXCollections.observableArrayList();
			for (int i = 0; i < data.size(); i++) {
				double agentFee = 0;
				for (int j = 0; j < data.size(); j++) {
					if (data.get(j).getC_condate().toString().substring(0, 4)
							.equals(data.get(i).getC_condate().toString().substring(0, 4))
							&& data.get(j).getC_negocs().equals("협상체결")
							&& data.get(j).getC_condate().toString().substring(5, 7).equals("12")) {
						
						agentFee = agentFee + data.get(j).getC_agentfee();
						
					}
				}
				December.add(new XYChart.Data(data.get(i).getC_condate().toString().substring(0, 4), agentFee));
			}
			SalesDecember.setData(December);
			barChart.getData().add(SalesDecember);
			
		btnPDF.setOnAction(event -> handlerBtnPDFAction(event));
		btnExit.setOnAction(event -> handlerBtnExitAction(event));
	}

	public void handlerBtnPDFAction(ActionEvent event) {
		
		try {
			Document document = new Document(PageSize.A4, 0, 0, 30, 30);
			// pdf 파일을 저장할 공간을 선언, pdf파일이 생성된다. 그후 스트림으로 저장.
			String strReportPDFName = "contract_" + System.currentTimeMillis() + ".pdf";
			PdfWriter.getInstance(document,
					new FileOutputStream("c:\\temp" + "\\" + strReportPDFName));
			// document를 열어 pdf문서를 쓸수 있도록한다
			document.open();
			// 한글 지원폰트 설정
			BaseFont bf = BaseFont.createFont("font/MALGUN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 8, Font.NORMAL);
			Font font2 = new Font(bf, 14, Font.BOLD);
			// 타이틀
			Paragraph title = new Paragraph("수수료 수익표", font2);
			// 중간 정렬
			title.setAlignment(Element.ALIGN_CENTER);
			// 문서에 추가
			document.add(title);
			document.add(new Paragraph("\r\n"));

			// 테이블생성 table객체보다 pdfptable객체가 더 정교하게 테이블을 만들수 있다
			// 생성자에 컬럼수를 써준다
			PdfPTable table = new PdfPTable(9);
			// 각각의 컬럼에 width를 정한다.
			table.setWidths(new int[] { 30, 50, 30, 30, 30, 30, 30, 30, 30});

			// 컬럼 타이틀
			PdfPCell header1 = new PdfPCell(new Paragraph("계약번호", font));
			PdfPCell header2 = new PdfPCell(new Paragraph("선수명", font));
			PdfPCell header3 = new PdfPCell(new Paragraph("구단명", font));
			PdfPCell header4 = new PdfPCell(new Paragraph("주급", font));
			PdfPCell header5 = new PdfPCell(new Paragraph("계약금", font));
			PdfPCell header6 = new PdfPCell(new Paragraph("수수료", font));
			PdfPCell header7 = new PdfPCell(new Paragraph("계약총액", font));
			PdfPCell header8 = new PdfPCell(new Paragraph("계약체결일", font));
			PdfPCell header9 = new PdfPCell(new Paragraph("계약만료일", font));

			// 가로정렬
			header1.setHorizontalAlignment(Element.ALIGN_CENTER);
			header2.setHorizontalAlignment(Element.ALIGN_CENTER);
			header3.setHorizontalAlignment(Element.ALIGN_CENTER);
			header4.setHorizontalAlignment(Element.ALIGN_CENTER);
			header5.setHorizontalAlignment(Element.ALIGN_CENTER);
			header6.setHorizontalAlignment(Element.ALIGN_CENTER);
			header7.setHorizontalAlignment(Element.ALIGN_CENTER);
			header8.setHorizontalAlignment(Element.ALIGN_CENTER);
			header9.setHorizontalAlignment(Element.ALIGN_CENTER);

			// 세로정렬
			header1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header4.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header5.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header6.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header7.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header8.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header9.setVerticalAlignment(Element.ALIGN_MIDDLE);

			// 테이블에 추가
			table.addCell(header1);
			table.addCell(header2);
			table.addCell(header3);
			table.addCell(header4);
			table.addCell(header5);
			table.addCell(header6);
			table.addCell(header7);
			table.addCell(header8);
			table.addCell(header9);

			// DB 연결 및 리스트 선택
			ContractInfoDAO cDao = new ContractInfoDAO();
			ContractInfoVO cVo = new ContractInfoVO();
			ArrayList<ContractInfoVO> list;
			list = cDao.getContractTotal();
			int rowCount = list.size();

			PdfPCell cell1 = null;
			PdfPCell cell2 = null;
			PdfPCell cell3 = null;
			PdfPCell cell4 = null;
			PdfPCell cell5 = null;
			PdfPCell cell6 = null;
			PdfPCell cell7 = null;
			PdfPCell cell8 = null;
			PdfPCell cell9 = null;

			for (int index = 0; index < rowCount; index++) {
				cVo = list.get(index);

				cell1 = new PdfPCell(new Paragraph(cVo.getC_no() + "", font));
				cell2 = new PdfPCell(new Paragraph(cVo.getP_name(), font));
				cell3 = new PdfPCell(new Paragraph(cVo.getF_name(), font));
				cell4 = new PdfPCell(new Paragraph(cVo.getC_wage() + "", font));
				cell5 = new PdfPCell(new Paragraph(cVo.getC_loyalty() + "", font));
				cell6 = new PdfPCell(new Paragraph(cVo.getC_agentfee() + "", font));
				cell7 = new PdfPCell(new Paragraph(cVo.getC_contractTotal() + "", font));
				cell8 = new PdfPCell(new Paragraph(cVo.getC_condate() + "", font));
				cell9 = new PdfPCell(new Paragraph(cVo.getC_cexpiredate() + "", font));

				// 가로정렬
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell9.setHorizontalAlignment(Element.ALIGN_CENTER);

				// 세로정렬
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);

				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
				table.addCell(cell5);
				table.addCell(cell6);
				table.addCell(cell7);
				table.addCell(cell8);
				table.addCell(cell9);

			}
			// 문서에 테이블 추가.
			document.add(table);
			document.add(new Paragraph("\r\n"));
			Alert alert = new Alert(AlertType.INFORMATION);
			
			Paragraph barImageTitle = new Paragraph("월별 수수료수익 막대 그래프", font);
			barImageTitle.setAlignment(Element.ALIGN_CENTER);
			document.add(barImageTitle);
			document.add(new Paragraph("\r\n"));
			final String barImageUrl = "C:\\Users\\alfo\\Desktop\\contractBarChart.png";

			// 기존에 javafx.scene.image.Image 객체를 사용하고 있어 충돌이 생겨 아래와 같이 사용함.
			com.itextpdf.text.Image barImage;
				if (com.itextpdf.text.Image.getInstance(barImageUrl) != null) {
					barImage = com.itextpdf.text.Image.getInstance(barImageUrl);
					barImage.setAlignment(Element.ALIGN_CENTER);
					barImage.scalePercent(30f);
					document.add(barImage);
					document.add(new Paragraph("\r\n"));
				}
			
			document.close();

			alert.setTitle("PDF 파일 생성");
			alert.setHeaderText("협상 목록 PDF 파일 생성 성공.");
			alert.setContentText("협상 목록 PDF 파일.");
			alert.showAndWait();
			
		} catch (Exception e) {
			
			System.err.println(e);
			
		}
		
	}
	
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
	
}
