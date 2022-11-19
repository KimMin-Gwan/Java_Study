import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class aba extends JFrame {
	private JLabel label = new JLabel("Love Java");
	public aba() {
		setTitle("마우스 이벤트 리스터");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		//리스터 객체 생성
		MyMouseListener listener = new MyMouseListener();
		c.addMouseListener(listener); //MouseListener등록
		c.addMouseMotionListener(listener); // 등록
		c.add(label);
		setSize(300,200);
		setVisible(true);
	}
	class MyMouseListener implements MouseListener,
		MouseMotionListener { //리스너 2가지를 가진 리스너 작성
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
        }
		public void mouseClicked(MouseEvent e) {

        }
		public void mouseEntered(MouseEvent e) {
			Component c = (Component)e.getSource();
			label.setText("사랑해");
		}
		public void mouseExited(MouseEvent e) {
			Component c = (Component)e.getSource();
			label.setText("Love Java");
		}
		public void mouseDragged(MouseEvent e) {
		}
		public void mouseMoved(MouseEvent e) {
		} 
	}
	public static void main(String [] args) {
		new aba();
	}
}

