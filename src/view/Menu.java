package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Constants;

public class Menu implements ActionListener{
	
	JFrame frame;
	JButton button;
	JPanel panel;
	JLabel label;
	
	
	public Menu() {
		try {
			int newWidth = Constants.SCREEN_WIDTH;
            int newHeight = Constants.SCREEN_HEIGHT;
            frame = new JFrame("My JFrame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(newWidth, newHeight);

            JPanel panel = new JPanel(new BorderLayout());

            File originalFile = new File("src/assets/images/background.jpg");
            BufferedImage originalImage = ImageIO.read(originalFile);

            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

            File newFile = new File("src/assets/images/background.jpg");
            ImageIO.write(resizedImage, "jpg", newFile);

            JLabel background = new JLabel(new ImageIcon(newFile.getPath()));
            panel.add(background, BorderLayout.CENTER);

            JLabel logo = new JLabel(new ImageIcon("src/assets/images/logo.png"));
            background.setLayout(new BorderLayout());
            background.add(logo, BorderLayout.NORTH);

            ImageIcon buttonIcon = new ImageIcon("src/assets/images/play.jpg");

            Image buttonImage = buttonIcon.getImage();
            int width = buttonImage.getWidth(null) / 2;
            int height = buttonImage.getHeight(null) / 2;
            Image scaledButtonImage = buttonImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImage);

            button = new JButton(scaledButtonIcon);
            button.setOpaque(true);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.addActionListener(this);

            background.add(button);

            frame.add(panel);

            // frame.setLocationRelativeTo(null);
            frame.setLocation(450,200);
            frame.setResizable(false);
            frame.setVisible(true);

		} catch(IOException error) {
			System.err.println("Error resizing image: " + error.getMessage());
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == button) {
			frame.dispose();
			EventQueue.invokeLater(()-> {
				View view = new View();
				view.setVisible(true);
			});
		}
	}
	
}
