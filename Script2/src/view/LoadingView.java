package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import load.LoadDataToStaging;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Color;

public class LoadingView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jlblNotify, icon;
	private JButton btnOk;
	private LoadDataToStaging loadDataToStaging;

	public LoadingView() {
		loadDataToStaging  = new LoadDataToStaging();
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panelNotify = new JPanel();
		getContentPane().add(panelNotify);
		panelNotify.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		jlblNotify = new JLabel("Loading data to db staging...");
		panelNotify.add(jlblNotify);

		JPanel panelIcon = new JPanel();
		getContentPane().add(panelIcon);
		panelIcon.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		icon = new JLabel("");
		icon.setIcon(new ImageIcon(LoadingView.class.getResource("/image/load.gif")));
		panelIcon.add(icon);

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		panel.add(btnOk);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		setVisible(true);
		setTitle("Load data");
		setSize(260, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		switch (loadDataToStaging.loadData()) {
		case -1:
			error();
			break;
		case 0:
			finish();
			break;
		case 1:
			noData();
			break;

		default:
			break;
		}
		btnOk.setEnabled(true);
	}

	public void finish() {
		jlblNotify.setText("Finish");
		icon.setIcon(new ImageIcon(LoadingView.class.getResource("/image/ok.png")));
	}

	public void error() {
		jlblNotify.setText("Fail");
		icon.setIcon(new ImageIcon(LoadingView.class.getResource("/image/fail.png")));
	}

	public void noData() {
		jlblNotify.setText("No data");
		icon.setIcon(new ImageIcon(LoadingView.class.getResource("/image/no-data.png")));
	}

	public static void main(String[] args) {
		new LoadingView();
	}
}
