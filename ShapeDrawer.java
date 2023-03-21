package com.example.demo;

import org.hibernate.engine.jdbc.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ShapeDrawer {

    private String shape;
    private String size;
    private String colour;
    private Boolean fill;
    public ShapeDrawer(String shape, String size, String colour, Boolean fill) {
        this.shape= shape;
        this.size=size;
        this.colour=colour;
        this.fill=fill;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    public ShapeDrawer(String shape, String size){
        this.shape=shape;
        this.size=size;
    }
    public ShapeDrawer(String shape){
        this.shape=shape;
    }
    public BufferedImage printer() throws NoSuchFieldException, IllegalAccessException {
        BufferedImage img = new BufferedImage(1024,1024,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, img.getWidth(), img.getHeight());
        if (getColour() == null){
            setColour("black");
        }
        Field field = Color.class.getField(getColour()) ;
        Color color =  (Color)field.get(null);
        g.setBackground(Color.white);
        if ( color!= null){
            g.setColor(color);
        }

        switch (shape){
            case "rectangle":
                if (getSize().equals("small")){
                    int shapeSize=10;
                    int x= img.getWidth()/2 - shapeSize/2;
                    int y= img.getHeight()/2 - shapeSize/2;
                    if (fill){
                        g.fillRect(x,y,shapeSize,shapeSize);
                    }
                    else {
                        g.drawRect(x,y,shapeSize,shapeSize);
                    }

                }
                else if (getSize().equals("medium")){
                    int shapeSize=500;
                    int x= img.getWidth()/2 - shapeSize/2;
                    int y= img.getHeight()/2 - shapeSize/2;
                    if (fill){
                        g.fillRect(x,y,shapeSize,shapeSize);
                    }
                    else {
                        g.drawRect(x,y,shapeSize,shapeSize);
                    }
                }
                else if (getSize().equals("large")){
                    int shapeSize=1000;
                    int x= img.getWidth()/2 - shapeSize/2;
                    int y= img.getHeight()/2 - shapeSize/2;
                    if (fill){
                        g.fillRect(x,y,shapeSize,shapeSize);
                    }
                    else {
                        g.drawRect(x,y,shapeSize,shapeSize);
                    }
                }
                break;
            case "circle":
                if (getSize().equals("small")){
                    int shapeSize=10;
                    int x= img.getWidth()/2 - shapeSize/2;
                    int y= img.getHeight()/2 - shapeSize/2;
                    if (fill ){
                        g.fillOval(x,y,shapeSize,shapeSize);
                    }
                    else {
                        g.drawOval(x,y,shapeSize,shapeSize);
                    }
                }
                if (getSize().equals("medium")){
                    int shapeSize=500;
                    int x= img.getWidth()/2 - shapeSize/2;
                    int y= img.getHeight()/2 - shapeSize/2;
                    if (fill){
                        g.fillOval(x,y,shapeSize,shapeSize);
                    }
                    else {
                        g.drawOval(x,y,shapeSize,shapeSize);
                    }
                }
                if (getSize().equals("large")){
                    int shapeSize=1000;
                    int x= img.getWidth()/2 - shapeSize/2;
                    int y= img.getHeight()/2 - shapeSize/2;
                    if (fill){
                        g.fillOval(x,y,shapeSize,shapeSize);
                    }
                    else {
                        g.drawOval(x,y,shapeSize,shapeSize);
                    }
                }
                break;
            default:
                g.drawRect(100,100,100,100);
        }
        drawDate(g);
        return img;
    }
    public File Img2file(BufferedImage img) throws IOException {
        File outputfile = new File("image.jpg");
        ImageIO.write(img, "jpg", outputfile);
        return outputfile;
    }



    public void drawDate(Graphics2D g){
        Font font = new Font("ARIAL", Font.PLAIN, 20);
        g.setFont(font);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        g.drawString((dtf.format(now)), 50, 50);
    }

}
