import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Point;

public class Painel extends JPanel{
    static BufferedImage currentImage=new BufferedImage(LowBudgetPaint.width, LowBudgetPaint.height-LowBudgetPaint.menuheight, BufferedImage.TYPE_INT_ARGB);
    static Point PP;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(currentImage, 0, 0, null);
    }
    private void toDraw(MouseEvent e){
        Graphics2D a=currentImage.createGraphics();
        a.setColor(LowBudgetPaint.currentColor);
        a.setStroke(new BasicStroke(LowBudgetPaint.currentLineSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        a.drawLine(PP.x, PP.y, e.getX(), e.getY());
        a.dispose();
        repaint();
    }
    Painel(){
        super();
        MouseInputListener motion=new MouseInputListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                PP=e.getPoint();
                toDraw(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseDragged(MouseEvent e) {
                toDraw(e);
                PP=e.getPoint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
            
        };
        addMouseListener(motion);
        addMouseMotionListener(motion);
    }
} 
