public class ScoreMatch {
   private static Player playerA;         //First player passed in
   private static Player playerB;         //Second player passed in
   private static int playerAWins;        //First players wins in the set
   private static int playerBWins;        //Second players wins in the set
   private static double HExpectedScore;  //Expected score for the higher player, used in MMR calculation
   private static double LExpectedScore;  //Expected score for the lower player, used in MMR calculation
   private static int multiplier;         //If the player's rating is <2400, multiplier is 40.  Otherwise is 20

/*Constructor with two passed in opponents and their respective win/lose record for the match
For example, if player "Fatty" played "Jeff" and won the set 2-1, you would pass in
ScoreMatch(Fatty,Jeff,2,1);
ScoreMatch(Jeff,Fatty,1,2); would also work
*/
public static void ScoreMatch(Player a, Player b, int aWins, int bWins) {
   playerA = a;
   playerB = b;
   playerAWins = aWins;
   playerBWins = bWins;
   
   /*Based on the difference of the players ratings, the MMR is calculated below with the HExpected & LExpected values
   General Formula: Scoredifference = score - expectedscore * multiplier, where
   score = 1 if you won, and 0 if you lost
   expectedscore is the HExpected and LExpected, respectively for the higher rated and lower rated player
   */
   determineHExpected(Math.abs(playerA.getRating() - playerB.getRating()));
   
   if (playerA.getRating() > playerB.getRating()) {
      //A Wins, A has higher rating
      if(playerAWins > playerBWins) {
         if(playerA.getRating()>2400)
            playerA.setRating(playerA.getRating() + ((1 - HExpectedScore)*20));
         else
            playerA.setRating(playerA.getRating() + ((1 - HExpectedScore)*40));
         if(playerB.getRating()>2400)
            playerB.setRating(playerB.getRating() + ((0 - LExpectedScore)*20));
         else
            playerB.setRating(playerB.getRating() + ((0 - LExpectedScore)*40));
      }
   
      else {
         //B wins, A has higher rating
         if(playerA.getRating()>2400)
            playerA.setRating(playerA.getRating() + ((0 - HExpectedScore)*20));
         else
            playerA.setRating(playerA.getRating() + ((0 - HExpectedScore)*40));
         if(playerB.getRating()>2400)
            playerB.setRating(playerB.getRating() + ((1 - LExpectedScore)*20));
         else
            playerB.setRating(playerB.getRating() + ((1 - LExpectedScore)*40));
      }
   }
   
   else {
      //A wins, B has higher rating
      if(playerAWins > playerBWins) {
         if(playerA.getRating()>2400)
            playerA.setRating(playerA.getRating() + ((1 - LExpectedScore)*20));
         else
            playerA.setRating(playerA.getRating() + ((1 - LExpectedScore)*40));
         if(playerB.getRating()>2400)
            playerB.setRating(playerB.getRating() + ((0 - HExpectedScore)*20));
         else
            playerB.setRating(playerB.getRating() + ((0 - HExpectedScore)*40));
      }
   
      else {
         //B wins, B has higher rating
         if(playerA.getRating()>2400)
            playerA.setRating(playerA.getRating() + ((0 - LExpectedScore)*20));
         else
            playerA.setRating(playerA.getRating() + ((0 - LExpectedScore)*40));
         if(playerB.getRating()>2400)
            playerB.setRating(playerB.getRating() + ((1 - HExpectedScore)*20));
         else
            playerB.setRating(playerB.getRating() + ((1 - HExpectedScore)*40));
      }
   }
}

//Manual calculation for MMR
public static void determineHExpected (double difference) {
   if (difference<=3)
      HExpectedScore = 0.5;
   else if (difference<=10)
      HExpectedScore = 0.51;
   else if (difference<=17)
      HExpectedScore = 0.52;
   else if (difference<=25)
      HExpectedScore = 0.53;
   else if (difference<=32)
      HExpectedScore = 0.54;
   else if (difference<=39)
      HExpectedScore = 0.55;
   else if (difference<=46)
      HExpectedScore = 0.56;
   else if (difference<=53)
      HExpectedScore = 0.57;
   else if (difference<=61)
      HExpectedScore = 0.58;
   else if (difference<=68)
      HExpectedScore = 0.59;
   else if (difference<=76)
      HExpectedScore = 0.6;
   else if (difference<=83)
      HExpectedScore = 0.61;
   else if (difference<=91)
      HExpectedScore = 0.62;
   else if (difference<=98)
      HExpectedScore = 0.63;
   else if (difference<=106)
      HExpectedScore = 0.64;
   else if (difference<=113)
      HExpectedScore = 0.65;
   else if (difference<=121)
      HExpectedScore = 0.66;
   else if (difference<=129)
      HExpectedScore = 0.67;
   else if (difference<=137)
      HExpectedScore = 0.68;
   else if (difference<=145)
      HExpectedScore = 0.69;
   else if (difference<=153)
      HExpectedScore = 0.70;
   else if (difference<=162)
      HExpectedScore = 0.71;
   else if (difference<=170)
      HExpectedScore = 0.72;
   else if (difference<=179)
      HExpectedScore = 0.73;
   else if (difference<=188)
      HExpectedScore = 0.74;
   else if (difference<=197)
      HExpectedScore = 0.75;
   else if (difference<=206)
      HExpectedScore = 0.76;
   else if (difference<=215)
      HExpectedScore = 0.77;
   else if (difference<=225)
      HExpectedScore = 0.78;
   else if (difference<=235)
      HExpectedScore = 0.79;
   else if (difference<=245)
      HExpectedScore = 0.8;
   else if (difference<=256)
      HExpectedScore = 0.81;
   else if (difference<=267)
      HExpectedScore = 0.82;
   else if (difference<=278)
      HExpectedScore = 0.83;
   else if (difference<=290)
      HExpectedScore = 0.84;
   else if (difference<=302)
      HExpectedScore = 0.85;
   else if (difference<=315)
      HExpectedScore = 0.86;
   else if (difference<=328)
      HExpectedScore = 0.87;
   else if (difference<=344)
      HExpectedScore = 0.88;
   else if (difference<=357)
      HExpectedScore = 0.89;
   else if (difference<=374)
      HExpectedScore = 0.9;
   else if (difference<=391)
      HExpectedScore = 0.91;
   else if (difference>=392)
      HExpectedScore = 0.92;
      
   LExpectedScore = 1 - HExpectedScore;
   return;
}
}