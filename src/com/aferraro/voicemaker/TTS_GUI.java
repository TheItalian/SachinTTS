package com.aferraro.voicemaker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TTS_GUI {

	private JFrame frame;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TTS_GUI window = new TTS_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TTS_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("SKAFLG TTS Engine");
		frame.setResizable(false);
		frame.setBounds(100, 100, 400, 105);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnViewCreditsabout = new JButton("View Credits/About");
		btnViewCreditsabout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnViewCreditsabout.setBounds(10, 42, 247, 23);
		panel.add(btnViewCreditsabout);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 247, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Edit Settings");
		btnNewButton.setBounds(267, 42, 117, 23);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Play Text");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VoiceMaker VM = new VoiceMaker();
				String text = textField.getText();
				VM.play(text);
			}
		});
		btnNewButton_1.setBounds(267, 10, 117, 23);
		panel.add(btnNewButton_1);
	}
}
