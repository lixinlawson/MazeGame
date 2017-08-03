/**
 * This frame is used to display the information about the game.
 */
package ca.MazeGUI.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FrameForAboutGame extends JFrame {

	public FrameForAboutGame() {
		super("About Game");
		setLayout(new FlowLayout());
		add(makeLeftPanel());
		add(makeRightPanel());

		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private JPanel makeLeftPanel() {
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		left.add(makeIconLabel(), BorderLayout.CENTER);
		return left;
	}

	private JPanel makeRightPanel() {
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		right.add(makeInterPanelForRightPanel(), BorderLayout.CENTER);
		right.add(makeOKButton(), BorderLayout.SOUTH);
		return right;
	}

	private JPanel makeInterPanelForRightPanel() {
		JPanel interPanel = new JPanel();
		interPanel.setLayout(new BoxLayout(interPanel, BoxLayout.PAGE_AXIS));
		interPanel.add(makeAuthorLabel());
		interPanel.add(makeImgSourceLabel());
		return interPanel;
	}

	private JLabel makeIconLabel() {
		ImageIcon cheese = new ImageIcon("icons\\cheese.png");
		return new JLabel(cheese);
	}

	private JLabel makeAuthorLabel() {
		return new JLabel("Game written by Lawson Li, 2014");
	}

	private JLabel makeImgSourceLabel() {
		return new JLabel("Images by Lawson Li, 2014");
	}

	private JButton makeOKButton() {
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		return ok;
	}

}
