package gk1_zad1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Transforms2D extends JPanel { 

	private class Display extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.translate(300,300);  // Moves (0,0) to the center of the display.
			int whichTransform = transformSelect.getSelectedIndex();

			switch (whichTransform) {
			case 0:
				break;
			case 1:
				g2.scale(0.5, 0.5);
				break;
			case 2:
				g2.rotate(Math.toRadians(45));
				break;
				
			case 3:
				g2.scale(-0.5, 0.85);
				g2.rotate(Math.toRadians(180));
				break;
				
			case 4:
				g2.shear(0.35, 0);
				break;
				
			case 5:
				g2.translate(0,-225);
				g2.scale(1, 0.5);
				break;
			case 6:
				g2.shear(0, -0.35);
				g2.rotate(Math.toRadians(90));
				break;
			case 7:
				g2.scale(0.5, 0.85);
				g2.rotate(Math.toRadians(180));
				break;
			case 8:
				g2.translate(0,130);
				g2.rotate(Math.toRadians(30));
				g2.scale(1, 0.5);
				
				break;
			case 9:
				g2.translate(100,0);
				g2.shear(0, 0.35);
				g2.rotate(Math.toRadians(180));
				break;
				
			}
          int wymiar = 7; //side count
          int[] xpts = new int[wymiar]; 
          int[] ypts = new int[wymiar];
          for(int i = 1; i < wymiar+1; i++)
          {
              xpts[i-1] = (int) (200*Math.cos((2*Math.PI/wymiar)*i));
          }

          for(int i = 1; i < wymiar+1; i++)
          {
              ypts[i-1] = (int) (200*Math.sin((2*Math.PI/wymiar)*i));
          }

          Polygon septagon = new Polygon(xpts, ypts, wymiar);
          g2.setColor(Color.blue);
          
          g2.fillPolygon(septagon);
		  //g2.drawImage(pic, -200, -150, null); // Draw image with center at (0,0).
		}
	}

	private Display display;
	private BufferedImage pic;
	private JComboBox<String> transformSelect;

	public Transforms2D() throws IOException {
		pic = ImageIO.read(getClass().getClassLoader().getResource("shuttle.jpg"));
		display = new Display();
		display.setBackground(Color.YELLOW);
		display.setPreferredSize(new Dimension(600,600));
		transformSelect = new JComboBox<String>();
		transformSelect.addItem("None");
		for (int i = 1; i < 10; i++) {
			transformSelect.addItem("No. " + i);
		}
		transformSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.repaint();
			}
		});
		setLayout(new BorderLayout(3,3));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.add(new JLabel("Transform: "));
		top.add(transformSelect);
		add(display,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);
	}


	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("2D Transforms");
		window.setContentPane(new Transforms2D());
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
		window.setVisible(true);
	}

}