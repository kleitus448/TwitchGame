import config
import urllib2
import json
import time
import thread
from time import sleep




def mess(sock,message):
	sock.send("PRIVMSG #{} :{}\r\n".format(config.CHAN,message))
	
	
	
	
	
def ban(sock,user):
	mess(sock, ".ban{}".format(user))
	
def addToPlayers(userName):
	config.Players_list.add(userName)
	
	
def timeout(sock, user,second = 500):
	mess(sock, ".timeout {}".format(user, seconds))
	
	
def fillOpList():	
	while True:
		try:
			url = "http://tmi.twitch.tv/group/user/sweet_dream5/chatters"
			req = urllib2.Request(url, headers = {"accept":"*/*"})
			res = urllib2.urlopen(req).read()
			if res.find("502 bad gateway") == -1:
				config.oplist.clear()
				data = json.loads(res)
				for p in data["chatters"]["moderators"]:
					config.oplist[p] = "mod"
				for p in data["chatters"]["global_mods"]:
					config.oplist[p] = "global_mod"
				for p in data["chatters"]["admins"]:
					config.oplist[p] = "admin"
				for p in data["chatters"]["staff"]:
					config.oplist[p] = "staff"		
		except:	
			"Something wrong. Chill... "
		sleep(5) 

def isPlayer(user):
	return user in config.Players_list			
