package main;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

import connectmysql.MySQLConnect;
import model.Objects;
import model.TestCase;
import model.TestScript;
import model.TestSuite;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import action.RunTestCase;

public class frmAddTestSuite extends JFrame {

	private JTextField txtName;
	private JComboBox cmbTestCase;

	private ArrayList<TestCase> testcaseListInProject = new ArrayList<TestCase>();
	private ArrayList<TestCase> testcaseListInTestSuite = new ArrayList<TestCase>();
	// Lúc đầu gán ID_TESTSUITE = 0, sau khi Thực hiện hành động lưu Testcase thì
	// gán ID_TESTSUITE lại
	private int ID_TESTSUITE = 0;
	private JTable tableTestSuite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAddTestSuite frame = new frmAddTestSuite(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.constructor
	 */

	public frmAddTestSuite(int id_project) {
		final int ID_PROJECT = id_project;
		initialize_new();
	}

	public frmAddTestSuite(int id_project, int id_testsuite) {
		final int ID_PROJECT = id_project;
		final int ID_OBJECT = id_testsuite;
		initialize_old(ID_PROJECT, ID_OBJECT);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize_new() {

		setBounds(100, 100, 902, 483);
		setFont(new Font(".VnTime", Font.PLAIN, 15));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(521, 11, 355, 422);
		getContentPane().add(panel);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(122, 162, 189, 37);
		panel.add(txtName);

		JTextArea txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		txtDescription.setBounds(122, 247, 189, 45);
		panel.add(txtDescription);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = cmbTestCase.getSelectedIndex();
				TestCase testcase = testcaseListInProject.get(index);
				testcaseListInTestSuite.add(testcase);
				Show_TestCase_In_JTable();
			}
		});
		btnAdd.setBounds(47, 356, 89, 23);
		panel.add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(266, 356, 89, 23);
		panel.add(btnCancel);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(162, 356, 89, 23);
		panel.add(btnDelete);

		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(23, 167, 53, 26);
		panel.add(label_1);

		JLabel descript = new JLabel("M\u00F4 t\u1EA3 ");
		descript.setBounds(23, 266, 89, 26);
		panel.add(descript);

		// Tạo combobox cho danh sách testcase

		Connection conn = MySQLConnect.getConnection();
		// ArrayList<Objects> objectList = new ArrayList<Objects>();
		String query = "SELECT * FROM mystudio.testcase where mystudio.testcase.ID_PROJECT = "
				+ frmMain.indexSelectProject;
		// String query = "SELECT * FROM mystudio.testcase where
		// mystudio.testcase.ID_PROJECT = " + 19;
		System.out.println(frmMain.indexSelectProject);
		Statement st;
		ResultSet res;

