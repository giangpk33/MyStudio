package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connectmysql.MySQLConnect;
import model.Objects;
import model.TestScript;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class frmAddObject extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JComboBox cmbMethodType;
	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnUpdate_1;
	private JButton btnDelete_1;
	private JTextArea txtObjectLocator;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAddObject frame = new frmAddObject(1, 2);
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
	/*
	 * public frmAddObject() {
	 * 
	 * }
	 */
	public frmAddObject(int id_project) {
		final int ID_PROJECT = id_project;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] method = { EnumTypes.METHODTYPES.CLASS.toString(), EnumTypes.METHODTYPES.ID.toString(),
				EnumTypes.METHODTYPES.XPATH.toString(), EnumTypes.METHODTYPES.CSS.toString() };
		DefaultComboBoxModel<String> combomethod = new DefaultComboBoxModel<String>(method);
		// Thêm ở đây
		cmbMethodType = new JComboBox(combomethod);
		cmbMethodType.setBounds(133, 31, 189, 26);
		contentPane.add(cmbMethodType);

		txtName = new JTextField();
		txtName.setBounds(133, 98, 189, 37);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtObjectLocator = new JTextArea();
		txtObjectLocator.setBounds(133, 163, 189, 37);
		contentPane.add(txtObjectLocator);
		txtObjectLocator.setLineWrap(true);

		btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String name, objectlocator;
				try {
					name = txtName.getText().toString().trim();
					if (name.equals("")) {
						txtName.requestFocus();
						JOptionPane.showMessageDialog(null, "You must input object name", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Bad object name", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					objectlocator = txtObjectLocator.getText().toString().trim();
					if (objectlocator.equals("")) {
						txtObjectLocator.requestFocus();
						JOptionPane.showMessageDialog(null, "You must input object locator", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "You must input object locator", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String query = "INSERT INTO `mystudio`.`object`(`METHODTYPE`,`NAME`,`OBJECTLOCATORS`,`ID_PROJECT`)VALUES ( "
						+ '"' + cmbMethodType.getSelectedItem().toString() + '"' + ',' + '"' + name + '"' + ',' + '"'
						+ objectlocator + '"' + ',' + ID_PROJECT + ')';
				// System.out.println(query);
				MySQLConnect.executeSQLQuery(query, "Insert");
			}
		});
		btnAdd.setBounds(39, 210, 89, 23);
		contentPane.add(btnAdd);

		btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(177, 210, 89, 23);
		contentPane.add(btnCancel);

		JLabel lblMethodType = new JLabel("Method type");
		lblMethodType.setBounds(10, 31, 89, 26);
		contentPane.add(lblMethodType);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 103, 53, 26);
		contentPane.add(lblName);

		JLabel lblObjectLocator = new JLabel("Object locator");
		lblObjectLocator.setBounds(10, 148, 89, 26);
		contentPane.add(lblObjectLocator);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public frmAddObject(int id_project, int id_object) {
		final int ID_PROJECT = id_project;
		final int ID_OBJECT = id_object;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] method = { EnumTypes.METHODTYPES.CLASS.toString(), EnumTypes.METHODTYPES.ID.toString(),
				EnumTypes.METHODTYPES.XPATH.toString(), EnumTypes.METHODTYPES.CSS.toString() };
		DefaultComboBoxModel<String> combomethod = new DefaultComboBoxModel<String>(method);
		// Thêm ở đây
		cmbMethodType = new JComboBox(combomethod);
		cmbMethodType.setBounds(133, 31, 189, 26);
		contentPane.add(cmbMethodType);
		String[] action = { EnumTypes.ACTIONTYPES.CLICK.toString(), EnumTypes.ACTIONTYPES.OPEN_BROWSER.toString(),
				EnumTypes.ACTIONTYPES.FIND.toString(), EnumTypes.ACTIONTYPES.SENDKEY.toString(),
				EnumTypes.ACTIONTYPES.SUBMIT.toString(), EnumTypes.ACTIONTYPES.VERIFY_EQUAL.toString(),
				EnumTypes.ACTIONTYPES.PAGE_LOAD_TIMEOUT.toString(), EnumTypes.ACTIONTYPES.CHECK_VISIBLE.toString(),
				EnumTypes.ACTIONTYPES.CLOSE_BROWSER.toString(), EnumTypes.ACTIONTYPES.WAIT.toString() };
		DefaultComboBoxModel<String> comboaction = new DefaultComboBoxModel<String>(action);

		txtName = new JTextField();
		txtName.setBounds(133, 98, 189, 37);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtObjectLocator = new JTextArea();
		txtObjectLocator.setBounds(133, 155, 189, 45);
		contentPane.add(txtObjectLocator);
		txtObjectLocator.setLineWrap(true);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name, objectlocator;
				try {
					name = txtName.getText().toString().trim();
					if (name.equals("")) {
						txtName.requestFocus();
						JOptionPane.showMessageDialog(null, "You must input object name", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Bad object name", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					objectlocator = txtObjectLocator.getText().toString().trim();
					if (objectlocator.equals("")) {
						txtObjectLocator.requestFocus();
						JOptionPane.showMessageDialog(null, "You must input object locator", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "You must input object locator", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String query = "UPDATE `mystudio`.`object` SET `METHODTYPE` = " + '"'
						+ cmbMethodType.getSelectedItem().toString().trim() + '"' + ',' + "`NAME` = " + '"' + name + '"'
						+ ',' + "`OBJECTLOCATORS` = " + '"' + objectlocator + '"' + " WHERE `ID` = " + ID_OBJECT;

				// System.out.println(query);
				MySQLConnect.executeSQLQuery(query, "Update");

			}
		});
		btnUpdate.setBounds(39, 210, 89, 23);
		contentPane.add(btnUpdate);

		btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(284, 212, 89, 23);
		contentPane.add(btnCancel);

		btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String query = "DELETE FROM `mystudio`.`object` WHERE `ID` = " + ID_OBJECT;
				MySQLConnect.executeSQLQuery(query, "Delete");
				dispose();
			}
		});
		btnDelete.setBounds(156, 212, 89, 23);
		contentPane.add(btnDelete);

		JLabel lblMethodType = new JLabel("Method type");
		lblMethodType.setBounds(10, 31, 89, 26);
		contentPane.add(lblMethodType);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 103, 53, 26);
		contentPane.add(lblName);

		JLabel lblObjectLocator = new JLabel("Object locator");
		lblObjectLocator.setBounds(10, 148, 89, 26);
		contentPane.add(lblObjectLocator);

		// Update information
		Objects obj = new Objects();
		Connection conn = MySQLConnect.getConnection();

		String query = "SELECT * FROM mystudio.object where mystudio.object.ID = " + id_object;
		Statement st;
		ResultSet res;

		try {
			st = conn.createStatement();
			res = st.executeQuery(query);

			res.next();
			obj = new Objects(res.getInt("ID"), res.getString("METHODTYPE"), res.getString("OBJECTLOCATORS"),
					res.getString("NAME"), res.getInt("ID_PROJECT"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		cmbMethodType.setSelectedItem(obj.getMethodType().toString());

		if (obj.getName() != null) {
			txtName.setText(obj.getName().toString());
		}
		if (obj.getObjectLocators() != null) {
			txtObjectLocator.setText(obj.getObjectLocators().toString());
		}
		// JOptionPane.showMessageDialog(null, obj.getData().toString());
		// JOptionPane.showMessageDialog(null, obj.getActionType().toString());
	}
}
