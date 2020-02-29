# Lightning (+ continents)

If lightning follows the path of least resistance, then why doesn't it go down in a straight line? Why don't rivers flow from mountaintops to the sea in a straight path?

I think the answer is that neither current nor water can "see" the overall shortest path. They only "see" what is immediately around them, and simply fall down on the easiest path. This creates many patterns we see in nature such as trees, lightning, rivers, even continent formations.

To test my idea, I designed an algorithm that simulates nature's tendency to follow the path of least resistance. The screen is divided into points of class Unit, which have a coordinate and a conductance value. From a given coordinate, the program only "sees" the values of adjacent Units. Among those, it chooses the one with the highest conductance value. It marks the Unit it came from as "passed", draws a line from the current point to the chosen point, passes on to the chosen point and repeats the process until a certain maximum number of loops is reached.

Dependencies: ACM

Todo:
1- Find a better graphics package than ACM
2- Better OOP
