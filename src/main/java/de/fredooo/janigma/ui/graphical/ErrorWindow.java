package de.fredooo.janigma.ui.graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Frederik Dennig
 * @since 2013-12-14
 * @version 0.1a (last revised 2013-12-15)
 */
@SuppressWarnings("serial")
public class ErrorWindow extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ErrorWindow(String text) {
		setTitle("Error");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 250, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblText = new JLabel(text);
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setBounds(10, 11, 214, 56);
		contentPane.add(lblText);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(70, 78, 89, 23);
		contentPane.add(btnClose);
		btnClose.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}
	
}
