import sqlite3
HOST = "irc.twitch.tv"
PORT = 6667
NICK = "WhatABotw"
PASS = "oauth:ukutonv79xf5o0g0m37hzdmt4o30sl"
CHAN = "sweet_dream5"
Rate = (20/30)       
Players_list = set()
oplist = ()
empty = list()

co = sqlite3.connect("database.db")

cursor = co.cursor()
search = "SELECT user FROM users "
cursor.execute(search)
Players_list.add(tuple(cursor.fetchall()))
co.commit()
co.close()


