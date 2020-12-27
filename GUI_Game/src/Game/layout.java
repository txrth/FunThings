package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class layout extends JPanel implements ActionListener, KeyListener {

	/**
	 * Create the panel.
	 */

	JLabel player, desk, timer, time, GameOver, lbl_items, lbl_itemsLeft, Monster = new JLabel(), Start, win, endPic;
	public int times = 10000, placeTime = 500;
	int Assign, holding = -1, height = 600, width = 900, counter = 0, currentItems = 0;
	JLabel items[] = new JLabel[ItemList.itemList.length];
	Timer t = new Timer(5, this);
	double x = 0, y = 0, velx = 0, vely = 0, mx = width / 2, my = height / 2, velmx = 0, velmy = 0;
	boolean started = false, EndGame = false;

	public layout(int times, int placeTime) {
		this.times = times;
		this.placeTime = placeTime;

		background();
		setplayer();
		placeItems();

		// height = getSize().getHeight();
		// width = getSize().getWidth();
		setdesk();

		GameOver = new JLabel("GAME OVER :(");
		GameOver.setFont(new Font("Arial Black", Font.BOLD, 70));
		GameOver.setForeground(Color.BLACK);
		GameOver.setHorizontalAlignment(SwingConstants.CENTER);
		GameOver.setBounds(0, 500, 900, 100);
		GameOver.setVisible(false);
		add(GameOver);

		lbl_items = new JLabel("Items left:");
		lbl_items.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_items.setForeground(Color.GRAY);
		lbl_items.setFont(new Font("Arial", Font.BOLD, 24));
		lbl_items.setBounds(650, 40, 140, 50);
		add(lbl_items);

		lbl_itemsLeft = new JLabel(String.valueOf(Assign));
		lbl_itemsLeft.setForeground(Color.GRAY);
		lbl_itemsLeft.setFont(new Font("Arial", Font.BOLD, 24));
		lbl_itemsLeft.setBounds(800, 40, 50, 50);
		add(lbl_itemsLeft);

		Start = new JLabel("Start");
		Start.setHorizontalAlignment(SwingConstants.CENTER);
		Start.setForeground(Color.CYAN);
		Start.setFont(new Font("Arial Black", Font.BOLD, 70));
		Start.setBounds(263, 236, 370, 134);
		add(Start);

		win = new JLabel("YOU WIN! :)");
		win.setFont(new Font("Arial Black", Font.BOLD, 96));
		win.setHorizontalAlignment(SwingConstants.CENTER);
		win.setForeground(Color.CYAN);
		win.setBounds(0, 500, 900, 100);
		win.setVisible(false);
		add(win);

		endPic = new JLabel("");
		endPic.setHorizontalAlignment(SwingConstants.CENTER);
		endPic.setBounds(200, 0, 500, 500);
		endPic.setVisible(false);
		add(endPic);

		actions();

	}

	void actions() {
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	void placeItems() {
		for (int i = 0; i < ItemList.itemList.length; i++) {
			items[i] = new JLabel();
			int val2 = ramdomPlacement();
			// System.out.println(val2);
			items[i].setIcon(ItemList.getImageIcon(i, 50, 50));
			items[i].setBounds(val2 / 1000, val2 - (val2 / 1000) * 1000, 50, 50);

			add(items[i]);
			items[i].setVisible(false);
		}
		Assign = items.length;
	}

	int ramdomPlacement() {
		int vx, vy;
		vx = (int) (Math.random() * (width - 200)) + 50;
		vy = (int) (Math.random() * (height - 200)) + 50;

		if (vx >= (width / 2 - 200 / 2) && vx <= width / 2 + 200 / 2 && vy >= 0 && vy < 100 &&

				vx <= 100 && vx >= width - 100 && vy <= 100 && vy >= height - 150) {
			int val = ramdomPlacement();
			vx = val / 1000;
			vy = val - vx * 1000;
		}
		// System.out.println("this" + vx + " , " + vy);
		return vx * 1000 + vy;
	}

	void setplayer() {
		player = new JLabel();
		Image image = null;
		try {
			image = ImageIO.read(layout.class.getResource("/Game/player.png"));
			player.setIcon(new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			System.out.println("404");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		player.setBounds(0, 0, 50, 50);
		add(player);

		try {
			Monster.setIcon(new ImageIcon(ImageIO.read(layout.class.getResource("/Game/monster.png"))
					.getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			System.out.println("404");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Monster.setBounds((int) mx, (int) my, 50, 50);
		Monster.setEnabled(false);
		Monster.setVisible(false);
		add(Monster);
	}

	void setdesk() {
		desk = new JLabel("");
		Image image = null;
		try {
			image = ImageIO.read(layout.class.getResource("/Game/desk.png"));
			desk.setIcon(new ImageIcon(image.getScaledInstance(200, 100, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			System.out.println("404");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		desk.setBounds(width / 2 - 200 / 2, 0, 200, 100);
		add(desk);
	}

	void background() {
		setSize(new Dimension(900, 600));
		setBackground(new Color(65, 51, 225));
		setLayout(null);

		timer = new JLabel("Time left:");
		timer.setFont(new Font("Tahoma", Font.BOLD, 30));
		timer.setBounds(width - 150 - 100, 0, 150, 50);
		add(timer);
		time = new JLabel(String.valueOf(times));
		time.setFont(new Font("Tahoma", Font.BOLD, 30));
		time.setBounds(800, 0, 100, 50);
		add(time);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if (!started) {
			Start.setVisible(false);
			started = true;
		}
		if (code == KeyEvent.VK_UP) {
			vely = -1.5;
		}

		if (code == KeyEvent.VK_DOWN) {
			vely = 1.5;
		}

		if (code == KeyEvent.VK_LEFT) {
			velx = -1.5;

		}

		if (code == KeyEvent.VK_RIGHT) {
			velx = 1.5;
		}

		if (code == KeyEvent.VK_SPACE && holding == -1) {
			holding = getNearItem();
			if (EndGame) {
				if (x >= mx - 50 && x <= x + 50 && y >= my - 50 && y <= my + 50) {
					holding = -2;
				}
			}
		} else if (code == KeyEvent.VK_SPACE && holding > -1) {
			int gx = items[holding].getX();
			int gy = items[holding].getY();
			if (gx >= 350 && gx <= 350 + 200 && gy < 100) {
				if (items[holding].isEnabled()) {
					items[holding].setVisible(false);
					items[holding].setEnabled(false);
					remove(items[holding]);
					Assign--;
					currentItems--;
					counter = 0;
					lbl_itemsLeft.setText(String.valueOf(Assign));
					if (Assign == 0) {
						endGame();
						lbl_itemsLeft.setVisible(false);
						lbl_items.setVisible(false);
						EndGame = true;
					}
				}
			}
			holding = -1;
		} else if (code == KeyEvent.VK_SPACE) {
			if (mx >= 350 && mx <= 350 + 200 && my < 100) {

				player.setVisible(false);
				desk.setVisible(false);
				timer.setVisible(false);
				time.setVisible(false);
				lbl_items.setVisible(false);
				lbl_itemsLeft.setVisible(false);
				Monster.setVisible(false);
				win.setVisible(true);
				endPic.setVisible(true);
				setBackground(new Color(0, 204, 0));

				try {
					endPic.setIcon(new ImageIcon(ImageIO.read(layout.class.getResource("/Game/winPic.png"))
							.getScaledInstance(endPic.getWidth(), endPic.getHeight(), Image.SCALE_DEFAULT)));
				} catch (IOException es) {
					System.out.println("404");
				}
				t.stop();
			}
			holding = -1;
		}

	}

	private int getNearItem() {
		// TODO Auto-generated method stub

		for (int i = 0; i < ItemList.itemList.length; i++) {
			int ix = items[i].getLocation().x;
			int iy = items[i].getLocation().y;
			if (x >= ix - 50 && x <= ix + 50 && y >= iy - 50 && y <= iy + 50) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		velx = 0;
		vely = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Clocate();
		System.out.println(currentItems);
		player.setLocation((int) (x), (int) (y));

		// ending

		if (counter == placeTime && currentItems < 5) {
			int u = ItemList.getRandom();
			if (u != -1) {
				items[u].setVisible(true);
				currentItems++;
				counter = 0;
			}
		}

		if (started) {
			counter++;
			times--;
			time.setText(String.valueOf(times));
		}
		if (times <= 1000) {
			time.setForeground(new Color(255, 0, 0));
		}

		if (holding > -1) {
			items[holding].setLocation((int) (x), (int) (y - 20));
		}

		if (holding == -2) {
			mx = x;
			my = y - 20;
			Monster.setLocation((int) mx, (int) my);
		}

		if (EndGame && holding == -1) {
			if (times % 100 == 0) {
				velmx = Math.round(Math.random() * 4) - 2;
				velmy = Math.round(Math.random() * 4) - 2;
			}
			mlocate();
			Monster.setLocation((int) mx, (int) my);
		}

		if (times == 0) {
			lost();
		}
	}

	void lost() {
		GameOver.setVisible(true);
		endPic.setVisible(true);
		player.setVisible(false);
		desk.setVisible(false);
		timer.setVisible(false);
		time.setVisible(false);
		lbl_items.setVisible(false);
		lbl_itemsLeft.setVisible(false);
		Monster.setVisible(false);
		endPic.setVisible(true);
		setBackground(new Color(172, 4, 4));
		for (int i = 0; i < ItemList.itemList.length; i++) {
			items[i].setVisible(false);
		}

		try {
			endPic.setIcon(new ImageIcon(ImageIO.read(layout.class.getResource("/Game/losePic.png"))
					.getScaledInstance(endPic.getWidth(), endPic.getHeight(), Image.SCALE_DEFAULT)));
		} catch (IOException es) {
			System.out.println("404");
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		t.stop();
	}

	void mlocate() {
		double nx = (mx + velmx), ny = (my + velmy);
		if (nx >= 0 && ny >= 0 && nx <= width && ny <= height
				&& !(nx >= (width / 2 - 200 / 2) && nx <= width / 2 + 200 / 2 && ny >= 0 && ny < 100)) {
			mx = nx;
			my = ny;
		}
	}

	void Clocate() {
		double nx = (x + velx), ny = (y + vely);
		if (nx >= 0 && ny >= 0 && nx <= width && ny <= height) {
			x = nx;
			y = ny;
		}
	}

	void endGame() {
		setBackground(new Color(127, 0, 255));
		Monster.setVisible(true);
		Monster.setEnabled(true);

	}
}

//HI FUTURE TIRTH YOU ARE STILL ANNOYING