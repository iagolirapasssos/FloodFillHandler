package com.bosonshiggs.extendedcanvas.helpers;

import com.google.appinventor.components.runtime.Canvas;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import java.util.LinkedList;
import java.util.Queue;

import android.util.Log;

public class FloodFillHandler {
    private android.view.View view;

    public void setCanvas(Canvas canvas) {
        this.view = canvas.getView();
    }
    
    public void fastFloodFill(int x, int y, int newColor, Canvas canvas) {
        if (canvas == null) {
            return;
        }

        int width = canvas.Width();
        int height = canvas.Height();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.view.invalidate();
        
     // Copies the current state of the Canvas to the Bitmap
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = canvas.GetPixelColor(i, j);
                bitmap.setPixel(i, j, color);
            }
        }

        int targetColor = bitmap.getPixel(x, y);
        int oldColor = canvas.PaintColor();
        
        if (targetColor == newColor) {
            return; // Nenhuma mudança necessária se a cor de destino for a mesma que a nova cor
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        
        canvas.PaintColor(newColor);
        while (!queue.isEmpty()) {
            Point p = queue.remove();
            if (p.x < 0 || p.x >= width || p.y < 0 || p.y >= height) {
                continue;
            }

            if (p.x >= 0 && p.x < width && p.y >= 0 && p.y < height && bitmap.getPixel(p.x, p.y) == targetColor) {
                bitmap.setPixel(p.x, p.y, newColor);
                canvas.DrawCircle(p.x, p.y, 1, true);

                // Adicionar pontos adjacentes à fila
                queue.add(new Point(p.x - 1, p.y));
                queue.add(new Point(p.x + 1, p.y));
                queue.add(new Point(p.x, p.y - 1));
                queue.add(new Point(p.x, p.y + 1));
            }
        }

        // Atualiza o canvas com o bitmap modificado
        canvas.PaintColor(oldColor);
    }
    
    public void floodFillCircle(int x, int y, int newColor, float radius, Canvas canvas) {
        Log.d("ExtendedCanvas", "Starting floodFill"); // Log to start the flood fill
        if (canvas == null) {
            Log.d("ExtendedCanvas", "Canvas is null");
            return;
        }
        int width = canvas.Width();
        int height = canvas.Height();
        Log.d("ExtendedCanvas", "Canvas Dimensions: Width = " + width + ", Height = " + height); // Log for canvas dimensions

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.view.invalidate();


        // Copies the current state of the Canvas to the Bitmap
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = canvas.GetPixelColor(i, j);
                bitmap.setPixel(i, j, color);
            }
        }

        // Applies flood fill to the bitmap
        int targetColor = bitmap.getPixel(x, y);
        int oldColor = canvas.PaintColor();
        Log.d("ExtendedCanvas", "Target color: " + targetColor); // Log for target color
        if (targetColor != newColor) {
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(x, y));
            
            Log.d("ExtendedCanvas", "Starting flood fill loop");
            while (!queue.isEmpty()) {
                Point p = queue.remove();
                if (p.x >= 0 && p.x < width && p.y >= 0 && p.y < height && bitmap.getPixel(p.x, p.y) == targetColor) {
                    bitmap.setPixel(p.x, p.y, newColor);
                    canvas.PaintColor(newColor);
                    canvas.DrawCircle(p.x, p.y, radius, true);

                    // Add points to the queue with the specified density
                    queue.add(new Point(p.x - 1, p.y));
                    queue.add(new Point(p.x + 1, p.y));
                    queue.add(new Point(p.x, p.y - 1));
                    queue.add(new Point(p.x, p.y + 1));
                }
            }
            canvas.PaintColor(oldColor);
            Log.d("ExtendedCanvas", "Flood fill completed");
        } else {
            Log.d("ExtendedCanvas", "Target color is the same as the new color"); // Log if the target color is the same as the new color
        }

        // Draws the modified bitmap back onto the Canvas
        this.view.invalidate();
        Log.d("ExtendedCanvas", "Canvas invalidated after flood fill");
    }
    
    public void floodFillDensity(int x, int y, int newColor, int density, Canvas canvas) {
        Log.d("ExtendedCanvas", "Starting floodFill"); // Log to start the flood fill
        if (canvas == null) {
            Log.d("ExtendedCanvas", "Canvas is null");
            return;
        }
        int width = canvas.Width();
        int height = canvas.Height();
        Log.d("ExtendedCanvas", "Canvas Dimensions: Width = " + width + ", Height = " + height); // Log for canvas dimensions

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.view.invalidate();


        // Copies the current state of the Canvas to the Bitmap
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = canvas.GetPixelColor(i, j);
                bitmap.setPixel(i, j, color);
            }
        }

        // Applies flood fill to the bitmap
        int targetColor = bitmap.getPixel(x, y);
        Log.d("ExtendedCanvas", "Target color: " + targetColor); // Log for target color
        if (targetColor != newColor) {
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(x, y));
            
            Log.d("ExtendedCanvas", "Starting flood fill loop");
            while (!queue.isEmpty()) {
                Point p = queue.remove();
                if (p.x >= 0 && p.x < width && p.y >= 0 && p.y < height && bitmap.getPixel(p.x, p.y) == targetColor) {
                    bitmap.setPixel(p.x, p.y, newColor);
                    canvas.SetBackgroundPixelColor(p.x, p.y, newColor);

                    // Add points to the queue with the specified density
                    if (p.x - density >= 0) queue.add(new Point(p.x - density, p.y));
                    if (p.x + density < width) queue.add(new Point(p.x + density, p.y));
                    if (p.y - density >= 0) queue.add(new Point(p.x, p.y - density));
                    if (p.y + density < height) queue.add(new Point(p.x, p.y + density));
                }
            }
            Log.d("ExtendedCanvas", "Flood fill completed");
        } else {
            Log.d("ExtendedCanvas", "Target color is the same as the new color"); // Log if the target color is the same as the new color
        }

        // Draws the modified bitmap back onto the Canvas
        this.view.invalidate();
        Log.d("ExtendedCanvas", "Canvas invalidated after flood fill");
    }
    
    public void floodFillPontSizeDensity(int x, int y, int newColor, int pointSize, int density, Canvas canvas) {
        Log.d("ExtendedCanvas", "Starting floodFill"); // Log to start the flood fill
        if (canvas == null) {
            Log.d("ExtendedCanvas", "Canvas is null");
            return;
        }
        int width = canvas.Width();
        int height = canvas.Height();
        Log.d("ExtendedCanvas", "Canvas Dimensions: Width = " + width + ", Height = " + height); // Log for canvas dimensions

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.view.invalidate();


        // Copies the current state of the Canvas to the Bitmap
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = canvas.GetPixelColor(i, j);
                bitmap.setPixel(i, j, color);
            }
        }

        // Applies flood fill to the bitmap
        int targetColor = bitmap.getPixel(x, y);
        Log.d("ExtendedCanvas", "Target color: " + targetColor); // Log for target color
        if (targetColor != newColor) {
        	Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(x, y));

            while (!queue.isEmpty()) {
                Point p = queue.remove();
                if (isValidPoint(p.x, p.y, width, height, targetColor, bitmap)) {
                    paintSquare(p.x, p.y, pointSize, newColor, width, height, bitmap, canvas);

                    // Adicionar pontos com a densidade especificada, sem verificar se já foram visitados
                    addPointWithDensity(queue, p.x - density, p.y, width, height);
                    addPointWithDensity(queue, p.x + density, p.y, width, height);
                    addPointWithDensity(queue, p.x, p.y - density, width, height);
                    addPointWithDensity(queue, p.x, p.y + density, width, height);
                }
            }
            Log.d("ExtendedCanvas", "Flood fill completed");
        } else {
            Log.d("ExtendedCanvas", "Target color is the same as the new color"); // Log if the target color is the same as the new color
        }

        // Draws the modified bitmap back onto the Canvas
        this.view.invalidate();
        Log.d("ExtendedCanvas", "Canvas invalidated after flood fill");
    }
    
    private void paintSquare(int x, int y, int pointSize, int color, int width, int height, Bitmap bitmap, Canvas canvas) {
        for (int dx = -pointSize; dx <= pointSize; dx++) {
            for (int dy = -pointSize; dy <= pointSize; dy++) {
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    bitmap.setPixel(nx, ny, color);
                    canvas.SetBackgroundPixelColor(nx, ny, color);
                }
            }
        }
        bitmap.setPixel(x, y, color);
        canvas.SetBackgroundPixelColor(x, y, color);
    }
    
    private void addPointWithDensity(Queue<Point> queue, int x, int y, int width, int height) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            queue.add(new Point(x, y));
        }
    }

    private boolean isValidPoint(int x, int y, int width, int height, int targetColor, Bitmap bitmap) {
        return x >= 0 && x < width && y >= 0 && y < height && bitmap.getPixel(x, y) == targetColor;
    }
}    