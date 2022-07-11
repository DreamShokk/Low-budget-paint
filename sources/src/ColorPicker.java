import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;


public class ColorPicker  extends JFrame {

    ColorPicker(){
        super("Color Picker");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        JColorChooser colorpick = new JColorChooser();
        colorpick.getSelectionModel().addChangeListener(l->{
            LowBudgetPaint.currentColor=colorpick.getColor();
        });
        MouseInputListener MouseEntered=new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

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
                repaint();
            }
        };
        addMouseMotionListener(MouseEntered);
        setSize(600,400);
        setLocationRelativeTo(null);
        add(colorpick);
    }
}
