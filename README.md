# FloodFillHandler
The FloodFillHandler class, part of the ExtendedCanvas package, provides advanced flood fill functionalities for a custom canvas in Android applications developed with Google App Inventor or similar platforms.


# Extended Canvas Flood Fill Handler

The `FloodFillHandler` class, part of the `ExtendedCanvas` package, provides advanced flood fill functionalities for a custom canvas in Android applications developed with Google App Inventor or similar platforms. This class offers different methods to perform flood fill, each catering to specific needs, such as filling with density control, circle-based filling, and point-size-density-based filling.

## Features

- **Custom Flood Fill Implementations**: Different methods to perform flood fill on a canvas with various approaches.
- **Efficient Bitmap Handling**: Utilizes `Bitmap` for pixel manipulation, ensuring efficient handling of canvas drawing operations.
- **Dynamic Filling**: Supports filling with adjustable parameters like color, density, point size, and radius.
- **Logging for Debugging**: Integrated logging for monitoring the flood fill process and troubleshooting.

## Methods

### `setCanvas(Canvas canvas)`

Sets the current working canvas to the provided `Canvas` object.

### `floodFillCircle(int x, int y, int newColor, float radius, Canvas canvas)`

Performs a flood fill operation starting from a specified point `(x, y)` on the canvas, using a circular approach based on the specified `radius`. The method fills the area with `newColor`.

### `floodFillDensity(int x, int y, int newColor, int density, Canvas canvas)`

Executes a flood fill from the point `(x, y)` with a specified `density`. This method allows for controlled spread of the flood fill, limiting the fill rate based on the `density` parameter.

### `floodFillPontSizeDensity(int x, int y, int newColor, int pointSize, int density, Canvas canvas)`

Combines point size and density parameters to perform a flood fill. This method is particularly useful when a more granular control over the flood fill pattern and spread is required.

### Memory and Recycling

- Bitmap Re-creation: After clearing the memory with clearMemory(), this.bitmap is set to null. Therefore, before using this.bitmap again (for example, in saveCurrentState() or fastFloodFill()), you need to recreate it if it is null.
- Conditional Recycling: In saveCurrentState(), before recycling the last state of the undoStack stack, it checks if it is not the same as this.bitmap to avoid recycling a bitmap that is still in use.

### Issue Addressed

The original code faced issues with recycled bitmaps, especially when attempting to repaint the same area multiple times. This led to runtime errors due to the use of bitmaps that had already been recycled.

**Key Improvements:**

1. **Modified `saveCurrentState` Method:**
   - The method now creates a copy of the current bitmap state before pushing it onto the undo stack.
   - This change prevents the recycling of the bitmap currently in use, which was causing the error when trying to repaint the same area.

2. **Improved Bitmap Management:**
   - Additional checks were implemented to ensure that a bitmap is not recycled if it's still in use or might be used again soon.
   - The code now carefully manages the lifecycle of bitmaps, especially when dealing with undo and redo stacks.

3. **Stack Management:**
   - The undo and redo stacks now handle bitmap states more reliably. When a state is pushed to the stack, it's a fresh copy, ensuring the original bitmap can be safely used or recycled without affecting the stack's integrity.

4. **Handling Recycled Bitmaps:**
   - Introduced checks for `isRecycled()` on bitmaps before their use to prevent runtime errors.
   - If a bitmap is found to be recycled when it's needed, the code now handles the scenario by creating a new bitmap.

5. **Optimizations for Performance and Memory Usage:**
   - Suggested optimizations to reduce memory consumption by reusing bitmaps where possible.
   - Advised on monitoring the quantity of bitmaps in memory to avoid `OutOfMemoryError`.

These improvements aim to enhance the application's stability, particularly when performing repetitive bitmap operations such as repainting the same area multiple times.


## Usage

To use the `FloodFillHandler` in your Android project:

1. Instantiate `FloodFillHandler`.
2. Set the canvas using `setCanvas`.
3. Call the desired flood fill method (`floodFillCircle`, `floodFillDensity`, or `floodFillPontSizeDensity`) with appropriate parameters.

## Example

```java
FloodFillHandler fillHandler = new FloodFillHandler();
fillHandler.setCanvas(yourCanvasInstance);
fillHandler.floodFillDensity(50, 50, Color.RED, 5, yourCanvasInstance);
```

## Notes

- Ensure the canvas is properly initialized before passing it to the flood fill methods.
- Adjust the parameters according to the specific requirements of your application for optimal results.

---

This description provides a comprehensive overview of the `FloodFillHandler` class and its capabilities. You can adjust or expand this description as needed to fit the broader context of your project or to include additional details about your implementation.