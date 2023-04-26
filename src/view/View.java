package view;

import javax.swing.JFrame;

import controller.Controller;

public class View extends JFrame {
	
	
	public View() {
		add(new Controller());
		setTitle("Breakout");					// đặt tiêu đề cho frame.
		setDefaultCloseOperation(EXIT_ON_CLOSE);	// đặt phương thức xử lý sự kiện khi người dùng đóng cửa sổ ->> thoát khỏi ứng dụng.
		setLocation(450,200);
		setResizable(false);				// khóa kích thước của frame không cho phép người dùng thay đổi kích thước.
		pack();	
	}
}
