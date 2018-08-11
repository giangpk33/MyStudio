package main;

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//connect sql
import connectmysql.MySQLConnect;
import model.TestSuite;
import model.Objects;
import model.Project;
import model.TestCase;
import model.TestScript;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

//Gui
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import action.RunTestCase;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class frmMain {

	private JFrame frame;
	Connection connection = null;
	private JTable table;
	private JPanel panelHeader;
	private JButton btnFile;
	private JButton btnEdit;
	private JButton btnProject;
	private JButton btnHelp;
	private JPanel panelMain;
	private JButton btnSave;
	private JButton btnReport;
	private JButton btnRun;
	private JButton btnPause;
	private JTree tree;
	private JScrollPane scrollPane;
	private JPanel panelTable;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	public static int idSelect = 0;// có cần id không? Nếu như đã có indexSelect và testcaseList thì đã lấy đc id
									// rồi??
	public static int indexSelect = -1;
	public static int indexSelectProject = -1;
	private int ID_TESTCASE = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain window = new frmMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static ArrayList<TestScript> getTestScriptList(int id_testcase) {
		ArrayList<TestScript> testscriptList = new ArrayList<TestScript>();
		Connection conn = MySQLConnect.getConnection();

		testscriptList.clear();// Init list test case
		String query = "SELECT testscript.ID, testscript.ITEM, testscript.INPUT, testscript.OUTPUT, testscript.DESCRIPTION, testscript.ID_TESTCASE, testscript.RESULT, testscript.ID_OBJECT, object.METHODTYPE, object.OBJECTLOCATORS, object.NAME\r\n"
				+ " FROM mystudio.testscript inner join mystudio.object ON testscript.ID_OBJECT = object.ID AND testscript.ID_TESTCASE = "
				+ id_testcase;
		System.out.println(query);
		Statement st;
		ResultSet res;

		try {
			st = conn.createStatement();
			res = st.executeQuery(query);
			TestScript testscript;
			while (res.next()) {
				testscript = new TestScript(res.getInt("ID"), res.getString("ITEM"), res.getString("INPUT"),
						res.getString("OUTPUT"), res.getString("DESCRIPTION"), id_testcase, res.getString("RESULT"),
						new Objects(res.getInt("ID_OBJECT"), res.getString("METHODTYPE"),
								res.getString("OBJECTLOCATORS"), res.getString("NAME")));
				testscriptList.add(testscript);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testscriptList;
	}

	public void Show_TestScript_In_JTable(int id_testcase) {
		ArrayList<TestScript> testscripts = getTestScriptList(id_testcase);
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		Object[] row = new Object[7];
		for (int i = 0; i < testscripts.size(); i++) {
			row[0] = testscripts.get(i).getId();
			row[1] = testscripts.get(i).getItem();
			row[2] = testscripts.get(i).getObjects().getName();
			row[3] = testscripts.get(i).getInput();
			row[4] = testscripts.get(i).getOutput();
			row[5] = testscripts.get(i).getDescription();
			row[6] = testscripts.get(i).getResult();
			model.addRow(row);
		}
	}

	public ArrayList<Project> getProjectList() {
		ArrayList<Project> projectList = new ArrayList<Project>();
		Connection conn = MySQLConnect.getConnection();

		String query = "SELECT * FROM mystudio.project";
		Statement st;
		ResultSet res;

		try {
			st = conn.createStatement();
			res = st.executeQuery(query);
			Project project;
			while (res.next()) {
				project = new Project(res.getString("Name"), res.getString("DESCRIPTION"), res.getInt("ID"));
				projectList.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return projectList;
	}

	public ArrayList<TestCase> getTestCaseList(int id) {
		ArrayList<TestCase> testcasetrueList = new ArrayList<TestCase>();
		Connection conn = MySQLConnect.getConnection();

		String query = "SELECT * FROM mystudio.testcase where mystudio.testcase.ID_PROJECT = " + id;
		Statement st;
		ResultSet res;

		try {
			st = conn.createStatement();
			res = st.executeQuery(query);
			TestCase testcase;
			while (res.next()) {
				testcase = new TestCase(res.getInt("ID"), res.getString("NAME"), res.getInt("ID_PROJECT"));
				testcasetrueList.add(testcase);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return testcasetrueList;
	}

	// tham số: id -> id của project
	public ArrayList<Objects> getObjectList(int id) {
		ArrayList<Objects> objectList = new ArrayList<Objects>();
		Connection conn = MySQLConnect.getConnection();

		String query = "SELECT * FROM mystudio.object where mystudio.object.ID_PROJECT = " + id;
		Statement st;
		ResultSet res;

		try {
			st = conn.createStatement();
			res = st.executeQuery(query);
			Objects object;
			while (res.next()) {
				// JOptionPane.showMessageDialog(null, res.getString("METHODTYPE"));
				object = new Objects(res.getInt("ID"), res.getString("METHODTYPE"), res.getString("OBJECTLOCATORS"),
						res.getString("NAME"), res.getInt("ID_PROJECT"));
				// System.out.println(object);
				objectList.add(object);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// JOptionPane.showMessageDialog(null,"size" + objectList.size());
		return objectList;
	}

	// tham số: id -> id của project
	public ArrayList<TestSuite> getTestSuitetList(int id) {
		ArrayList<TestSuite> testSuiteList = new ArrayList<TestSuite>();
		Connection conn = MySQLConnect.getConnection();

		String query = "SELECT * FROM mystudio.testsuite where mystudio.testsuite.ID_PROJECT = " + id;
		Statement st;
		ResultSet res;

		try {
			st = conn.createStatement();
			res = st.executeQuery(query);
			TestSuite testSuite;
			while (res.next()) {
				// JOptionPane.showMessageDialog(null, res.getString("METHODTYPE"));
				testSuite = new TestSuite(res.getInt("ID"), res.getString("NAME"), res.getInt("ID_PROJECT"));
				// System.out.println(object);
				testSuiteList.add(testSuite);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// JOptionPane.showMessageDialog(null,"size" + objectList.size());
		return testSuiteList;
	}

	public void displayProject() {
		ArrayList<Project> projects = getProjectList();
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		root.removeAllChildren();
		for (int i = 0; i < projects.size(); i++) {
			DefaultMutableTreeNode newTestExployer = new DefaultMutableTreeNode(projects.get(i));
			newTestExployer.add(new DefaultMutableTreeNode("Test case"));
			newTestExployer.add(new DefaultMutableTreeNode("Object"));// New add
			newTestExployer.add(new DefaultMutableTreeNode("Test suite"));// New delete cmt
			newTestExployer.add(new DefaultMutableTreeNode("Report"));// New add
			// newTestExployer.add(new DefaultMutableTreeNode("Object")); New cmt
			model.insertNodeInto(newTestExployer, root, root.getChildCount());

			// Get ArrayList<TestCase>
			ArrayList<TestCase> testcaselist = getTestCaseList(projects.get(i).getId());
			// DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
			// tree.getLastSelectedPathComponent();
			DefaultMutableTreeNode projectNode = (DefaultMutableTreeNode) root.getChildAt(i);
			DefaultMutableTreeNode testcaseNode = (DefaultMutableTreeNode) projectNode.getChildAt(0);
			for (int j = 0; j < testcaselist.size(); j++) {
				DefaultMutableTreeNode newTestCaseExployer = new DefaultMutableTreeNode(testcaselist.get(j));
				model.insertNodeInto(newTestCaseExployer, testcaseNode, testcaseNode.getChildCount());

				// Lấy các thuộc tính của Test Case từ cây ra
				/*
				 * TestCaseTrue js = (TestCaseTrue) newTestCaseExployer.getUserObject();
				 * System.out.println(js.getName() + js.getId_project() + js.getClass() +
				 * js.getId());
				 */
			}

			// Lấy danh sách object ra
			// Nếu có test suite thì đây là getChildAt(2)
			// DefaultMutableTreeNode objectNode = (DefaultMutableTreeNode)
			// projectNode.getChildAt(2);
			// Nếu không có test suite thì đây là getChildAt(1)
			DefaultMutableTreeNode objectNode = (DefaultMutableTreeNode) projectNode.getChildAt(1);
			ArrayList<Objects> objectList = getObjectList(projects.get(i).getId());
			// JOptionPane.showMessageDialog(null, "số phần tử của object là :" +
			// objectList.size());
			for (int k = 0; k < objectList.size(); k++) {
				DefaultMutableTreeNode newObjectExployer = new DefaultMutableTreeNode(objectList.get(k));
				model.insertNodeInto(newObjectExployer, objectNode, objectNode.getChildCount());
			}

			// Hiển thị danh sách TestSuite
			DefaultMutableTreeNode testsuiteNode = (DefaultMutableTreeNode) projectNode.getChildAt(2);
			ArrayList<TestSuite> testsuiteList = getTestSuitetList(projects.get(i).getId());
			// JOptionPane.showMessageDialog(null, "số phần tử của object là :" +
			// objectList.size());
			for (int k = 0; k < testsuiteList.size(); k++) {
				DefaultMutableTreeNode newTestSuiteExployer = new DefaultMutableTreeNode(testsuiteList.get(k));
				model.insertNodeInto(newTestSuiteExployer, testsuiteNode, testsuiteNode.getChildCount());
			}

			//Hiển thị danh sách report 
			DefaultMutableTreeNode reportNode = (DefaultMutableTreeNode) projectNode.getChildAt(3);
			//ArrayList<TestSuite> testsuiteList = getTestSuitetList(projects.get(i).getId());
			// JOptionPane.showMessageDialog(null, "số phần tử của object là :" +
			// objectList.size());
			for (int k = 0; k < testsuiteList.size(); k++) {
				DefaultMutableTreeNode newReportExployer = new DefaultMutableTreeNode(testsuiteList.get(k));
				model.insertNodeInto(newReportExployer, reportNode, reportNode.getChildCount());
			}
		}
		model.reload();
	}

	public void AddTestCaseTittle() {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (selectedNode != null) {

			String tittle = selectedNode.getUserObject().toString();
			System.out.println(tittle);
			if (tittle.equals("Test case")) {
				int dialogButton = JOptionPane.showConfirmDialog(null, "Would You Like to create a new test case?",
						"Warning", JOptionPane.YES_NO_OPTION);
				if (dialogButton == JOptionPane.YES_OPTION) {
					System.out.println("Tạo test case");
					DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
					Project pro = (Project) parent.getUserObject();
					indexSelectProject = pro.getId();
					frmAddTestCase frm = new frmAddTestCase();
					frm.setLocationRelativeTo(null);
					frm.setVisible(true);
					frm.addWindowListener(new WindowListener() {
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							displayProject();

						}

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}
					});
				}
				/*
				 * else { System.out.println("Không tạo test case"); }
				 */

			}

			if (selectedNode.isLeaf()) {
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
				if (parent.getUserObject().equals("Test case")) {

					// Click vào từng test case sẽ hiển thị test case đó vào bảng
					TestCase test = (TestCase) selectedNode.getUserObject();
					indexSelectProject = test.getId_project();// Cập nhật id project
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					Show_TestScript_In_JTable(test.getId());
					ID_TESTCASE = test.getId();
					frmMain.idSelect = 0;
					frmMain.indexSelect = -1;
				}
			}

		}
	}

	public void AddRePort() {
		
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (selectedNode != null) {

			String tittle = selectedNode.getUserObject().toString();
			

			if (selectedNode.isLeaf()) {
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
				if (parent.getUserObject().equals("Report")) {
					TestSuite currentTestSuite = (TestSuite) selectedNode.getUserObject();
					indexSelectProject = currentTestSuite.getId_project();// Cập nhật id project
					int id_testsuite = currentTestSuite.getId();
					// System.out.println(id_object);
					//frmAddTestSuite frm = new frmAddTestSuite(indexSelectProject, id_object);
					frmTestReport frm = new frmTestReport(id_testsuite);
					frm.setLocationRelativeTo(null);
					frm.setVisible(true);
					frm.addWindowListener(new WindowListener() {

						@Override
						public void windowOpened(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosed(WindowEvent arg0) {
							// TODO Auto-generated method stub
							displayProject();
						}

						@Override
						public void windowActivated(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}
					});
				}
			}

		}
	}
	
public void AddTestSuite() {
		
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (selectedNode != null) {

			String tittle = selectedNode.getUserObject().toString();
			if (tittle.equals("Test suite")) {
				int dialogButton = JOptionPane.showConfirmDialog(null, "Would You Like to create a new test suite?",
						"Warning", JOptionPane.YES_NO_OPTION);
				if (dialogButton == JOptionPane.YES_OPTION) {
					DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
					Project pro = (Project) parent.getUserObject();
					// indexSelectProject là id của project đó
					indexSelectProject = pro.getId();
					frmAddTestSuite frm = new frmAddTestSuite(indexSelectProject);
					frm.setLocationRelativeTo(null);
					frm.setVisible(true);
					frm.addWindowListener(new WindowListener() {
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							// sửa lại chỗ này
							displayProject();

						}

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}
					});
				}
				/*
				 * else { System.out.println("Không tạo test case"); }
				 */

			}

			if (selectedNode.isLeaf()) {
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
				if (parent.getUserObject().equals("Test suite")) {
					TestSuite currentTestSuite = (TestSuite) selectedNode.getUserObject();
					indexSelectProject = currentTestSuite.getId_project();// Cập nhật id project
					int id_testsuite = currentTestSuite.getId();
					// System.out.println(id_object);
					//frmAddTestSuite frm = new frmAddTestSuite(indexSelectProject, id_object);
					frmAddTestSuite frm = new frmAddTestSuite(indexSelectProject, id_testsuite);
					frm.setLocationRelativeTo(null);
					frm.setVisible(true);
					frm.addWindowListener(new WindowListener() {

						@Override
						public void windowOpened(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosed(WindowEvent arg0) {
							// TODO Auto-generated method stub
							displayProject();
						}

						@Override
						public void windowActivated(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}
					});
				}
			}

		}
	}

	public void AddObject() {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (selectedNode != null) {

			String tittle = selectedNode.getUserObject().toString();
			System.out.println(tittle);
			if (tittle.equals("Object")) {
				int dialogButton = JOptionPane.showConfirmDialog(null, "Would You Like to create a new object?",
						"Warning", JOptionPane.YES_NO_OPTION);
				if (dialogButton == JOptionPane.YES_OPTION) {
					System.out.println("Tạo object");
					DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
					Project pro = (Project) parent.getUserObject();
					// indexSelectProject là id của project đó
					indexSelectProject = pro.getId();
					frmAddObject frm = new frmAddObject(indexSelectProject);
					frm.setLocationRelativeTo(null);
					frm.setVisible(true);
					frm.addWindowListener(new WindowListener() {
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							// sửa lại chỗ này
							displayProject();

						}

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}
					});
				}
				/*
				 * else { System.out.println("Không tạo test case"); }
				 */

			}

			if (selectedNode.isLeaf()) {
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
				if (parent.getUserObject().equals("Object")) {
					Objects currentObject = (Objects) selectedNode.getUserObject();
					indexSelectProject = currentObject.getId_project();// Cập nhật id project
					int id_object = currentObject.getId();
					// System.out.println(id_object);
					frmAddObject frm = new frmAddObject(indexSelectProject, id_object);
					frm.setLocationRelativeTo(null);
					frm.setVisible(true);
					frm.addWindowListener(new WindowListener() {

						@Override
						public void windowOpened(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosed(WindowEvent arg0) {
							// TODO Auto-generated method stub
							displayProject();
						}

						@Override
						public void windowActivated(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}
					});
				}
			}

		}
	}
	/**
	 * Create the application.
	 */

	public frmMain() {
		initialize();
		connection = MySQLConnect.getConnection();
		// getTestCaseList();
		displayProject();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		// frame.setBounds(100, 100, 805, 482);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panelHeader = new JPanel();
		panelHeader.setBackground(Color.WHITE);
		FlowLayout fl_panelHeader = (FlowLayout) panelHeader.getLayout();
		fl_panelHeader.setAlignment(FlowLayout.LEFT);
		panelHeader.setBounds(10, 11, 964, 30);
		frame.getContentPane().add(panelHeader);

		btnProject = new JButton("New Project");
		btnProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnProject.setEnabled(false);

				frmAddProject fra = new frmAddProject();
				fra.setLocationRelativeTo(null);
				fra.setVisible(true);
				fra.addWindowListener(new WindowListener() {

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						btnProject.setEnabled(true);
						// displayProject();
					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub

					}

				});
			}
		});
		btnProject.setBackground(new Color(0, 128, 128));
		panelHeader.add(btnProject);

		btnFile = new JButton("File");
		btnFile.setBackground(new Color(0, 128, 128));
		panelHeader.add(btnFile);

		btnEdit = new JButton("Edit");
		btnEdit.setBackground(new Color(0, 128, 128));
		panelHeader.add(btnEdit);

		btnHelp = new JButton("Help");
		btnHelp.setBackground(new Color(0, 128, 128));
		panelHeader.add(btnHelp);

		panelMain = new JPanel();
		panelMain.setBackground(new Color(192, 192, 192));
		FlowLayout fl_panelMain = (FlowLayout) panelMain.getLayout();
		fl_panelMain.setAlignment(FlowLayout.LEFT);
		panelMain.setBounds(12, 42, 962, 35);
		frame.getContentPane().add(panelMain);

		btnSave = new JButton("Save");
		btnSave.setBackground(new Color(0, 128, 128));
		btnSave.setIcon(new ImageIcon("D:\\AUTOMATION TEST\\MyStudio1.1\\images\\save_all_16.png"));
		panelMain.add(btnSave);

		btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*frmTestReport frm = new frmTestReport();
				frm.setLocationRelativeTo(null);
				frm.setVisible(true);*/
			}

		});
		btnReport.setBackground(new Color(0, 128, 128));
		btnReport.setIcon(new ImageIcon("D:\\AUTOMATION TEST\\MyStudio1.3\\images\\report_16.png"));
		panelMain.add(btnReport);

		btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ID_TESTCASE > 0) {
					ArrayList<TestScript> testscriptList = getTestScriptList(ID_TESTCASE);
					RunTestCase example = new RunTestCase();
					//Đối với trường hợp chạy đơn lẻ 1 testcase, gán id_testsuite = 0, để phân biệt với những testsuite khác
					int id_testsuite = 0;
					example.executeTestCase(testscriptList, id_testsuite);

				}
			}
		});
		btnRun.setBackground(new Color(0, 128, 128));
		btnRun.setIcon(new ImageIcon("D:\\AUTOMATION TEST\\MyStudio1.1\\images\\done_16.png"));
		panelMain.add(btnRun);

		btnPause = new JButton("Pause");
		btnPause.setBackground(new Color(0, 128, 128));
		btnPause.setIcon(new ImageIcon("D:\\AUTOMATION TEST\\MyStudio1.1\\images\\stop_16.png"));
		panelMain.add(btnPause);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(167, 125, 807, 425);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			// non editabe JTabel
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Dùng cho update 1 test case
				// JOptionPane.showMessageDialog(null, "click");
				indexSelect = table.getSelectedRow();
				TableModel model = table.getModel();
				idSelect = (Integer.parseInt(model.getValueAt(indexSelect, 0).toString()));
				// JOptionPane.showMessageDialog(null, "idselect "+idSelect);
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Item", "Object", "Input", "Ouput", "Description", "Result" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(39);
		table.getColumnModel().getColumn(2).setPreferredWidth(65);

		scrollPane.setViewportView(table);

		panelTable = new JPanel();
		FlowLayout fl_panelTable = (FlowLayout) panelTable.getLayout();
		fl_panelTable.setAlignment(FlowLayout.LEFT);
		panelTable.setBounds(167, 84, 807, 30);
		frame.getContentPane().add(panelTable);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// idSelect = 0;
				// frame.dispose();
				if (ID_TESTCASE > 0) {
					frmAddTestScript frm = new frmAddTestScript(ID_TESTCASE);
					// <<--frm display center screen
					frm.setLocationRelativeTo(null);
					// 27/4/2018-->
					frm.setVisible(true);
				}
			}
		});
		btnAdd.setIcon(new ImageIcon("D:\\AUTOMATION TEST\\MyStudio1.1\\images\\add_16.png"));
		btnAdd.setBackground(new Color(0, 128, 128));
		panelTable.add(btnAdd);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "DELETE FROM `mystudio`.`testscript` WHERE `ID` = " + idSelect;
				// executeSQLQuery(query, "Delete");// Call method executeSQLQuery of frmMain.
				MySQLConnect.executeSQLQuery(query, "Delete");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				Show_TestScript_In_JTable(ID_TESTCASE);
				idSelect = 0;
				indexSelect = -1;
			}
		});
		btnDelete.setIcon(new ImageIcon("D:\\AUTOMATION TEST\\MyStudio1.1\\images\\clear_16.png"));
		btnDelete.setBackground(new Color(0, 128, 128));
		panelTable.add(btnDelete);

		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				Show_TestScript_In_JTable(ID_TESTCASE);
				frmMain.idSelect = 0;
				frmMain.indexSelect = -1;
			}
		});
		panelTable.add(btnReload);
		btnReload.setBackground(new Color(0, 128, 128));
		btnReload.setIcon(new ImageIcon("D:\\AUTOMATION TEST\\MyStudio1.1\\images\\folder_test_case_16.png"));

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (idSelect > 0 && ID_TESTCASE > 0) {
					frmUpdateTestScript frm = new frmUpdateTestScript();
					// <<--frm display center screen
					frm.setLocationRelativeTo(null);
					// 27/4/2018-->
					frm.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,
							"You must reload and choose the test case." + ID_TESTCASE + idSelect);
				}
			}
		});
		btnUpdate.setBackground(new Color(0, 128, 128));
		panelTable.add(btnUpdate);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 88, 148, 462);
		frame.getContentPane().add(scrollPane_1);

		tree = new JTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				DefineIndexDroject();
				AddTestCaseTittle();
				AddObject();
				AddTestSuite();
				AddRePort();
			}

			private void DefineIndexDroject() {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				try {
					System.out.println(currentNode);
					if (currentNode != null && !currentNode.isRoot()) {
						DefaultMutableTreeNode parent = (DefaultMutableTreeNode) currentNode.getParent();
						if (parent.isRoot()) {
							Project project = (Project) currentNode.getUserObject();
							indexSelectProject = project.getId();
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});

		scrollPane_1.setViewportView(tree);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("My Studio") {
			{
				add(new DefaultMutableTreeNode(""));
			}
		}));
		tree.setScrollsOnExpand(true);
	}
}
