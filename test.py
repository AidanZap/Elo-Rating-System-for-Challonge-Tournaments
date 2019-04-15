import config, requests
from EloMethods import scoreMatch, findCurrentElo, setElo

request = 'https://' + config.username + ':' + config.apiKey + '@api.challonge.com/v1/'
tourneyID = int(input("Enter tourney ID"))

matchList = requests.get(request + 'tournaments/' + str(tourneyID) + '/matches.json').json()
participantList = requests.get(request + 'tournaments/' + str(tourneyID) + '/participants.json').json()

#Dictionary that matches Challonge id to username (i.e '94507523': 'Fatty')
entrants = {}
for p in participantList:
    entrants[str(p['participant']['id'])] = str(p['participant']['name'])

for m in matchList:
    player1ID = str(m['match']['player1_id'])
    player2ID = str(m['match']['player2_id'])
    player1Tag = str(entrants[player1ID])
    player2Tag = str(entrants.get(player2ID))
    player1Elo = findCurrentElo(player1Tag)
    player2Elo = findCurrentElo(player2Tag)
    player1Score = m['match']['scores_csv'].split('-')[0]
    player2Score = m['match']['scores_csv'].split('-')[1]
    returnedElos = scoreMatch(player1Elo,player1Score,player2Elo,player2Score)
    setElo(player1Tag,returnedElos[0])
    setElo(player2Tag,returnedElos[1])