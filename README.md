# AI Java car game
Artificial intelligence learns to drive a car by crashing it. The game is written in java!

The neural network was taken from here - https://github.com/kim-marcel/basic_neural_network

This project uses maven

# Binds

1,2,3,4,0 - speed of learning <br />
C - saving the best NeuranNetwork <br />
R - toggle ray render <br />
Z - toggle game render <br />
X - smooth lines toggle <br />
H - respawn cars <br />
UP - Increases the minimum number to show the connection between neurons <br />
DOWN - Reduces the minimum number to show the connection between neurons <br />

Mouse pressed and drag - edit road <br />

# Config

There are 2 neural network modes. <br /><br />
1 - At the output, one neuron, which is responsible for turning, learns very quickly, in 10 cycles, but the machine is always twitching. <br />
2 - There are 2 neurons at the output, it learns quite slowly, for ~10000 cycles, but the machine does not twitch

# Screens

![alt text](https://github.com/UmaltIbragimov/AI-car-game-Java-/blob/main/Screen_%231.png?raw=true)
![alt text](https://github.com/UmaltIbragimov/AI-car-game-Java-/blob/main/Screen_%232.png?raw=true)
