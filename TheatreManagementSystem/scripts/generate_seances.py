import opendb


# connect to the database

conn = opendb.conn


# get today's date

import datetime
import random

today = datetime.date.today()


# get performances table from the database
performances = conn.execute("SELECT * FROM performance").fetchall()

# get halls table from the database
halls = conn.execute("SELECT * FROM halls").fetchall()

DAYS_TO_FILL = 7

# generate seances from random performances for the next 7 days
# seances:  id | hall_id | performance_id | seance_date | seance_time
for i in range(DAYS_TO_FILL):
    # get random performance
    performance = random.choice(performances)
    # get id
    performance_id = performance[0]

    for i in range(3):
        # get random time
        hour = random.randint(16, 22)
        minute = random.choice([0, 15, 30, 45])
        
        # get random hall
        hall = random.choice(halls)
        # get id
        hall_id = hall[0]

        # generate seance date

        seance_date = today + datetime.timedelta(days=i)

        # generate seance time

        seance_time = datetime.time(hour, minute)

        # insert into database

        conn.execute(f"INSERT INTO seances (hall_id, performance_id, seance_date, seance_time) VALUES ('{hall_id}', {performance_id}, '{seance_date}', '{seance_time}')")

