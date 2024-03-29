# ShortestPath
Author: Jason Liu, Yifei Zhang
Date March 17th, 2019

This is a program used to visualize the shortest path between two points on a stl 3D model. 

Before running this program, place the stl model in the folder, and rename it "model.stl". (The program can process 3D models with up to 1,000,000 unique vertices and up to 5,000,000 unique edges.) Edit the "config.txt" file to locate the start and end point of the path. The coordinates do not have to be exact. The program will find the closest point on the model. (A sample model and configuration file are included in the folder for testing.) 

To find the path, run "STLParser.jar" first. Then run "dijkstra". (Only the 64 bit Linux binary file is provided. However, you can compile "dijkstra.cpp" in the source folder yourself.) Then, run "visulization.m" in MATLAB. 

Note that this program cannot be used to calculate the exact shortest path or its length. The the length of the path will always be overestimated, and will not converge to the length of the actual shortest path regardless of the precision of the triangulation. It should be used for visualization purpose only. The function of calculating the exact shortest path is being worked on. 

"stlread.m" is the work of Copyright (c) 2011, Eric Johnson

"csvimport.m" is the work of Copyright (c) 2008, Ashish Sadanandan

This program is under the MIT License. 
The authors are not responsible for serious injuries or death caused by this program. 
