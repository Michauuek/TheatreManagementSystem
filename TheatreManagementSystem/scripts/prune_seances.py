import opendb

# connect to the database

conn = opendb.conn

# drop all data from reservedseats table
conn.execute("DELETE FROM reservedseats")

# drop all data from reservations table
conn.execute("DELETE FROM reservation")

# drop all data from seances table
conn.execute("DELETE FROM seances")


