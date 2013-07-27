README

CSE 3521 Project 2
Tom Gleghorn
4/5/2013

The project compiles properly on stdlinux using javac. TicTacToe is the executable,
and all of the search value reporting happens in the program, so I won't reprint it here.

My H-Minimax values are not identical to Professor Hamm's, so it is possible that
my heuristic is not exactly the one specified, but I believe that it functions very
similarly. For a given state, I explore every row, column, and diagonal, and award/subtract
points based on the number of the player's squares and the number of their opponent's squares.
The values added/subtracted are +-3 for two like squares, +-1 for a single square. The values
I'm getting are similar to the ones Professor Hamm provided, but not exact, and I'm not sure
what I missed.