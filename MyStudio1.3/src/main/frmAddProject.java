package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connectmysql.MySQLConnect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class frmAddProject extends JFrame {

	private JPanel contentPane;
	public static JTextField txtNameProject;
	public static JTextField txtDescriptionProject;
    private JLabel lblName;
    private JLabel lblDescription;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAddProject frame = new frmAddProject();
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
	public frmAddProject() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblName = new JLabel("Name ");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblName.setBounds(26, 1, 79, 68);
		contentPane.add(lblName);
		
		lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDescription.setBounds(26, 80, 117, 46);
		contentPane.add(lblDescription);
		
		txtNameProject = new JTextField();
		txtNameProject.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtNameProject.setBounds(133, 25, 245, 20);
		contentPane.add(txtNameProject);
		txtNameProject.setColumns(10);
		
		txtDescriptionProject = new JTextField();
		txtDescriptionProject.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtDescriptionProject.setBounds(133, 87, 245, 68);
		contentPane.add(txtDescriptionProject);
		txtDescriptionProject.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name, description;
				try {
					name = txtNameProject.getText().toString().trim();
					if(name.equals("")) {
						txtNameProject.requestFocus();
						JOptionPane.showMessageDialog(null, "You must input project name", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Bad project name", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					description = txtDescriptionProject.getText().toString().trim();
					if(description.equals("")) {
						txtNameProject.requestFocus();
						JOptionPane.showMessageDialog(null, "You must input project description", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Bad project description", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String query = "INSERT INTO `mystudio`.`project` (`NAME`,`DESCRIPTION`) VALUES ( '"
						+ name + "' , '" + description + "')";
				MySQLConnect.executeSQLQuery(query, "Insert");
			}
		});
		btnAdd.setBounds(136, 187, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnCancer = new JButton("Cancel");
		btnCancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancer.setBounds(279, 187, 89, 23);
		contentPane.add(btnCancer);
	}
}
