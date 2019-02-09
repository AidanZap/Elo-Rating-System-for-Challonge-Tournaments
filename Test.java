public class Test {
   public static void main(String[] args) {
   
   //Examples of creating players
   Player Aidan = new Player("Fatty");
   Aidan.getStats();
   
   Player Jeff = new Player("Jeff");
   Jeff.getStats();
   
   //Examples where Aidan beats Jeff 10 times in a row
   for (int i = 0; i<10;i++) {
      ScoreMatch.ScoreMatch(Aidan,Jeff,2,1);
      Aidan.getStats();
      Jeff.getStats();
   }
   
   //Example where Jeff beats Aidan after losing 10 previous rounds
   //*Note the drastic change in scores due to Aidan's higher rating
   ScoreMatch.ScoreMatch(Aidan,Jeff,0,2);
      Aidan.getStats();
      Jeff.getStats();
   
   }
}