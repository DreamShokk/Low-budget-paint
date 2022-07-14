import javax.swing.*;
import java.awt.*;

public class ColorPicker  extends JFrame {

    public static Color Invert(Color color) {
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return y >= 128 ? Color.black : Color.white;
    }

    ColorPicker(){
        super("Color Picker");
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setSize(600,400);
            setLocationRelativeTo(null);
            JColorChooser colorpick = new JColorChooser();
            colorpick.getSelectionModel().addChangeListener(l->{
                LowBudgetPaint.currentColor=colorpick.getColor();
                LowBudgetPaint.panels.get(Tool.Brush.code).setBackground(colorpick.getColor());
                LowBudgetPaint.sizeToText.setForeground(Invert(colorpick.getColor()));
            });
            add(colorpick);
    }
}
