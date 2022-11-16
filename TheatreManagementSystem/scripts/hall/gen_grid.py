import numpy as np
import xlsxwriter
import json

# generuje siedzenia w gridzie

GRID_START_X = 10
GRID_SIZE_X = 590
GRID_COUNT_X = 30

GRID_START_Y = 10
GRID_SIZE_Y = 390
GRID_COUNT_Y = 20

def gen_grid(startX, startY, sizeX, sizeY, countX, countY):
    X = np.linspace(startX, sizeX, countX, dtype=np.int32)
    Y = np.linspace(startY, sizeY, countY, dtype=np.int32)

    # combine X and Y
    grid = np.array(np.meshgrid(X, Y)).T.reshape(-1, 2)

    return grid

def cord_normalized(cord):
    return (int(cord[0] / GRID_COUNT_X), int(cord[1]/GRID_COUNT_Y))

def gen_name(cords):
    x, y = cord_normalized(cords)
    xl = xlsxwriter.utility.xl_col_to_name(x) #type: ignore
    return xl + '-' + str(y)

def gen_seats(grid):
    seats = []
    for seat in grid:
        seats.append({
            'x': int(seat[0]),
            'y': int(seat[1]),
            'name': gen_name(seat)
        })
    return seats

if __name__ == '__main__':
    grid = gen_grid(GRID_START_X, GRID_SIZE_X, GRID_COUNT_X, 
                    GRID_START_Y, GRID_SIZE_Y, GRID_COUNT_Y)
    seats = gen_seats(grid)
    
    json.dump(seats, open('seats.json', 'w'), indent=4)
