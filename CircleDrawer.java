import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// new comment
public class CircleDrawer extends JFrame {

    private JTextField radiusField;
    private DrawPanel drawPanel;

    public CircleDrawer() {
        setTitle("Circle Drawer");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        radiusField = new JTextField(5);
        JButton drawButton = new JButton("Draw");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawCircle();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Radius: "));
        controlPanel.add(radiusField);
        controlPanel.add(drawButton);

        drawPanel = new DrawPanel();

        add(controlPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
    }

    private void drawCircle() {
        try {
            int radius = Integer.parseInt(radiusField.getText());
            drawPanel.setRadius(radius);
            drawPanel.repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer for the radius.");
        }
    }

    private static class DrawPanel extends JPanel {
        private int radius = 0;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (radius > 0) {
                int x = getWidth() / 2 - radius;
                int y = getHeight() / 2 - radius;
                g.drawOval(x, y, radius * 2, radius * 2);
            }
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CircleDrawer().setVisible(true);
            }
        });
    }
}
