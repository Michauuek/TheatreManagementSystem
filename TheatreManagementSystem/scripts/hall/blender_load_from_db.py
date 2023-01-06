import bpy # type: ignore
import json
import os
import sys
import pip
python_exe = os.path.join(sys.prefix, 'bin', 'python.exe')
# C:\Program Files\blender3-0\3.0\python\bin\python.exe

target = os.path.join(sys.prefix, 'lib', 'site-packages')
# C:\Program Files\blender3-0\3.0\python\lib\site-packages
import subprocess
 
try:
    import psycopg2 as psycopg2
except ImportError:
    subprocess.call([python_exe, '-m', 'ensurepip'])
    subprocess.call([python_exe, '-m', 'pip', 'install', '--upgrade', 'pip'])

    def install(package):
        subprocess.call([python_exe, '-m', 'pip', 'install', package, '-t', target])

    install("SQLAlchemy")
    install("psycopg2")

current = os.path.dirname(os.path.realpath(__file__))
print(os.path.dirname(os.path.dirname(current)))
sys.path.append(os.path.dirname(os.path.dirname(current)))
import opendb

# remove all objects and collections from the scene 

bpy.ops.object.select_all(action='SELECT')
bpy.ops.object.delete(use_global=False)
for collection in  bpy.data.collections:
    bpy.data.collections.remove(collection)


# connect to the database

conn = opendb.conn

# testing - add one hall to the database

# conn.execute("INSERT INTO halls (hall_name, background_path) VALUES ('hall1', 'C:\\Users\\user\\Desktop\\hall1.png')")

# get hall table from the database

hall = conn.execute("SELECT * FROM halls").fetchall()

print(hall)

# hall attributes:
# * hall_name
# * background_path
# create a collection for each hall, set parent to scene collection

for h in hall:
    bpy.ops.collection.create(name=h[0])

# collections are not in the scene by default, so we need to add them

for collection in bpy.data.collections:
    bpy.context.scene.collection.children.link(collection)


# get seats table from the database

# add one seat to hall1 as test
#conn.execute("INSERT INTO seats (hall_name, seat_name, pos_x, pos_y) VALUES ('hall1', 'seat1', 0, 0)")


seats = conn.execute("SELECT * FROM seats").fetchall()

# seat attributes:
# * seat_id
# * hall_name
# * seat_name
# * posX
# * posY

# change list of tupels to list of dicts
seats = conn.execute("SELECT * FROM seats").fetchall()

# add seats to collections of halls

seats = [dict(zip([key[0] for key in conn.execute("SELECT * FROM seats").cursor.description], row)) for row in seats]
# example output [{'seat_id': 1, 'hall_name': 'hall1', 'seat_name': 'seat1', 'pos_x': 0.0, 'pos_y': 0.0}]

# add seats as cube to collections of halls
for seat in seats:
    # add cube
    bpy.ops.mesh.primitive_cube_add(
        size=1, 
        enter_editmode=False, 
        align='WORLD', 
        location=(seat['pos_x'], seat['pos_y'], 0), 
        scale=(1, 1, 1)
        )
    # rename cube
    bpy.data.objects['Cube'].name = seat['seat_name']

    obj = bpy.context.active_object
    # Remove object from all collections not used in a scene
    bpy.ops.collection.objects_remove_all()
    # add it to HALL COLLECTION
    bpy.data.collections[seat['hall_name']].objects.link(obj)
    