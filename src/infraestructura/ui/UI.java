package infraestructura.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import domain.portsin.DomainException;
import domain.portsin.GestorConcurso;

public class UI extends JFrame {

	private JPanel            contentPane;
	private JLabel            lblName;
	private JTextField        txtName;
	private JLabel            lblLastName;
	private JTextField        txtLastName;
	private JLabel            lblId;
	private JTextField        txtId;
	private JLabel            lblPhone;
	private JTextField        txtPhone;
	private JLabel            lblEmail;
	private JTextField        txtEmail;
	private JComboBox<String> comboBox;
	private JButton           btnOk;
	private JLabel            lblCompetition;
	private GestorConcurso    concursos;

	public UI(GestorConcurso concursos) {
		var frame = new JFrame("Inscription to Competition");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 451, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		this.concursos = concursos;
		formElements();
		layouts();
		frame.setVisible(true);

	}

	private void formElements() {
		lblName = new JLabel("Nombre:");
		txtName = new JTextField();
		txtName.setColumns(10);
		lblLastName = new JLabel("Apellido:");
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		lblId = new JLabel("Dni:");
		txtId = new JTextField();
		txtId.setColumns(10);
		lblPhone = new JLabel("Telefono:");
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		lblEmail = new JLabel("Email:");
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnOk.setEnabled(false);
					saveInscription();
					btnOk.setEnabled(true);
				} catch (DomainException e1) {
					JOptionPane.showMessageDialog(contentPane, e1);
				}
			}
		});
		lblCompetition = new JLabel("Concurso:");
		comboBox       = new JComboBox<String>();
		todosLosConcursos();
	}

	private void todosLosConcursos() {
		for (String nombre : concursos.listaConcursos()) {
			comboBox.addItem(nombre);
		}
	}

	private void saveInscription() {

		concursos.cargarParticipante(txtName.getText(), txtLastName.getText(), txtPhone.getText(), txtId.getText(),
				txtEmail.getText(), comboBox.getName());

		JOptionPane.showMessageDialog(this.contentPane, "Incripcion finalizada");

	}

	private void layouts() {
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblLastName)
								.addComponent(lblId).addComponent(lblPhone).addComponent(lblEmail).addComponent(lblName)
								.addComponent(lblCompetition))
						.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtEmail, Alignment.TRAILING).addComponent(txtPhone, Alignment.TRAILING)
								.addComponent(txtId, Alignment.TRAILING).addComponent(txtLastName, Alignment.TRAILING)
								.addComponent(txtName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 298,
										Short.MAX_VALUE)))
						.addComponent(btnOk, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 86,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblLastName).addComponent(
						txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblId).addComponent(txtId,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblPhone)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblEmail))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCompetition))))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnOk).addContainerGap(67, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}
