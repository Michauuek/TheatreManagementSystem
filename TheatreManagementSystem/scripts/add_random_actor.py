import faker
import opendb
import argparse

# add argparse

parser = argparse.ArgumentParser(description='Add random actor(s) to the database')

parser.add_argument('-n', '--number', type=int, default=1, help='number of actors to add')

args = parser.parse_args()

conn = opendb.conn

genrator = faker.Faker()

# ACTOR SCHEMA
# actor_id
# name    
# surname 

for i in range(args.number):
    # get first name
    name = genrator.first_name()
    # get last name
    surname = genrator.last_name()

    conn.execute(f"INSERT INTO actors (name, surname) VALUES ('{name}', '{surname}')")
