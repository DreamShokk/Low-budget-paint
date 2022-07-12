import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ColorPicker  extends JFrame {

    ColorPicker(){
        super("Color Picker");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        JColorChooser colorpick = new JColorChooser();
        colorpick.getSelectionModel().addChangeListener(l-> LowBudgetPaint.currentColor=colorpick.getColor());
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                repaint();
            }
        });
        setSize(600,400);
        setLocationRelativeTo(null);
        add(colorpick);
    }
}
