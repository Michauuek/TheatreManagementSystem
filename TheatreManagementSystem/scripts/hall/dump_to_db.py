import opendb
import cv2
import json
import numpy as np

# Wpisuje `hall.json` do bazy danych

RESTART_TABLE = True
USE_IMG = False

halls = json.load(open('halls.json'))
conn = opendb.conn

cv2.namedWindow('image', cv2.WINDOW_NORMAL)

if RESTART_TABLE:
    conn.execute("DELATE FROM halls")

for name, hall in halls.items():
    seats = hall['seats']
    url = hall['url']
    size = hall['size']
    
    if USE_IMG:
        img = cv2.imread(url)
    else:
        img = np.zeros((size[0], size[1], 3), np.uint8)

    for seat in seats:
        center = (seat['x'], seat['y'])
        seat_name = seat['name']

        img = cv2.circle(img, center, 5, (255, 255, 255), -1)

    cv2.imshow('img', img)
    cv2.waitKey(-1)

    # TODO 
    








