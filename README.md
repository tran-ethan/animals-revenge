# Animal's Revenge
Animal’s revenge is an immersive interactive graphical physics simulator centered around
projectile motion and rigid body dynamics (RBD) inspired by _Angry Birds_. Upon startup, users are greeted by a
home screen with a variety of buttons and menus, determining where they want to go and
what they want to do. User launch a projectile at a structure that was built with a drag
and drop system allowing users to have invoke their creativity and design unique structures.
Users have full control over the type of projectiles, their size, density, shape, etc. and are
able to save the layout as a level and load it for later use. The projectile collisions with the
obstacles will cause a ripple (domino) effect with the obstacles that it collides, one causing
the movement of the next. The movement of these interconnected bodies under the action of
external forces (RBD) acting upon one another was implemented with the help of a physics
engine. The mathematical algorithms necessary to implement projectile motion, kinematics,
and rigid body dynamics include the Pythagorean theorem, trigonometric functions, and the
derivation of acceleration from velocity.

https://github.com/user-attachments/assets/726f6cc0-e101-4df8-a127-65adc74e0767
## Features
- **Projectile motion in 2D**: Users will be able to launch projectiles along a specific initial
velocity vector. They will be able to manipulate that vector in the simulator
(customizing its direction, velocity magnitude, and angle)
- **Drag-and-drop obstacles**: Users are able to spawn in obstacles into the simulator based
on selected parameters. These parameters include, the shape, the texture, the friction,
the rotation and the size. By pressing `CTRL` + `LMB` , the users will spawn an obstacle
with the specified parameters at the position of their cursor
- **Rigid-body dynamics**: The application effectively simulates highly sophisticated rigidbody
dynamics and forces between obstacles and projectiles with the help of a physics
engine
- **Kinematics Graphs**: When a user launches the projectile by clicking on the launch button,
a window with 3 tabs of kinematics graphs appears, including $$y$$ position, velocity
$$v_y$$, and acceleration $$a_y$$ versus time $$t$$, which describes the motion of the projectile
- **Custom projectiles**: Users are given the ability to create and customize their very own
projectile. They are able to choose the shape of the projectile, the size and the colour.
Furthermore, users are also able to customize non-physical properties of the projectile
such as its restitution (how bouncy something is) and its density. Allowing the user
to have these options gives them to ability to learn about projectile motion and how
changing such parameters can affect it
- **Parameter customization**: Users are able to open the Parameters window to change
some of the parameters of the simulator. These include the value of gravity and whether
the graphs are turned on or off
- **Save and load**: Users can save a certain layout of obstacles into a file (this is essentially
a preset level), such that they can load that same layout in the future even if they
close the application
- **Settings**: User can modify background, music and sound style and volume. Those
modification are automatically saved, and after exiting and restarting program, users
preferences will be saved.


## Documentation
- [Final project report](documentation/MAZE-Animals-Revenge-Project-Report.pdf)
- [Project proposal](documentation/MAZE-Project-proposal.pdf)
- [Project plan](documentation/MAZE-Project-plan.pdf)
- [Team attribution table](documentation/MAZE-Attribution-Table.pdf)
