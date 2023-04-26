package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Level.Level;
import model.components.Ball;
import model.components.Brick;
import model.components.Paddle;
import utils.Constants;

public class Controller extends JPanel {

	Image backgroundImage;
	
	private Timer timer;
	private Ball ball;
	private Paddle paddle;
	private Brick[] bricks;
	private boolean inGame = true;
	private String message = "Game Over";
	private String Str_score = "Score: ";
	private int Score = 0;
	private int Combo_Score = 0;
	

	
	private String Str_Combo_Score = "";
	private int Timer_Display_Combo = 101;

	private int HealPoint = Constants.HEALPOINT;
	
	private int Level__game = 0;
	
	public Controller() {
		initial();
	}
	
	private void initial() {
		addKeyListener(new TAdapter());			
		/*+
		 * 
		 * Thêm một bộ lắng nghe sự kiện bàn phím mới,
		 *  được tạo bởi một đối tượng TAdapter, vào bảng điều khiển hiện tại.
		 */
		setFocusable(true);
		/*
		 * Thiết lập cho bảng điều khiển này có thể nhận được trọng tâm (focus)
		 *  và có thể nhận các sự kiện liên quan đến bàn phím.
		 */
		setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
		/*
		 * Đặt kích thước ưu tiên của bảng điều khiển hiện tại là rộng và cao
		 *  được xác định bởi các hằng số WIDTH và HEIGHT..
		 */
		
		gameInit();
	}
	
	
	private void gameInit() {

		try {
			backgroundImage = new ImageIcon("src/assets/images/background.jpg").getImage();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{

		
		
			bricks = new Brick[Constants.NUMBER_OF_BRICK];

			ball = new Ball();
			paddle = new Paddle();
			
			MapGerenation(Level__game);

			timer = new Timer(Constants.PERIOD, new GameCycle());		// mỗi "Commons.PERIOD" cập nhật GameCycle 1 lần											// run actionPerformed() của GameCycle
		
		
		}
		
	}
	
	private void MapGerenation(int level){
		int[] arr = Level.getLevel(level);

		int k = 0;

		for (int i = 0; i < 5; i++) {

			for (int j = 0; j < 6; j++) {
				if(arr[k] == 1){
																		//40*10  ->> 100 * 30
					bricks[k] = new Brick(j * 100 + 100, i * 30 + 50);
				}else{
					bricks[k] = new Brick(j * 100 + 100, i * 30 + 50);
					bricks[k].setDestroyed(true);
				}
				
				k++;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, null);

		var g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,		// bật khử răng cưa
							 RenderingHints.VALUE_ANTIALIAS_ON);	// đường vẽ mượt hơn nhưng tốn tài nguyên hơn

		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,			// setting KEY_RENDERING thành VALUE_RENDER_QUALITY
							 RenderingHints.VALUE_RENDER_QUALITY);	// vẽ high_quality nhưng tốn tài nguyên hơn


		//ingame = true     ->> khi game đang chạy
		if (inGame) {		

			drawObjects(g2d);

		} else {

			gameFinished(g2d);				// ->> end game

		}

		Toolkit.getDefaultToolkit().sync();			// hoàn tất các hoạt động vẽ trước đó trước khi vẽ cái khác
		
		/*		
		Việc sử dụng Toolkit.getDefaultToolkit().sync();
		có nghĩa là chúng ta đang yêu cầu hệ thống vẽ tất cả các đối tượng đã được lên lịch trước đó,
		trước khi tiếp tục vẽ các đối tượng khác. Điều này đảm bảo rằng tất cả các đối tượng sẽ được 
		vẽ trong thời gian ngắn nhất và hạn chế được tình trạng lag hay chậm trong việc vẽ các đối tượng trên giao diện.
		 */
	}
	
