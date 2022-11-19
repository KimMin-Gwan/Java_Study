import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class abc extends JFrame {
    public abc() {
        super("드래깅동안YELLOW로 변경");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setBackground(Color.GREEN);
        MyMouseListener ml = new MyMouseListener();
        c.addMouseMotionListener(ml);
        c.addMouseListener(ml);
        setSize(250,150);
        setVisible(true);
    }
        
    class MyMouseListener extends MouseAdapter 
    {
        public void mouseDragged(MouseEvent e) {
            JComponent c = (JComponent)e.getSource();
            c.setBackground(Color.YELLOW);
        }

        public void mouseReleased(MouseEvent e) {
            JComponent c = (JComponent)e.getSource();
            c.setBackground(Color.GREEN);
        }
    }

    static public void main(String [] args) {
        new abc();
    }
}

