import json
import opendb


# connect to the database

conn = opendb.conn

# get all halls and seats from the db

halls = conn.execute("SELECT * FROM halls").fetchall()

seats = conn.execute("SELECT * FROM seats").fetchall()

# set all halls as collections with seats 

halls_with_seats = []

for hall in halls:
    hall_with_seats = {}
    hall_with_seats['hall_name'] = hall[0]
    hall_with_seats['background_path'] = hall[1]
    hall_with_seats['seats'] = []
    for seat in seats:
        if seat[1] == hall[0]:
            hall_with_seats['seats'].append(list(seat))
    halls_with_seats.append(hall_with_seats)

# save as json

with open('halls_with_seats.json', 'w') as f:
    json.dump(halls_with_seats, f)