	private void drawObjects(Graphics2D g2d) {

		var font = new Font("Verdana", Font.BOLD, 18);
		// FontMetrics fontMetrics = this.getFontMetrics(font);
		
		g2d.setColor(Color.white);
		g2d.setFont(font);
		g2d.drawString("Level: " + (Level__game + 1), 350, 20);
		g2d.drawString(Str_score + Score, 650, 20);
		g2d.drawString("Live: " + HealPoint, 10, 20);

		g2d.setColor(Color.GREEN);
		g2d.drawString(Str_Combo_Score, 330, 300);

		
		g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(), ball.getImageWidth(), ball.getImageHeight(), this);
		g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(), paddle.getImageWidth(), paddle.getImageHeight(),
				this);

		for (int i = 0; i < Constants.NUMBER_OF_BRICK; i++) {

			if (!bricks[i].isDestroyed()) {

				g2d.drawImage(bricks[i].getImage(), bricks[i].getX(), bricks[i].getY(), bricks[i].getImageWidth(),
						bricks[i].getImageHeight(), this);
			}
		}
	}
	
	
	private void gameFinished(Graphics2D g2d) {

		var font = new Font("Verdana", Font.BOLD, 25);
		// FontMetrics fontMetrics = this.getFontMetrics(font);

		g2d.setColor(Color.white);
		g2d.setFont(font);
		g2d.drawString(message, 320, 200);

		g2d.drawString("Score: " + Score, 320, 250);
		// message "private String message = "Game Over";"
	}
	
	
	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			timer.start();
			paddle.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			timer.start();
			paddle.keyPressed(e);
		}
	}
	
	private class GameCycle implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			doGameCycle();

		}
	}
	
	
	private void doGameCycle() {
		ball.move();
		paddle.move();
		checkCollision();
		repaint();
		Timer_Display_Combo ++;
		// System.out.println(Timer_Display_Combo);
		if(Timer_Display_Combo > 100) Str_Combo_Score = "";
		else Str_Combo_Score = "Combo: " + Combo_Score  + " + " + (10 + Combo_Score * 10 - 10 );
	}

	private void stopGame() {
		inGame = false;
		timer.restart();
		timer.stop();
	}

	private void continueGame(){
		
		paddle.reset();
		ball.reset();
		ball.setyDirection(-Constants.SPEED_BALL);
		new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					ball.setxDirection(Constants.SPEED_BALL);
				}

				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					ball.setxDirection(-Constants.SPEED_BALL);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
		};                                                       
		timer.stop();
	}

	private void checkCollision() {

		if (ball.getRectangle().getMaxY() > Constants.BOTOM_EDGE) {
			HealPoint --;
			if(HealPoint == 0)
				stopGame();
			else {
				continueGame();
			}
		}

		for (int i = 0, j = 0; i < Constants.NUMBER_OF_BRICK; i++) {

			if (bricks[i].isDestroyed()) {

				j++;

			}

			if (j == Constants.NUMBER_OF_BRICK) {

				Level__game++;

				if(Level__game == Level.getLevelMax()){
					message = "Victory";
					stopGame();
				}else{
					initial();
					timer.stop();
				}
				
				
			}
		}



		// nếu viền bóng(ball) chạm viền thanh trượt(paddle)
		if ((ball.getRectangle()).intersects(paddle.getRectangle())) {					

				ball.setyDirection(-Constants.SPEED_BALL);
				Combo_Score = 0;
				
			    if (ball.getX() + ball.getImageWidth() / 2 < paddle.getX() + paddle.getImageWidth() / 4 && ball.getxDirection() > 0) {
			        ball.setxDirection(-Constants.SPEED_BALL);
			    } else if (ball.getX() + ball.getImageWidth() / 2 > paddle.getX() + paddle.getImageWidth() * 3 / 4 && ball.getxDirection() < 0) {
			        ball.setxDirection(Constants.SPEED_BALL);
			    }
			}

		for (int i = 0; i < Constants.NUMBER_OF_BRICK; i++) {
			
			//kiểm tra bóng có chạm vào viên gạch thứ i không

			if ((ball.getRectangle()).intersects(bricks[i].getRectangle())) {

				// viền hình chữ nhật xung quanh quả bóng

				int ballLeft = (int) ball.getRectangle().getMinX();
				int ballHeight = (int) ball.getRectangle().getHeight();
				int ballWidth = (int) ball.getRectangle().getWidth();
				int ballTop = (int) ball.getRectangle().getMinY();

				var pointRight = new Point(ballLeft + ballWidth + 5, ballTop + ballHeight / 2);
				var pointLeft = new Point(ballLeft - 5, ballTop + ballHeight / 2);
				var pointTop = new Point(ballLeft + ballWidth / 2, ballTop - 5);
				var pointBottom = new Point(ballLeft + ballWidth / 2, ballTop + ballHeight + 5);


				// nếu viên gạch thứ i chưa bị phá hủy

				if (!bricks[i].isDestroyed()) {

					if (bricks[i].getRectangle().contains(pointRight)) {

						ball.setxDirection(-Constants.SPEED_BALL);

					} else if (bricks[i].getRectangle().contains(pointLeft)) {

						ball.setxDirection(Constants.SPEED_BALL);

					}

					if (bricks[i].getRectangle().contains(pointTop)) {

						ball.setyDirection(Constants.SPEED_BALL);

					} else if (bricks[i].getRectangle().contains(pointBottom)) {

						ball.setyDirection(-Constants.SPEED_BALL);

					}


					// viên gạch thứ i bị phá hủy 
					Timer_Display_Combo = 0;
					

					bricks[i].setDestroyed(true);
					Combo_Score ++;
					Score += 10 + Combo_Score * 10 - 10;
				}


			}
		}
		
	}
}
