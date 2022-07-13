import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.imageio.ImageIO;

public class LowBudgetPaint extends JFrame{
    //Global settings
    static final int width=800;
    static final int height=800;
    static final int menuheight=50;
    static Color currentColor=Color.black;
    static int currentLineSize=10;
    static int currentRubberSize=10;
    private ColorPicker colorPickerWindow;
    static JLabel sizeToText;
    static JLabel rubberSizeToText;
    static ArrayList<JPanel> panels=new ArrayList<>();
    static Tool currentTool=Tool.Brush;


    //Global images
    static final ImageIcon saved=new ImageIcon(LowBudgetPaint.class.getResource("images/save.png"));
    static final ImageIcon done=new ImageIcon(LowBudgetPaint.class.getResource("images/done.png"));
    static final ImageIcon size=new ImageIcon(LowBudgetPaint.class.getResource("images/size.png"));
    static final ImageIcon rubber=new ImageIcon(LowBudgetPaint.class.getResource("images/rubber.png"));
    static final ImageIcon pipette=new ImageIcon(LowBudgetPaint.class.getResource("images/pipette.png"));

//This instance
    static private LowBudgetPaint instance;

    public static void hideAll(int ExcludedIndex){
        for(int i=0;i<panels.size();i++)
            if(i!=ExcludedIndex)
                panels.get(i).setVisible(false);
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
        ButtonPrototype save=new ButtonPrototype(saved,0,new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hideAll(Tool.Save.code);
                try{
                    ImageIO.write(Painel.currentImage, "png", new File("image.png"));
                    repaint();
                    ((JButton)e.getSource()).setIcon(done);

                    Timer timer = new Timer(2000, arg0 -> ((JButton)e.getSource()).setIcon(saved));
                    timer.setRepeats(false);
                    timer.start();


                }catch(IOException ex){System.err.println("Error while omitting file: "+ex.getMessage());}}
        });
        panels.add(Tool.Save.code,new JPanel());
        //Button for brush settings
        ButtonPrototype setBrush=new ButtonPrototype(size,1,new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                hideAll(1);
                currentTool=Tool.Brush;
                panels.get(Tool.Brush.code).setVisible(!panels.get(Tool.Brush.code).isVisible());
            }});

        //UI for setting brush details
        panels.add(Tool.Brush.code,setBrush.getMenu(75,Color.black));

        JSlider setSizeSlider=new JSlider(5,25);
        sizeToText=new JLabel(setSizeSlider.getValue()+" px");

        //Labeling for the stroke size
        sizeToText.setBounds(0,0,150,25);
        sizeToText.setHorizontalAlignment(JLabel.CENTER);
        sizeToText.setForeground(Color.white);
        panels.get(Tool.Brush.code).add(sizeToText);

        //Slider for the stroke size

        setSizeSlider.setValue(10);
        setSizeSlider.setBounds(0,25,150,25);
        setSizeSlider.setLayout(null);
        setSizeSlider.addChangeListener(x->{
            currentLineSize=setSizeSlider.getValue();
            sizeToText.setText(setSizeSlider.getValue()+" px");
        });
        panels.get(Tool.Brush.code).add(setSizeSlider);

        //Color picker
        SwingUtilities.invokeLater(() -> colorPickerWindow=new ColorPicker());

        JButton colorPicker=new JButton("Color picker");

        colorPicker.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                colorPickerWindow.setVisible(true);
            }
        });

        colorPicker.setBounds(0,50,150,25);
        panels.get(Tool.Brush.code).add(colorPicker);

        //Button for rubber settings
        ButtonPrototype setRubber=new ButtonPrototype(rubber, 2, new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                hideAll(Tool.Rubber.code);
                currentTool=Tool.Rubber;
                panels.get(Tool.Rubber.code).setVisible(!panels.get(Tool.Rubber.code).isVisible());
            }});

        //Panel for rubber settings
        panels.add(setRubber.getMenu(50,Color.black));

        //Slider for rubber size
        JSlider setRubberSlider=new JSlider(5,50);
        setRubberSlider.setValue(10);
        setRubberSlider.setBounds(0,25,150,25);
        setRubberSlider.setLayout(null);
        setRubberSlider.addChangeListener(x->{
            currentRubberSize=setRubberSlider.getValue();
            rubberSizeToText.setText(setRubberSlider.getValue()+" px");
        });
        panels.get(Tool.Rubber.code).add(setRubberSlider);

        //labeling for rubber size
        rubberSizeToText=new JLabel(setRubberSlider.getValue()+" px");
        rubberSizeToText.setBounds(0,0,150,25);
        rubberSizeToText.setHorizontalAlignment(JLabel.CENTER);
        rubberSizeToText.setForeground(Color.white);
        panels.get(Tool.Rubber.code).add(rubberSizeToText);

        //TODO Use these enumerated panel slots when new functions are implemented
        panels.add(Tool.Bucket.code,new JPanel());
        panels.add(Tool.Spray.code,new JPanel());
        panels.add(Tool.Wand.code,new JPanel());


        //Colorpicker (pipette) Button and menu
        ButtonPrototype setPipette=new ButtonPrototype(pipette, Tool.ColorPicker.code, new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTool=Tool.ColorPicker;
            }
        });
        panels.add(Tool.ColorPicker.code,new JPanel());








        //The body (canvas) itself
        Painel body=new Painel();
        body.setBounds(0, menuheight, width, height-menuheight);
        body.setBackground(Color.WHITE);

        //Construction of the menu bar
        menu.add(save);
        menu.add(setBrush);
        menu.add(setRubber);
        menu.add(setPipette);

        //Construction of the window itself
        add(menu);
        add(panels.get(Tool.Brush.code));
        add(panels.get(Tool.Rubber.code));
        add(body);
        setResizable(false);
        panels.get(Tool.Brush.code).setLayout(null);
        panels.get(Tool.Rubber.code).setLayout(null);
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
