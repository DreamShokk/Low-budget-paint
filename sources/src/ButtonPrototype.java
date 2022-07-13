import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class ButtonPrototype extends JButton {
    private final int Index;
    ButtonPrototype(ImageIcon I, int index, MouseAdapter Adapter){
        Index=index;
        setIcon(I);
        setBorder(null);
        setFocusable(false);
        setBackground(Color.white);
        setBounds(5+Index*50,5,40,40);
        addMouseListener(Adapter);
    }
    JPanel getMenu(int height, Color color){
        JPanel Panel=new JPanel();
        Panel.setBounds((Index-1)*50,50,150,height);
        Panel.setBackground(color);
        Panel.setVisible(false);
        return Panel;
    }
}
