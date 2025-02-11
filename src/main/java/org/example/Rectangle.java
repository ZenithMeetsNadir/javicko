package org.example;

public class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return String.format("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>shape</title>
                    <style>
                        body {
                            margin: 0;
                        }
                        .shape {
                            display: block;
                            margin: 0 auto;
                            width: %dpx;
                            height: %dpx;
                            background-color: cyan;
                        }
                        .shape::before {
                            content: "area: %.2fpx^2\\Aperimeter: %.2fpx";
                            white-space: pre;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            width: 100%%;
                            height: 100%%;
                            font-size: 20px;
                        }
                    </style>
                </head>
                <body>
                   <div class="shape"></div>
                </body>
                </html>
                """, (int)getWidth(), (int)getHeight(), getArea(), getPerimeter());
    }
}
