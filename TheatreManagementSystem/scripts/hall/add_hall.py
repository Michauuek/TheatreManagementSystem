import json

# Dodaje do `hall.json` nową sale

grid_blend = json.load(open('hall/grid-blend.json'))
grid = json.load(open('hall/halls.json', encoding='utf-8'))
hall_name = input("Hall Name:")

if hall_name in grid:
    print("Hall already exists")
    exit()

MAX_X = max([seat['x'] for seat in grid_blend])
MAX_Y = max([seat['y'] for seat in grid_blend])

grid[hall_name] = {"seats": grid_blend, "size": [MAX_X, MAX_Y], "path":"hello_world.jmg" }

json.dump(grid, open('hall/halls.json', 'w', encoding='utf-8'), ensure_ascii=False, indent=4)
