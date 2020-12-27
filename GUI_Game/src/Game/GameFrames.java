package Game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameFrames extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrames frame = new GameFrames();
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
	public GameFrames() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 945, 668);
		setResizable(false);

		int opt = 0;
		while (!(opt > 0 && opt <= 3)) {
			try {
				opt = Integer.parseInt(JOptionPane.showInputDialog(contentPane, "Select Level 1,2, or 3"));
			} catch (Exception e) {

			}
		}
		if (opt == 1) {
			contentPane = new layout(8000, 500);
		} else if (opt == 2) {
			contentPane = new layout(6500, 200);
		} else {
			contentPane = new layout(5000, 100);
		}

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	}

}
