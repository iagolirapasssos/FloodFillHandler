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