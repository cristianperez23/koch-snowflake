import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KochSnowflake extends JPanel implements ActionListener {
    private int order = 0;
    private int numSides = 3;
    private double length = 400.0;
    private double centerX = 300.0;
    private double centerY = 300.0;
    private double rotationAngle = 0.0;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Koch Snowflake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        KochSnowflake snowflake = new KochSnowflake();
        frame.add(snowflake);
        frame.pack();
        frame.setVisible(true);
    }
    
    public KochSnowflake() {
        setPreferredSize(new Dimension(600, 600));
        JButton increaseButton = new JButton("+");
        increaseButton.addActionListener(this);
        JButton decreaseButton = new JButton("-");
        decreaseButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(decreaseButton);
        buttonPanel.add(increaseButton);
        add(buttonPanel, BorderLayout.NORTH);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSnowflake(g, order, centerX, centerY, length, rotationAngle);
    }
    
    private void drawSnowflake(Graphics g, int order, double centerX, double centerY, double length, double rotationAngle) {
        if (order == 0) {
            // base case: draw a line segment
            double x = centerX + length * Math.cos(Math.toRadians(rotationAngle));
            double y = centerY - length * Math.sin(Math.toRadians(rotationAngle));
            g.drawLine((int) centerX, (int) centerY, (int) x, (int) y);
        } else {
            // recursive case: draw four smaller snowflake segments
            length /= 3.0;
            drawSnowflake(g, order - 1, centerX, centerY, length, rotationAngle);
            centerX += length * Math.cos(Math.toRadians(rotationAngle));
            centerY -= length * Math.sin(Math.toRadians(rotationAngle));
            rotationAngle += 60.0;
            drawSnowflake(g, order - 1, centerX, centerY, length, rotationAngle);
            centerX += length * Math.cos(Math.toRadians(rotationAngle));
            centerY -= length * Math.sin(Math.toRadians(rotationAngle));
            rotationAngle -= 120.0;
            drawSnowflake(g, order - 1, centerX, centerY, length, rotationAngle);
            centerX += length * Math.cos(Math.toRadians(rotationAngle));
            centerY -= length * Math.sin(Math.toRadians(rotationAngle));
            rotationAngle += 60.0;
            drawSnowflake(g, order - 1, centerX, centerY, length, rotationAngle);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("+")) {
            order++;
        } else if (e.getActionCommand().equals("-")) {
            order--;
        }
        repaint();
    }
}
