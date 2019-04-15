import requests, config

request = 'https://' + config.username + ':' + config.apiKey + '@api.challonge.com/v1/'

r = requests.get(request + 'tournaments.json').json()
for tourney in r:
    print(tourney['tournament']['name'] + ' : ' + str(tourney['tournament']['id']))