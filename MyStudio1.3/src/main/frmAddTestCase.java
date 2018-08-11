package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connectmysql.MySQLConnect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class frmAddTestCase extends JFrame {

	private JPanel contentPane;
	private JTextField txtTestCaseName;
	
	private JLabel lblTestCaseName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAddTestCase frame = new frmAddTestCase();
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
	public frmAddTestCase() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTestCaseName = new JLabel("Test case name");
		lblTestCaseName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTestCaseName.setBounds(10, 42, 110, 68);
		contentPane.add(lblTestCaseName);
		
		txtTestCaseName = new JTextField();
		txtTestCaseName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtTestCaseName.setColumns(10);
		txtTestCaseName.setBounds(130, 48, 274, 45);
		contentPane.add(txtTestCaseName);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name, url;
				try {
					name = txtTestCaseName.getText().toString().trim();
					if(name.equals("")) {
						txtTestCaseName.requestFocus();
						JOptionPane.showMessageDialog(null, "You must input testcase name", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Bad testcase name", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String query = "INSERT INTO `mystudio`.`testcase`(`NAME`,`ID_PROJECT`)VALUES ( '"
						+ name + "' , " +frmMain.indexSelectProject +")";
				MySQLConnect.executeSQLQuery(query, "Insert");
			}
		});
		btnAdd.setBounds(115, 227, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(255, 227, 89, 23);
		contentPane.add(btnCancel);
	}
}
