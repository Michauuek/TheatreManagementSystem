import bpy # type: ignore
import json
import gen_grid

# generuje siedzienia z blendera

grid = []
for object in bpy.data.objects:
    name = object.name
    pos = (object.location.x, object.location.y)
    grid.append({'name': name, 'x': pos[0], 'y': pos[1]})

seats = gen_grid.gen_seats(grid)
    
json.dump(seats, open('grid-grid.json', 'w'), indent=4)




