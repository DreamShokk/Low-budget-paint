import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.imageio.ImageIO;

public class LowBudgetPaint extends JFrame{

    static final int width=800;
    static final int height=800;
    static final int menuheight=50;
    static Color currentColor=Color.black; 
    static int currentLineSize=10;

    static final ImageIcon saved=new ImageIcon(LowBudgetPaint.class.getResource("images/save.png"));
    static final ImageIcon done=new ImageIcon(LowBudgetPaint.class.getResource("images/done.png"));


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

        JButton save=new JButton();
        save.setIcon(saved);
        save.setBorder(null);
        save.setMargin(new Insets(0, 0, 0, 0));;

        save.setBackground(Color.white);
        save.setBounds(0,0,50,50);
        save.addMouseListener(new MouseInputListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
            try{
                ImageIO.write(Painel.currentImage, "png", new File("image.png"));
                repaint();
                save.setIcon(done);

                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        save.setIcon(saved);
                    }
                });
                timer.setRepeats(false);
                timer.start();


            }catch(IOException ex){System.err.println("Error while omitting file: "+ex.getMessage());};}

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