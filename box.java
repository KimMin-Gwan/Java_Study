import java.awt.*;
import javax.swing.*;

public class box  extends JFrame{
    public box()
    {
        super("Ten Buttons Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new GridLayout(1, 10));

        JButton [] btn = new JButton[10];
        for(int i = 0; i < btn.length; i++)
        {
            btn[i] = new JButton(Integer.toString(i));
            c.add(btn[i]);
        }
        setSize(500, 200);
        setVisible(true);
    }
    public static void main(String[] args) 
    {
        new box();
    }
}
