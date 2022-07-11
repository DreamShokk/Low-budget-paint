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
    static final ImageIcon size=new ImageIcon(LowBudgetPaint.class.getResource("images/size.png"));


    private JPanel sizeSettings=new JPanel();
    static private LowBudgetPaint instance;
    public static void setSizeSettingsVisibility(boolean setTo){
        instance.sizeSettings.setVisible(setTo);
    }

    public LowBudgetPaint(String title){
        super(title);
        setLayout(null);
        setBounds( 0,0,width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        JPanel menu=new JPanel();
        menu.setLayout(null);
        menu.setBounds(0, 0, width, menuheight);
        menu.setBackground(Color.GRAY);


        Painel body=new Painel();
        body.setBounds(0, menuheight, width, height-menuheight);
        body.setBackground(Color.WHITE);

        JButton save=new JButton(saved);
        save.setBorder(null);
        save.setFocusable(false);
        save.setBackground(Color.white);
        save.setBounds(5,5,40,40);



        sizeSettings.setBounds(0,50,150,50);
        sizeSettings.setBackground(Color.cyan);

        JSlider setSizeSlider=new JSlider(5,25);
        setSizeSlider.setValue(10);
        JLabel sizeToText=new JLabel(setSizeSlider.getValue()+" px");

        sizeToText.setBounds(0,0,150,25);
        sizeToText.setHorizontalAlignment(JLabel.CENTER);
        sizeSettings.add(sizeToText);


        setSizeSlider.setBounds(0,25,150,25);
        setSizeSlider.setLayout(null);
        setSizeSlider.addChangeListener(x->{
            currentLineSize=setSizeSlider.getValue();
            sizeToText.setText(setSizeSlider.getValue()+" px");
        });
        sizeSettings.add(setSizeSlider);



        JButton setSize=new JButton(size);
        setSize.setBorder(null);
        setSize.setFocusable(false);
        setSize.setBackground(Color.white);
        setSize.setBounds(55,5,40,40);
        MouseInputListener sizeClicked=new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                sizeSettings.setVisible(!sizeSettings.isVisible());
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        };
        setSize.addMouseListener(sizeClicked);
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
        menu.add(setSize);

        add(menu);
        sizeSettings.setVisible(false);
        add(sizeSettings);
        add(body);
        setResizable(false);
        sizeSettings.setLayout(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        instance=new LowBudgetPaint("Low Budget Paint");
    }
}