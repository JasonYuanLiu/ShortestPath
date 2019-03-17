% Modefied from 3D Model Demo by The MathWorks, Inc.

[X,Y,Z] = csvimport('path.csv', 'columns', {'X', 'Y', 'Z'});

X(1) = [];
Y(1) = [];
Z(1) = [];

fv = stlread('model.stl');

plot3(X,Y,Z,'Color',[1.0,0.3,0.3]);
patch(fv,'FaceColor',       [0.4,0.4,1.0], ...
         'EdgeColor',       'none',        ...
         'FaceLighting',    'gouraud',     ...
         'AmbientStrength', 0.15);

camlight('headlight');
material('dull');

axis('image');
view([-135 35]);
