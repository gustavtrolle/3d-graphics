# 3-D graphics display

This program displays a 3D prism. 

![Display example](/images/3d_display.gif) 

The program is developed with the javax.swing (JFrame) and java.awt.event packages.

The edges are stored as double arrays in an ArrayList<double[]>. These arrays represent the absolute positions of the edges (specifically their two vertices) in 3-D space.
The x-y-z position, horizontal and vertical rotational angles, and the horizontal and vertical fields of view of the display are stored in variables.
As the user moves the mouse and/or presses the movement keys (WASD), these variables change and the program updates the display.

When the display is updated, the original vertices of the prism are first translated according to the display's x-y-z position.
The translated vertices are then rotated according to the display's rotational angles. These rotations are calculated with linear algebra (matrix transformations).
The rotated vertices are then scaled down along the x and y axes, according to their ratio (along the axes) with the fields of view.
Finally, the scaled vertices are projected onto the display (i.e. the z-coordinate is 0).

When the user moves the mouse off the screen, the mouse is moved to the other side of the screen, while the parameter variables of the display remain the same.