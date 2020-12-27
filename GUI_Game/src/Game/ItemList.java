package Game;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ItemList {

	static String itemList[] = { "/Game/armR.png", "/Game/Brain.png", "/Game/ears.png", "/Game/LegR.png",
			"/Game/ribs.png", "/Game/skull.png", "/Game/spine.png", "/Game/eyes.png" };
	static List<Integer> nums = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));

	static int getRandom() {
		if (nums.size() <= 0) {
			return -1;
		}
		int val = (int) (Math.round(Math.random() * (nums.size() - 1)));//
		System.out.println("this is " + val + nums);
		int num = nums.get(val);
		nums.remove(val);
		return num;
		// return 0;
	}

	static ImageIcon getImageIcon(int val, int x, int y) {
		System.out.println(val);
		try {
			Image image = ImageIO.read(layout.class.getResource(itemList[val]));
			return new ImageIcon(image.getScaledInstance(x, y, Image.SCALE_DEFAULT));
		} catch (IOException e) {
			System.out.println("404");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