		try {
			st = conn.createStatement();
			res = st.executeQuery(query);
			TestCase testcase;
			while (res.next()) {
				// JOptionPane.showMessageDialog(null, res.getString("METHODTYPE"));
				testcase = new TestCase(res.getInt("ID"), res.getString("NAME"), res.getInt("ID_PROJECT"));
				// System.out.println(object);
				testcaseListInProject.add(testcase);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] testcaseName = new String[testcaseListInProject.size()];
		for (int i = 0; i < testcaseListInProject.size(); i++) {
			testcaseName[i] = testcaseListInProject.get(i).getName().toString();
		}
		DefaultComboBoxModel<String> comboTestcase = new DefaultComboBoxModel<String>(testcaseName);
		cmbTestCase = new JComboBox(comboTestcase);

		cmbTestCase.setBounds(122, 74, 189, 37);
		panel.add(cmbTestCase);
		////////////////////////
		JLabel lblTestcase = new JLabel("Testcase");
		lblTestcase.setBounds(10, 67, 74, 51);
		panel.add(lblTestcase);

		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (TestCase testcase : testcaseListInTestSuite) {
					ArrayList<TestScript> testscriptList = frmMain.getTestScriptList(testcase.getId());
					RunTestCase example = new RunTestCase();
					System.out.println(testscriptList);
					example.executeTestCase(testscriptList, ID_TESTSUITE);
				}
			}
		});
		btnRun.setBounds(59, 393, 89, 23);
		getContentPane().add(btnRun);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/*
				 * DefaultListModel DLM = new DefaultListModel<>(); DLM.addElement("Mark");
				 * DLM.addElement("John"); DLM.addElement("Nana"); listTestCase.setModel(DLM);
				 */
			}
		});
		btnLoad.setBounds(195, 393, 89, 23);
		getContentPane().add(btnLoad);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 22, 488, 342);
		getContentPane().add(scrollPane);

		tableTestSuite = new JTable() {
			// non editabe JTabel
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tableTestSuite.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Testcase name", "Result", "Log", "Time" }));
		scrollPane.setViewportView(tableTestSuite);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name;
				try {
					name = txtName.getText().toString().trim();

					if (name.equals("")) {
						txtName.requestFocus();
						JOptionPane.showMessageDialog(null, "You must fill Name field", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Bad input data", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Thêm một test suite
				String query;
				query = "INSERT INTO `mystudio`.`testsuite`(`NAME`,`ID_PROJECT`) VALUES ( '" + name + "' , "
						+ frmMain.indexSelectProject + ")";
				MySQLConnect.executeSQLQuery(query, "Insert");
				// Lấy id của test suite ra để thêm dữ liệu vào bảng suite case

				Connection conn = MySQLConnect.getConnection();
				Statement st;
				ResultSet res;
				try {
					st = conn.createStatement();
					res = st.executeQuery(
							"SELECT testsuite.id  FROM mystudio.testsuite where testsuite.name = '" + name + "'");
					while (res.next()) {
						ID_TESTSUITE = res.getInt("ID");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				for (int i = 0; i < testcaseListInTestSuite.size(); i++) {
					query = "INSERT INTO `mystudio`.`suite_case` (`ID_TESTCASE`,`ID_TESTSUITE`) VALUES ( "
							+ testcaseListInTestSuite.get(i).getId() + " , " + ID_TESTSUITE + ")";
					MySQLConnect.executeSQLQuery(query, "Insert");
				}

			}
		});
		btnSave.setBounds(342, 393, 89, 23);
		getContentPane().add(btnSave);
	}

	private void initialize_old(int id_project, final int id_testsuite) {

		setBounds(100, 100, 902, 483);
		setFont(new Font(".VnTime", Font.PLAIN, 15));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(521, 11, 355, 422);
		getContentPane().add(panel);

		JTextArea txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		txtDescription.setBounds(122, 247, 189, 45);
		panel.add(txtDescription);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = cmbTestCase.getSelectedIndex();
				TestCase testcase = testcaseListInProject.get(index);
				testcaseListInTestSuite.add(testcase);
				String query = "INSERT INTO `mystudio`.`suite_case` (`ID_TESTCASE`,`ID_TESTSUITE`) VALUES ( "
						+ testcase.getId() + " , " + id_testsuite + ")";
				MySQLConnect.executeSQLQuery(query, "Insert");
				Show_TestCase_In_JTable();
			}
		});
		btnAdd.setBounds(47, 356, 89, 23);
		panel.add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(266, 356, 89, 23);
		panel.add(btnCancel);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(162, 356, 89, 23);
		panel.add(btnDelete);

		JLabel descript = new JLabel("M\u00F4 t\u1EA3 ");
		descript.setBounds(31, 266, 89, 26);
		panel.add(descript);

		Connection conn = MySQLConnect.getConnection();
		// ArrayList<Objects> objectList = new ArrayList<Objects>();
		String query = "SELECT * FROM mystudio.testcase where mystudio.testcase.ID_PROJECT = "
				+ frmMain.indexSelectProject;
		
		//System.out.println(frmMain.indexSelectProject);
		Statement st;
		ResultSet res;

		try {
			st = conn.createStatement();
			res = st.executeQuery(query);
			TestCase testcase;
			while (res.next()) {
				// JOptionPane.showMessageDialog(null, res.getString("METHODTYPE"));
				testcase = new TestCase(res.getInt("ID"), res.getString("NAME"), res.getInt("ID_PROJECT"));
				// System.out.println(object);
				testcaseListInProject.add(testcase);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] testcaseName = new String[testcaseListInProject.size()];
		for (int i = 0; i < testcaseListInProject.size(); i++) {
			testcaseName[i] = testcaseListInProject.get(i).getName().toString();
		}
		DefaultComboBoxModel<String> comboTestcase = new DefaultComboBoxModel<String>(testcaseName);
		cmbTestCase = new JComboBox(comboTestcase);

		cmbTestCase.setBounds(122, 74, 189, 37);
		panel.add(cmbTestCase);
		////////////////////////
		JLabel lblTestcase = new JLabel("Testcase");
		lblTestcase.setBounds(31, 85, 46, 14);
		panel.add(lblTestcase);

		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (TestCase testcase : testcaseListInTestSuite) {
					ArrayList<TestScript> testscriptList = frmMain.getTestScriptList(testcase.getId());
					RunTestCase example = new RunTestCase();
					//System.out.println(testscriptList);
					example.executeTestCase(testscriptList, ID_TESTSUITE);
				}
			}
		});
		btnRun.setBounds(59, 393, 89, 23);
		getContentPane().add(btnRun);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/*
				 * DefaultListModel DLM = new DefaultListModel<>(); DLM.addElement("Mark");
				 * DLM.addElement("John"); DLM.addElement("Nana"); listTestCase.setModel(DLM);
				 */
			}
		});
		btnLoad.setBounds(195, 393, 89, 23);
		getContentPane().add(btnLoad);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 22, 488, 342);
		getContentPane().add(scrollPane);

		tableTestSuite = new JTable() {
			// non editabe JTabel
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tableTestSuite.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Testcase name", "Result", "Log", "Time" }));
		scrollPane.setViewportView(tableTestSuite);

		// Update information
		TestCase testcase = new TestCase();
		conn = MySQLConnect.getConnection();

		query = "SELECT DISTINCT mystudio.suite_case.ID_TESTCASE AS ID ,mystudio.testcase.NAME AS NAME FROM mystudio.suite_case inner join mystudio.testcase on mystudio.testcase.ID = mystudio.suite_case.ID_TESTCASE and mystudio.suite_case.ID_TESTSUITE ="
				+ id_testsuite;
		try {
			st = conn.createStatement();
			res = st.executeQuery(query);

			while (res.next()) {
				testcase = new TestCase(res.getInt("ID"), res.getString("NAME"), id_project);
				testcaseListInTestSuite.add(testcase);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Show_TestCase_In_JTable();
	}

	public void Show_TestCase_In_JTable() {

		DefaultTableModel model = (DefaultTableModel) tableTestSuite.getModel();
		// Dòng này để xóa hết dữ liệu cũ của table
		model.setRowCount(0);
		// Dòng này để xóa hết dữ liệu cũ của table
		Object[] row = new Object[7];
		for (int i = 0; i < testcaseListInTestSuite.size(); i++) {
			row[0] = testcaseListInTestSuite.get(i).getName();

			model.addRow(row);
		}
	}

	// Hàm chuyển đổi thời gian
	public String DateToString() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = Calendar.getInstance().getTime();
		try {
			String date_to_string = df.format(date);
			return date_to_string;
		} catch (Exception e) {
			throw new RuntimeException("Failed to parse date: ", e);
		}

	}
}
