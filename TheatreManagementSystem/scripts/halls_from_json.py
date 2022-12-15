import json
import opendb

# [{"hall_name": "Sala Numer 1", "background_path": "C:\\Users\\user\\Desktop\\hall1.png", "seats": [[47, "Sala Numer 1", "A1", 0.0, 0.0] ...


# connect to the database

conn = opendb.conn

# get halls and seats from json

with open('halls_with_seats.json', 'r') as f:
    halls_with_seats = json.load(f)


# remove all halls and seats from the database

conn.execute("DELETE FROM seats")

conn.execute("DELETE FROM halls")

# add halls and seats from json to the database

for hall in halls_with_seats:
    conn.execute("INSERT INTO halls (hall_name, background_path) VALUES ('{0}', '{1}')".format(hall['hall_name'], hall['background_path']))
    for seat in hall['seats']:
        conn.execute("INSERT INTO seats (hall_name, seat_name, pos_x, pos_y) VALUES ('{0}', '{1}', {2}, {3})".format(seat[1], seat[2], seat[3], seat[4]))


