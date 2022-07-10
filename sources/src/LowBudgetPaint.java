import java.awt.image.BufferedImage;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LowBudgetPaint extends JFrame{
    final int width=800;
    final int height=800;
    final int menuheight=50;


    BufferedImage currentImage=new BufferedImage(width, height-menuheight, BufferedImage.TYPE_INT_ARGB);
    


    public LowBudgetPaint(String title){
        super(title);

        setBounds( 0,0,width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel menu=new JPanel();
        JPanel body=new JPanel();
        menu.setBounds(0, 0, width, menuheight);
        body.setBounds(0, menuheight, width, height-menuheight);
        menu.setBackground(Color.GRAY);
        body.setBackground(Color.WHITE);

        add(menu);
        add(body);
        setResizable(false);
        setVisible(true);
    }






    public static void main(String[] args) {
        new LowBudgetPaint("LBP");
    }
}