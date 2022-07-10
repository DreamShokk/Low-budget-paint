import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.event.MouseInputListener;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LowBudgetPaint extends JFrame{

    static final int width=800;
    static final int height=800;
    static final int menuheight=50;
    static Color currentColor=Color.black; 
    static int currentLineSize=10;
    


    public LowBudgetPaint(String title){
        super(title);
        setLayout(null);
        setBounds( 0,0,width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel menu=new JPanel();
        Painel body=new Painel();
        menu.setLayout(null);

        menu.setBounds(0, 0, width, menuheight);
        body.setBounds(0, menuheight, width, height-menuheight);
        menu.setBackground(Color.GRAY);
        body.setBackground(Color.WHITE);

        JButton save=new JButton("Save image");
        save.setBackground(Color.white);
        save.setForeground(Color.black);
        save.setBounds(0,0,50,50);
        save.addMouseListener(new MouseInputListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
            try{
                ImageIO.write(Painel.currentImage, "png", new File("image.png"));
                repaint();
            }catch(IOException ex){System.err.println("Error while ommiting file: "+ex.getMessage());};}

            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {}
            
        });

        menu.add(save);


        add(menu);
        add(body);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LowBudgetPaint("LBP");
    }
}