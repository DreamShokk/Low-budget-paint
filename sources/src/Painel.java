import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
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
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PP=e.getPoint();
                LowBudgetPaint.setSizeSettingsVisibility(false);
                toDraw(e);
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                toDraw(e);
                PP=e.getPoint();
            }
        });
    }
} 
