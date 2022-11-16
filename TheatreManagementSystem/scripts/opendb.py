import sqlalchemy

conn = sqlalchemy.create_engine('postgresql://postgres:root@localhost/theatredb')

def dump_database(conn: sqlalchemy.engine.base.Engine):
    print(conn.table_names())

    for table in conn.table_names():
        print(table)
        print(conn.execute(f"SELECT * FROM {table}").fetchall())

if __name__ == '__main__':
    dump_database(conn)