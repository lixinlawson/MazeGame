/**
 * This frame is used to tell user how to play the game.
 */
package ca.MazeGUI.frames;

import java.awt.BorderLayout;
import java.awt.Color;
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
public class FrameForGameHelp extends JFrame {

	public FrameForGameHelp() {
		super("Game Help");
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
		interPanel.add(makeWelcomeLabel());
		interPanel.add(makePurposeLabel());
		interPanel.add(makeWarningLabel());
		interPanel.add(makeToWinLabel());
		interPanel.add(makeIntroLabel());
		interPanel.add(makeGoodLuckLabel());
		return interPanel;
	}

	private JLabel makeIconLabel() {
		return new JLabel(new ImageIcon("icons\\mouse.png"));
	}

	private JLabel makeWelcomeLabel() {
		return new JLabel("Welcome To The MAZE GAME!");
	}

	private JLabel makePurposeLabel() {
		return new JLabel("Your are the mouse trying to collect chesse.");
	}

	private JLabel makeWarningLabel() {
		return new JLabel(
				"Six cats are randomly exploring the maze to kill you! ");
	}

	private JLabel makeToWinLabel() {
		return new JLabel("Try to collect five cheese before you die.");
	}

	private JLabel makeIntroLabel() {
		JLabel intro = new JLabel("Use the ARROW KEYS ro move around.");
		intro.setForeground(Color.RED);
		return intro;
	}

	private JLabel makeGoodLuckLabel() {
		return new JLabel("Good luck! Have fun!");
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
