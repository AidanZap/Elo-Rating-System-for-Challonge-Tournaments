import config, requests, csv
from EloMethods import setNewTag, newPlayer

request = 'https://' + config.username + ':' + config.apiKey + '@api.challonge.com/v1/'
tourneyID = int(input("Enter tourney ID"))
participantList = requests.get(request + 'tournaments/' + str(tourneyID) + '/participants.json').json()

for p in participantList:
    player = p['participant']['name']
    #Flag is false if name has not been found in CSV file
    flag = False
    with open('EloRatings.csv',newline='') as csvfile:
        reader = csv.reader(csvfile,delimiter=',')
        for row in reader:
            usernames = row[1].split('|')
            if player in usernames:
                flag = True

        if not flag: #at this point, player is not on the CSV
            response = int(input("player '" + player + "' is not in CSV file. "
                                       "\n1.) Update existing username"
                                       "\n2.) Add new entry for '" + player +"'\n"))
            if response == 1:
                responseID = int(input("What is '" + player + "' CSV ID?\n"))
                setNewTag(responseID,player)
            elif response == 2:
                newPlayer(player)
            else:
                print("Invalid response")