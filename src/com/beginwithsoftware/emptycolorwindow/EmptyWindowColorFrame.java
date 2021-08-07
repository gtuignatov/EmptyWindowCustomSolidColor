package com.beginwithsoftware.emptycolorwindow;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creates simple window filled with solid color, defined by user in first argument line
 * and maximized to desktop resolution. Title for the windows could be defined as second argument:
 * c:\OpenJDK\bin\java.exe -classpath out\production\EmptyWindowCustomSolidColor
 * com.beginwithsoftware.emptycolorwindow.EmptyWindowColorFrame #AAABBB "My Window Title"
 **/
public class EmptyWindowColorFrame extends JFrame {

    private static String defaultWindowTitle = "BeginWithSoftware.Com/java";
    private static String defaultColor = "#000040";
    private static String hexColorRegex = "^#[0-9A-F]{6}$";

    public EmptyWindowColorFrame(String customHexColor) {

        // https://stackoverflow.com/questions/36969432/java-change-color-of-an-jpanel-with-fillrect
        JPanel colorPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Let's Paint Background
                g2.setColor(Color.decode(customHexColor));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setLayout(new BorderLayout());
        add(colorPanel);
    }

    private static String getCustomHexColor(String[] args) {
        String customHexColor = defaultColor;
        if (args != null && args.length > 0) {
            Pattern pattern = Pattern.compile(hexColorRegex);
            Matcher matcher = pattern.matcher(args[0]);
            if (matcher.find()) {
                customHexColor = args[0];
            }
        }
        return customHexColor;
    }

    private static String getCustomWindowTitle(String[] args) {
        String customWindowTitle = defaultWindowTitle;
        if (args !=null && args.length > 1) {
            customWindowTitle = args[1];
        }
        return customWindowTitle;
    }

    public static void main(String[] args) {
        // Check custom color in args[]
        String customHexColor = getCustomHexColor(args);
        // Custom window title
        String customWindowTitle = getCustomWindowTitle(args);
        // Detect screen size
        //https://stackoverflow.com/questions/3680221/how-can-i-get-screen-resolution-in-java
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        EmptyWindowColorFrame emptyWindowColorFrame = new EmptyWindowColorFrame(customHexColor);
        emptyWindowColorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // https://stackoverflow.com/questions/479523/jframe-maximize-window
        emptyWindowColorFrame.setExtendedState(emptyWindowColorFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        emptyWindowColorFrame.setTitle(customWindowTitle);
        emptyWindowColorFrame.setVisible(true);
    }
}
