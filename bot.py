import config
import utils
import socket
import re
import time
import thread
from time import sleep
import sqlite3
import os

def main():
	co = sqlite3.connect("database.db")
	cursor = co.cursor()
	s = socket.socket()

	s.connect((config.HOST,config.PORT))
	s.send("PASS {}\r\n".format(config.PASS).encode("utf-8"))
	s.send("NICK {}\r\n".format(config.NICK).encode("utf-8"))
	s.send("JOIN #{}\r\n".format(config.CHAN).encode("utf-8"))
	

	chat_message = re.compile(r"^:\w+!\w+@\w+\.tmi\.twitch\.tv PRIVMSG #\w+ :")
	utils.mess(s, "Hey, what is up?")
	thread.start_new_thread(utils.fillOpList, ())
	while True:
		response = s.recv(1024).decode("utf-8")
		if response == "PING :tmi.twitch.tv\r\n":
			s.send("PONG :tmi.twitch.tv\r\n").encode("utf-8")
		else:
			username = re.search(r"\w+", response).group(0)
			message = chat_message.sub("", response)
			print(response)
			if message.strip() == "@WhatABotw !time":
				utils.mess(s, "it's currently: " + time.strftime("%I:%M %p %Z on %A %B %d %Y"))
			if message.strip() == "@WhatABotw !I want to play" :
				User_name = username.encode("utf-8")
				if  not (utils.isPlayer(username)):
					utils.mess(s,"/w " + username + " Lets play! " + "What is your rasa and name? (type in chat of streaming chanell)")
					utils.addToPlayers(User_name)
			if "@WhatABotw !My name is " in message.strip() and "and my rasa is " in message.strip():			
				cursor.execute("SELECT * FROM users WHERE user = ?", (username,))
				co.commit()
				if cursor.fetchall() == config.empty:
					if utils.isPlayer(username.encode("utf-8")):				
						information = (mndDessage.strip().replace("!My name is ", '')).replace("and my rasa is", '')
						rasa = (information.split(' ')[-1]).encode("utf-8")
						name = (information.replace(rasa,'')).replace(" ", '').encode("utf-8")
						utils.mess(s, "/w " + username + " Now you are one of us, " + name)
						print(User_name, name, rasa)
						cursor.execute("INSERT INTO users(user, nickname, rasa, level) VALUES (?,?,?,?)", (User_name, name, rasa, 1))
						co.commit()						
				else: print ("He is in base already")	
				print("Here's a listing of all the records in the table:")
				cursor.execute("SELECT * FROM users")
				print(cursor.fetchall())
				co.commit()
				print(list(config.Players_list))
			if message.strip() == "@WhatABotw !stop":
				break	
		sleep(1)

if __name__ == "__main__":
	main()		
