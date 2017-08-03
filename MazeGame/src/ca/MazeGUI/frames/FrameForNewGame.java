/**
 *This a frame is using to ask user to confirm if they want to start a new game.
 */
package ca.MazeGUI.frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.MazeGUI.MazeGameGUI;
import ca.MazeGame.MazeGameForGUI;

@SuppressWarnings("serial")
public class FrameForNewGame extends JFrame {

	private MazeGameGUI currentGame;

	public FrameForNewGame(MazeGameGUI currentGame) {
		super("New Game ?");
		this.currentGame = currentGame;
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
		right.add(add(makeWarningLabel()), BorderLayout.CENTER);
		right.add(makeInterPanelForRightPanel(), BorderLayout.SOUTH);
		return right;
	}

	private JPanel makeInterPanelForRightPanel() {
		JPanel interPanel = new JPanel();
		interPanel.setLayout(new FlowLayout());
		interPanel.add(makeYesButton());
		interPanel.add(makeNoButton());
		return interPanel;
	}

	private JLabel makeIconLabel() {
		return new JLabel(new ImageIcon("icons\\cat.png"));
	}

	private JLabel makeWarningLabel() {
		return new JLabel("Do you really want to give up the current game?");
	}

	private Component makeYesButton() {
		JButton yes = new JButton("Yes");
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentGame.dispose();
				dispose();
				new MazeGameGUI(new MazeGameForGUI());
			}
		});
		return yes;
	}

	private JButton makeNoButton() {
		JButton no = new JButton("No");
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		return no;
	}
}
