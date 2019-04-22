import csv

#Pass in player A and B match scores and elo ratings, the resulting elo ratings are returned in a list
def scoreMatch(ARank, AScore, BRank, BScore):
    ARank = int(ARank)
    BRank = int(BRank)
    AScore = int(AScore)
    BScore = int(BScore)
    AExpect = 1 / (1 + 10 ** ((BRank - ARank) / 400))
    BExpect = 1 / (1 + 10 ** ((ARank - BRank) / 400))
    AKMult = determineMult(ARank)
    BKMult = determineMult(BRank)

    AWin = False
    if AScore > BScore:
        AWin = True

    #Update scores based on winner
    if AWin:
        ARank = int(ARank + AKMult * (1-AExpect))
        BRank = int(BRank + BKMult * (0-BExpect))
    else:
        ARank = int(ARank + AKMult * (0-AExpect))
        BRank = int(BRank + BKMult * (1-BExpect))

    return [ARank, BRank]

#Used by scorematch
def determineMult(Rank):
    if Rank > 1400:
        return 16
    elif Rank > 1200:
        return 24
    else:
        return 32

#Return current elo of a player
def findCurrentElo(username):
    with open('EloRatings.csv',newline='') as csvfile:
        reader = csv.reader(csvfile,delimiter=',')
        for row in reader:
            usernames = row[1].split('|')
            if username in usernames:
                return (int(row[2]))

#Set elo for a given username
def setElo(username,newElo):
    lines = []
    with open('EloRatings.csv', newline='') as csvfile:
        reader = csv.reader(csvfile,dialect='excel',delimiter=',')
        for row in reader:
            lines.append(row)
            usernames = row[1].split('|')
            if username in usernames:
                userID = int(row[0])
                lines[userID] = [userID,row[1],newElo]
    with open('EloRatings.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile,delimiter=',')
        for line in lines:
            writer.writerow(line)

#Set new tag for specified ID
def setNewTag(ID, newTag):
    lines = []
    with open('EloRatings.csv', newline='') as csvfile:
        reader = csv.reader(csvfile, dialect='excel', delimiter=',')
        for row in reader:
            lines.append(row)
            if str(ID) == row[0]:
                newTags = row[1] + '|' + newTag
                lines[ID] = [ID, newTags, row[2]]
    with open('EloRatings.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile, delimiter=',')
        for line in lines:
            writer.writerow(line)

#Retrieve last ID in the CSV file
def getIDCount():
    lastID = -1
    with open('EloRatings.csv', newline='') as csvfile:
        reader = csv.reader(csvfile, dialect='excel', delimiter=',')
        for row in reader:
            lastID+=1
    return lastID

#Create a new player with set username
def newPlayer(username):
    with open(r'EloRatings.csv', 'a', newline='') as f:
        writer = csv.writer(f)
        writer.writerow([getIDCount()+1,username,1000])