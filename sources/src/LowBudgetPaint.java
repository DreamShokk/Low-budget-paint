
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.imageio.ImageIO;

public class LowBudgetPaint extends JFrame{
    //Global settings
    static final int width=800;
    static final int height=800;
    static final int menuheight=50;
    static Color currentColor=Color.black;
    static int currentLineSize=10;
    private ColorPicker colorPickerWindow;


    //Global images
    static final ImageIcon saved=new ImageIcon(LowBudgetPaint.class.getResource("images/save.png"));
    static final ImageIcon done=new ImageIcon(LowBudgetPaint.class.getResource("images/done.png"));
    static final ImageIcon size=new ImageIcon(LowBudgetPaint.class.getResource("images/size.png"));

//This instance
    static private LowBudgetPaint instance;
    private final JPanel brushSettings =new JPanel();
    public static void setSizeSettingsVisibility(boolean setTo){
        instance.brushSettings.setVisible(setTo);
    }

    private LowBudgetPaint(String title){
        //The window itself
        super(title);
        setLayout(null);
        setBounds( 0,0,width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //The menu itself
        JPanel menu=new JPanel();
        menu.setLayout(null);
        menu.setBounds(0, 0, width, menuheight);
        menu.setBackground(Color.GRAY);

        //Save button on the menu
        JButton save=new JButton(saved);
        save.setBorder(null);
        save.setFocusable(false);
        save.setBackground(Color.white);
        save.setBounds(5,5,40,40);
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    ImageIO.write(Painel.currentImage, "png", new File("image.png"));
                    repaint();
                    save.setIcon(done);

                    Timer timer = new Timer(2000, arg0 -> save.setIcon(saved));
                    timer.setRepeats(false);
                    timer.start();


                }catch(IOException ex){System.err.println("Error while omitting file: "+ex.getMessage());}}
        });


        //UI for setting brush details
        brushSettings.setBounds(0,50,150,75);
        brushSettings.setBackground(Color.cyan);

        JSlider setSizeSlider=new JSlider(5,25);
        JLabel sizeToText=new JLabel(setSizeSlider.getValue()+" px");

        //Labeling for the stroke size
        sizeToText.setBounds(0,0,150,25);
        sizeToText.setHorizontalAlignment(JLabel.CENTER);
        brushSettings.add(sizeToText);

        //Slider for the stroke size
        setSizeSlider.setValue(10);
        setSizeSlider.setBounds(0,25,150,25);
        setSizeSlider.setLayout(null);
        setSizeSlider.addChangeListener(x->{
            currentLineSize=setSizeSlider.getValue();
            sizeToText.setText(setSizeSlider.getValue()+" px");
        });
        brushSettings.add(setSizeSlider);

        //Color picker
        SwingUtilities.invokeLater(() -> colorPickerWindow=new ColorPicker());

        JButton colorPicker=new JButton();

        colorPicker.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                colorPickerWindow.setVisible(true);
            }
        });

        colorPicker.setBounds(0,50,150,25);
        brushSettings.add(colorPicker);

        //Button for brush settings
        JButton setBrush=new JButton(size);
        setBrush.setBorder(null);
        setBrush.setFocusable(false);
        setBrush.setBackground(Color.white);
        setBrush.setBounds(55,5,40,40);
        setBrush.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                brushSettings.setVisible(!brushSettings.isVisible());
            }
        });


        //The body (canvas) itself
        Painel body=new Painel();
        body.setBounds(0, menuheight, width, height-menuheight);
        body.setBackground(Color.WHITE);

        //Construction of the menu bar
        menu.add(save);
        menu.add(setBrush);


        //Construction of the window itself
        add(menu);
        brushSettings.setVisible(false);
        add(brushSettings);
        add(body);
        setResizable(false);
        brushSettings.setLayout(null);
        setVisible(true);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                repaint();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> instance=new LowBudgetPaint("Low Budget Paint"));
    }
}
