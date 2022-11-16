import opendb
import cv2
import json

halls = json.load(open('halls.json'))
conn = opendb.conn

for hall in halls:
    seats = hall['seats']
    url = hall['url']

    






