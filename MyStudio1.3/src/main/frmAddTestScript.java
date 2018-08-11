package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import connectmysql.MySQLConnect;
import model.Objects;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class frmAddTestScript extends JFrame {

	private JPanel contentPane;
	private JTextField txtInput;
	private JTextField txtOutput;
	private JTextArea txtDescription;
	private JLabel lblChooseItem;
	private JComboBox cmbItem;
	private JLabel lblObject;
	private JComboBox cmbObject;
	private JLabel lblInput;
	private JLabel lblOutput;
	private JLabel lblDescription;
	private JButton btnAdd;
	private JButton btnCancer;
	/*
	 * private int id_object = 0; private String method_type = null; private String
	 * object_name = null; private String object_locator = null;
	 */
	private Objects objectCurrent = null;
	private ArrayList<Objects> objectList = new ArrayList<Objects>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAddTestScript frame = new frmAddTestScript();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmAddTestScript() {

	}

	public frmAddTestScript(int id_testcase) {
		final int ID_TESTCASE = id_testcase;
		setFont(new Font(".VnTime", Font.PLAIN, 15));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblChooseItem = new JLabel("Choose Item");
		lblChooseItem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblChooseItem.setBounds(28, 28, 105, 23);
		contentPane.add(lblChooseItem);

		String[] item = { EnumTypes.ACTIONTYPES.CLICK.toString(), EnumTypes.ACTIONTYPES.OPEN_BROWSER.toString(),
				EnumTypes.ACTIONTYPES.FIND.toString(), EnumTypes.ACTIONTYPES.SENDKEY.toString(),
				EnumTypes.ACTIONTYPES.SUBMIT.toString(), EnumTypes.ACTIONTYPES.VERIFY_EQUAL.toString(),
				EnumTypes.ACTIONTYPES.PAGE_LOAD_TIMEOUT.toString(), EnumTypes.ACTIONTYPES.CHECK_VISIBLE.toString(),
				EnumTypes.ACTIONTYPES.CLOSE_BROWSER.toString(), EnumTypes.ACTIONTYPES.WAIT.toString(),
				EnumTypes.ACTIONTYPES.MAXIMIZE_WINDOW.toString() };
		DefaultComboBoxModel<String> comboItem = new DefaultComboBoxModel<String>(item);
		// Thêm ở đây
		cmbItem = new JComboBox(comboItem);
		cmbItem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cmbItem.setBounds(165, 29, 131, 20);
		contentPane.add(cmbItem);

		// Tạo combobox cho danh sách object
		Connection conn = MySQLConnect.getConnection();
		// ArrayList<Objects> objectList = new ArrayList<Objects>();
		String query = "SELECT * FROM mystudio.object where mystudio.object.ID_PROJECT = " + frmMain.indexSelectProject;
		System.out.println(frmMain.indexSelectProject);
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

		String[] objectName = new String[objectList.size()];
		for (int i = 0; i < objectList.size(); i++) {
			objectName[i] = objectList.get(i).getName().toString();
		}
		DefaultComboBoxModel<String> comboObject = new DefaultComboBoxModel<String>(objectName);
		cmbObject = new JComboBox(comboObject);
		cmbObject.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int index = cmbObject.getSelectedIndex();
				objectCurrent = objectList.get(index);
			}
		});
		cmbObject.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cmbObject.setBounds(165, 70, 131, 20);
		contentPane.add(cmbObject);
		lblObject = new JLabel("Object");
		lblObject.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblObject.setBounds(28, 73, 71, 23);
		contentPane.add(lblObject);

		lblInput = new JLabel("Input");
		lblInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblInput.setBounds(28, 118, 46, 14);
		contentPane.add(lblInput);

		txtInput = new JTextField();
		txtInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtInput.setBounds(165, 115, 131, 20);
		contentPane.add(txtInput);
		txtInput.setColumns(10);

		lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblOutput.setBounds(28, 162, 46, 14);
		contentPane.add(lblOutput);

		txtOutput = new JTextField();
		txtOutput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtOutput.setBounds(165, 159, 131, 20);
		contentPane.add(txtOutput);
		txtOutput.setColumns(10);

		lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDescription.setBounds(28, 204, 71, 14);
		contentPane.add(lblDescription);

		txtDescription = new JTextArea();
		txtDescription.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDescription.setBounds(165, 201, 242, 60);
		contentPane.add(txtDescription);
		txtDescription.setColumns(10);
		txtDescription.setLineWrap(true);

		cmbItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = cmbItem.getSelectedItem().toString();
				if (item.equals("WAIT") || item.equals("OPEN_BROWSER") || item.equals("CLOSE_BROWSER")
						|| item.equals("PAGE_LOAD_TIMEOUT") || item.equals("MAXIMIZE_WINDOW")) {
					cmbObject.setEnabled(false);
				} else {
					cmbObject.setEnabled(true);
				}
			}
		});

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input, item;
				try {
					//input = txtInput.getText().toString().trim();
					input = txtInput.getText().toString();
					item = cmbItem.getSelectedItem().toString();
					if (input.equals("") && item.equals("SENDKEY")) {
						txtInput.requestFocus();
						JOptionPane.showMessageDialog(null, "You must fill input field", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (input.trim().equals("") && item.equals("WAIT")) {
						txtInput.requestFocus();
						JOptionPane.showMessageDialog(null, "You must fill input field", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (input.trim().equals("") && item.equals("OPEN_BROWSER")) {
						txtInput.requestFocus();
						JOptionPane.showMessageDialog(null, "You must fill input field", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Bad input data", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (objectCurrent == null) {
					System.out.println("objectcurrent = " + objectCurrent);
					objectCurrent = objectList.get(0);
				}
				String query;
				if (cmbObject.isEnabled()) {
					query = "INSERT INTO `mystudio`.`testscript` (`ITEM`,`INPUT`,`OUTPUT`,`DESCRIPTION`,`ID_TESTCASE`,`ID_OBJECT`) VALUES ( "
							+ '"' + cmbItem.getSelectedItem().toString() + '"' + "," + '"' + input + '"' + "," + '"'
							+ txtOutput.getText() + '"' + "," + '"' + txtDescription.getText() + '"' + "," + ID_TESTCASE
							+ "," + objectCurrent.getId() + ")";
				} else {
					query = "INSERT INTO `mystudio`.`testscript` (`ITEM`,`INPUT`,`OUTPUT`,`DESCRIPTION`,`ID_TESTCASE`,`ID_OBJECT`) VALUES ( "
							+ '"' + cmbItem.getSelectedItem().toString() + '"' + "," + '"' + input + '"' + "," + '"'
							+ txtOutput.getText() + '"' + "," + '"' + txtDescription.getText() + '"' + "," + ID_TESTCASE
							+ "," + 1 + ")";
				}

				System.out.println(query);
				MySQLConnect.executeSQLQuery(query, "Insert");

				frmMain.idSelect = 0;
				frmMain.indexSelect = -1;

			}
		});
		btnAdd.setBounds(74, 299, 89, 23);
		contentPane.add(btnAdd);

		btnCancer = new JButton("Cancer");
		btnCancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMain.idSelect = 0;
				frmMain.indexSelect = -1;
				dispose();
			}
		});
		btnCancer.setBounds(229, 299, 89, 23);
		contentPane.add(btnCancer);

	}
}
