package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import connectmysql.MySQLConnect;
import model.Objects;
import model.TestCase;
import model.TestScript;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;

public class frmUpdateTestScript extends JFrame {

	private JPanel contentPane;
	private JTextField txtInput;
	private JTextField txtOutput;
	private JLabel lblChooseItem;
	private JComboBox cmbItem;
	private JLabel lblObject;
	private JComboBox cmbObject;
	private JLabel lblInput;
	private JLabel lblOutput;
	private JLabel lblDescription;
	private JButton btnUpdate;
	private JButton btnCancer;

	private Objects objectCurrent = new Objects();
	private ArrayList<Objects> objectList = new ArrayList<Objects>();
	private JTextArea txtDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmUpdateTestScript frame = new frmUpdateTestScript();
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

	public frmUpdateTestScript() {
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

		Connection conn = MySQLConnect.getConnection();

		// Tạo combobox cho danh sách object
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

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query;
				String input, item;
				try {
					input = txtInput.getText().toString().trim();
					item = cmbItem.getSelectedItem().toString();
					if (input.equals("") && item.equals("SENDKEY")) {
						txtInput.requestFocus();
						JOptionPane.showMessageDialog(null, "You must fill input field", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (input.equals("") && item.equals("WAIT")) {
						txtInput.requestFocus();
						JOptionPane.showMessageDialog(null, "You must fill input field", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (input.equals("") && item.equals("OPEN_BROWSER")) {
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
				int ID_OBJECT;
				if (objectCurrent != null) {
					ID_OBJECT = (cmbObject.isEnabled()) ? objectCurrent.getId() : 1;
					query = "UPDATE `mystudio`.`testscript` SET `ITEM` =" + '"' + item.trim() + '"' + ',' + "`INPUT` = "
							+ '"' + input.trim() + '"' + ',' + "`OUTPUT` = " + '"' + txtOutput.getText().trim() + '"'
							+ ',' + "`DESCRIPTION` = " + '"' + txtDescription.getText().trim() + '"' + ','
							+ "ID_OBJECT =" + ID_OBJECT + " WHERE `ID` = " + frmMain.idSelect;
					System.out.println(query);
				}
				// Nếu không chọn ở combo object
				else {
					if (cmbObject.isEnabled()) {
						query = "UPDATE `mystudio`.`testscript` SET `ITEM` =" + '"' + item.trim() + '"' + ','
								+ "`INPUT` = " + '"' + input.trim() + '"' + ',' + "`OUTPUT` = " + '"'
								+ txtOutput.getText().trim() + '"' + ',' + "`DESCRIPTION` = " + '"'
								+ txtDescription.getText().trim() + '"' + " WHERE `ID` = " + frmMain.idSelect;
					} else {
						query = "UPDATE `mystudio`.`testscript` SET `ITEM` =" + '"' + item.trim() + '"' + ','
								+ "`INPUT` = " + '"' + input.trim() + '"' + ',' + "`OUTPUT` = " + '"'
								+ txtOutput.getText().trim() + '"' + ',' + "`DESCRIPTION` = " + '"'
								+ txtDescription.getText().trim() + '"' + ',' + "ID_OBJECT =" + 1 + " WHERE `ID` = "
								+ frmMain.idSelect;
					}
					System.out.println(query);
				}

				MySQLConnect.executeSQLQuery(query, "Update");
				frmMain.idSelect = 0;
				frmMain.indexSelect = -1;
			}
		});
		btnUpdate.setBounds(74, 299, 89, 23);
		contentPane.add(btnUpdate);

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

		// ======================================================================================//

		// Get data from frmMain
		TestScript testscript = new TestScript();

		query = "SELECT testscript.ID, testscript.ITEM, testscript.INPUT, testscript.OUTPUT, testscript.DESCRIPTION, testscript.ID_TESTCASE,testscript.RESULT, testscript.ID_OBJECT, object.NAME\r\n"
				+ " FROM mystudio.testscript inner join mystudio.object ON testscript.ID_OBJECT = object.ID AND testscript.ID = "
				+ frmMain.idSelect;

		try {
			st = conn.createStatement();
			res = st.executeQuery(query);

			res.next();
			testscript = new TestScript(res.getInt("ID"), res.getString("ITEM"), res.getString("INPUT"),
					res.getString("OUTPUT"), res.getString("DESCRIPTION"), res.getInt("ID_TESTCASE"),
					res.getString("RESULT"), new Objects(res.getInt("ID_OBJECT"), res.getString("NAME")));

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (testscript.getItem().toString() != null) {
			cmbItem.setSelectedItem(testscript.getItem().trim());
		}
		if (testscript.getObjects().getId() != 1 && testscript.getObjects().getName() != null) {
			cmbObject.setSelectedItem(testscript.getObjects().getName().trim());
		}
		txtInput.setText(testscript.getInput().toString());
		txtOutput.setText(testscript.getOutput().toString());

		txtDescription = new JTextArea();
		txtDescription.setBounds(165, 204, 259, 83);
		contentPane.add(txtDescription);
		txtDescription.setLineWrap(true);
	}

}
