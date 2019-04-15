import csv

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
    if AScore-BScore > 1:
        AWin = True

    #Update scores based on winner
    if AWin:
        ARank = int(ARank + AKMult * (1-AExpect))
        BRank = int(BRank + BKMult * (0-BExpect))
    else:
        ARank = int(ARank + AKMult * (0-AExpect))
        BRank = int(BRank + BKMult * (1-BExpect))

    return [ARank, BRank]

def determineMult(Rank):
    if Rank > 2400:
        return 16
    elif Rank > 2100:
        return 24
    else:
        return 32

def findCurrentElo(username):
    with open('EloRatings.csv',newline='') as csvfile:
        reader = csv.reader(csvfile,delimiter=',')
        for row in reader:
            if row[1] == username:
                return (int(row[2]))

def setElo(username,newElo):
    lines = []
    with open('EloRatings.csv', newline='') as csvfile:
        reader = csv.reader(csvfile,dialect='excel',delimiter=',')
        for row in reader:
            lines.append(row)
            if row[1] == username:
                userID = int(row[0])
                lines[userID] = [userID,username,newElo]
    with open('EloRatings.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile,delimiter=',')
        for line in lines:
            writer.writerow(line)