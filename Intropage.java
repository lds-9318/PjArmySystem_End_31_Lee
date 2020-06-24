package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Intropage {

	private JFrame frmLogin;

	public Intropage() {
		initialize();
	}

	private void initialize() {
		frmLogin = new JFrame();

		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Intropage.class.getResource("/images/AmyIcon.jpg")));
		frmLogin.setTitle("Run");
		frmLogin.setBounds(100, 100, 300, 150);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setLocationRelativeTo(null);

		MainTable Ml = new MainTable();

		Ml.setVisible(false);
		JButton btnNewButton = new JButton("Login");
		JTextField Jtf = new JTextField();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArmyDAO da = new ArmyDAO();
				String data = Jtf.getText() + "�� �����߽��ϴ�.";
				
				if (da.getIdByCheck(Jtf.getText())) {
					System.out.println("�α��ν���");
				} else {
					System.out.println("����");
					frmLogin.setVisible(false);
					Ml.setVisible(true);
					JOptionPane.showMessageDialog(frmLogin, data);
				}
				
				
				

			}
		});
		btnNewButton.setBounds(150, 70, 100, 30);
		Jtf.setBounds(50, 70, 90, 30);
		frmLogin.getContentPane().add(Jtf);
		frmLogin.getContentPane().add(btnNewButton);
		frmLogin.setVisible(true);
		Label label = new Label("�� ���� ��ȸ �ý���");
		Label label2 = new Label("���� :");

		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("���� ���", Font.BOLD, 24));
		label.setBounds(10, 10, 260, 54);
		label2.setBounds(10, 70, 40, 30);
		label2.setForeground(Color.DARK_GRAY);
		label2.setFont(new Font("���� ���", Font.BOLD, 12));
		frmLogin.getContentPane().add(label);
		frmLogin.getContentPane().add(label2);

	}

}