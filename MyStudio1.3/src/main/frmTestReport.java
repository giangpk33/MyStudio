package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import connectmysql.MySQLConnect;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmTestReport extends JFrame {

	// Create the dialog
	Connection connection = null;
	String sql;
	JasperDesign jd;
	JasperReport jr;
	JasperPrint jp;
	HashMap params;
	private JLayeredPane show_report;
	private JCheckBox chckbxViewAll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmTestReport frame = new frmTestReport(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmTestReport(int id_testsuite) {

		System.out.println("Đã sang");
		setBounds(100, 100, 1000, 600);

		setFont(new Font(".VnTime", Font.PLAIN, 15));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLayeredPane show_report = new JLayeredPane();
		show_report.setBounds(100, 100, 800, 700);
		// show_report.setMaximumSize(new Dimension(800, 550));
		getContentPane().add(show_report);

		// String query = "SELECT NAME, TIME, RESULT, LOG FROM mystudio.result join
		// mystudio.testcase ON mystudio.result.ID_TESTCASE = mystudio.testcase.ID";
		String query = "SELECT testcase.name AS testcase_name, testsuite.name as testsuit_name,result.time,result.log,result.result FROM suite_case inner join testcase\r\n"
				+ "on testcase.id=suite_case.id_testcase\r\n"
				+ "inner join testsuite on testsuite.id= suite_case.id_testsuite\r\n"
				+ "inner join result on result.id= suite_case.id_result\r\n" + "where testsuite.ID=" + id_testsuite;
		commonPrintFunction(query, "testReport");

		show_report.removeAll();
		show_report.setLayout(new BorderLayout());
		show_report.repaint();
		show_report.add(new JRViewer(jp));
		show_report.revalidate();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	/*
	 * private JLabel company_name; private JLabel address; private JComboBox
	 * expen_paymode_search; private JDateChooser date1_1; private JComboBox
	 * select_product; private JLayeredPane show_report; private JTextField
	 * txtTaxInvoisce;
	 */

	public void commonPrintFunction(String sql, String jasper) {
		connection = MySQLConnect.getConnection();
		try {
			jd = JRXmlLoader.load(new File("").getAbsolutePath() + "/src/main/" + jasper + ".jrxml");

			params = new HashMap();

			params.put("testcasename", null);
			params.put("time", null);
			params.put("result", null);
			params.put("log", null);

			JRDesignQuery newQuery = new JRDesignQuery();
			newQuery.setText(sql);
			jd.setQuery(newQuery);
			jr = JasperCompileManager.compileReport(jd);
			jp = JasperFillManager.fillReport(jr, params, connection);
			JasperPrintManager.printReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {

			}
		}
	}
}
