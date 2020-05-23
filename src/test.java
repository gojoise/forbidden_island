import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;





class test {
	public static void main( String[]args )
	{
		Image image = null;
		Image [] images = new Image[4];
		for(int i=0;i<4;i++) {
			try {
				File fichier = new File("src/images/"+i+".png");
				image = ImageIO.read(fichier);
				images[i]=image;
				System.out.println(fichier.toString()+"   lu");
			} catch (IOException e) {
				System.out.println("pop");
			}
		}
			JFrame frame = new JFrame();
			frame.setSize(300, 300);
			frame.setLayout(new GridLayout());
			JLabel label = new JLabel(new ImageIcon(images[0]));
			JLabel label1 = new JLabel(new ImageIcon(images[1]));
			JLabel label2 = new JLabel(new ImageIcon(images[2]));
			JLabel label3 = new JLabel(new ImageIcon(images[3]));
			
			frame.add(label);
			frame.add(label1);
			frame.add(label2);
			frame.add(label3);
	
			frame.setVisible(true);
		
	}
}