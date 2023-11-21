package com.bosonshiggs.extendedcanvas;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.YailList;

import com.bosonshiggs.extendedcanvas.helpers.FloodFillHandler;

import android.util.Log;

@DesignerComponent(
    version = 1,
    description = "Extended Canvas with flood fill functionality",
    category = ComponentCategory.EXTENSION,
    nonVisible = true,
    iconName = "images/extension.png"
)
@SimpleObject(external = true)
public class ExtendedCanvas extends AndroidNonvisibleComponent {
    private FloodFillHandler floodFillHandler;
    private Canvas canvasComponent; // Reference to the Kodular Canvas component
    
    public ExtendedCanvas(ComponentContainer container) {
        super(container.$form());
        floodFillHandler = new FloodFillHandler();
    }

    @SimpleFunction(description = "Perform flood fill from a point with a specified color. "
    		+ "A spacing value of 1 will fill every adjacent point (as it was originally doing), "
    		+ " while a higher value will leave more space between the filled points, creating a sparser fill pattern.")
    public void floodFillDensity(
    		final int x, 
    		final int y, 
    		final int newColor, 
    		final int density) 
    {
    	new Thread(new Runnable() {
            @Override
            public void run() {
            	floodFillHandler.floodFillDensity(x, y, newColor, density, canvasComponent); // Now the canvas reference is passed correctly
            }
        }).start();
    }
    
    @SimpleFunction(description = "Perform flood fill from a point with a specified color. "
    		+ "A spacing value of 1 will fill every adjacent point (as it was originally doing), "
    		+ " while a higher value will leave more space between the filled points, creating a sparser fill pattern.\n"
    		+ "For example, a pointSize of 1 will paint a 3x3 pixel square (the center point plus one pixel in each direction). "
    		+ "Adjust this value as needed to get the desired dot size")
    public void floodFillPontSizeDensity(
    		final int x, 
    		final int y, 
    		final int newColor, 
    		final int pointSize, 
    		final int density) 
    {
    	new Thread(new Runnable() {
            @Override
            public void run() {
            	floodFillHandler.floodFillPontSizeDensity(x, y, newColor, pointSize, density, canvasComponent); // Now the canvas reference is passed correctly
		    }
		}).start();
    }
    
    @SimpleFunction(description = "Perform flood fill from a point with a specified color.")
    public void floodFillCircle(
    		final int x, 
    		final int y, 
    		final int newColor, 
    		final float radius) 
    {
    	new Thread(new Runnable() {
            @Override
            public void run() {
            	floodFillHandler.floodFillCircle(x, y, newColor, radius, canvasComponent); // Now the canvas reference is passed correctly
		    }
		}).start();
    }

    @SimpleProperty(description = "Set the Canvas component used for painting.")
    public void SetCanvas(Canvas canvas) {
        this.canvasComponent = canvas; // Updates the canvasComponent reference
        floodFillHandler.setCanvas(canvas);
    }  
}
