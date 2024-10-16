# Animal's Revenge
"Animal’s Revenge" is an interactive physics simulator focused on projectile motion and rigid body dynamics (RBD), inspired by _Angry Birds_. Players can launch projectiles at user-designed structures created through a drag-and-drop system, fostering creativity. Users have control over projectile characteristics such as type, size, density, and shape, and can save and load custom levels. The simulation features a domino effect where projectile collisions trigger movement in interconnected obstacles. This dynamic interaction is powered by a physics engine, utilizing mathematical algorithms for projectile motion, kinematics, and RBD, including the Pythagorean theorem, trigonometric functions, and classical mechanics.

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

## Getting started
### Prerequisites
Before you can build and run this project, ensure you have the following software installed on your system:
- [Oracle OpenJDK 18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
- [Git](https://git-scm.com/downloads)

### Installation
1. Clone the repository
```shell
git clone https://github.com/tran-ethan/animals-revenge.git
```
2. Navigate to the project directory
```shell
cd animals-revenge
```
3. Build the project
```shell
./gradlew build
```

## Usage
### Using Gradle Wrapper
Make sure Oracle OpenJDK 18.0.2 is properly configured in the `JAVA_HOME` environment variable before trying this method. To run the application using the Gradle Wrapper, execute the following command:
```shell
./gradlew run
```

### Using IDE
You can run the program by directly executing the `main` method in the `MainApp` class directly from within your IDE. Ensure your IDE is configured to use the JDK 18 and has the necessary dependencies in `build.gradle` installed.

## License
This project is licensed under the [MIT License](LICENSE)
