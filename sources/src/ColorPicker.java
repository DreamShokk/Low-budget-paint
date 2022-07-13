
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ColorPicker  extends JFrame {
    private JColorChooser colorpick;

    ColorPicker(){
        super("Color Picker");
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setSize(600,400);
            setLocationRelativeTo(null);
        SwingUtilities.invokeLater(() -> {
            colorpick = new JColorChooser();
            colorpick.getSelectionModel().addChangeListener(l-> LowBudgetPaint.currentColor=colorpick.getColor());
            add(colorpick);
        });


        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                repaint();
            }
        });


    }
    @Override
    public void paintComponents(Graphics g){
        super.paintComponents(g);
        colorpick.repaint();
    }
}
