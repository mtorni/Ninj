package com.test.image;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HtmlToImage {
    private static volatile boolean loaded;

    public static void main(String[] args) throws IOException {
        loaded = false;
        URL url = new URL("http://www.google.com");
        JEditorPane editorPane = new JEditorPane();
        editorPane.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("page")) {
                    loaded = true;
                }
            }
        });
        editorPane.setPage(url);
        while (!loaded) {
            Thread.yield();
        }

        File file = new File("out.png");

        componentToImage(editorPane, file);
    }

    public static void componentToImage(Component comp, File file) throws IOException {
        Dimension prefSize = comp.getPreferredSize();
        System.out.println("prefSize = " + prefSize);
        BufferedImage img = new BufferedImage(prefSize.width, comp.getPreferredSize().height,
                                              BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = img.getGraphics();
        comp.setSize(prefSize);
        comp.paint(graphics);
        ImageIO.write(img, "png", file);
    }

}