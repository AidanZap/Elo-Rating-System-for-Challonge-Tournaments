public class Player {
   private int idNumber;                  //ID isthe order the objects are generated, ex: first ID is 0
   private String playerTag;              //ex: "Fatty"
   private double rating;                 //ex: 1248
   private int ranking;                   //ex: 5/28
   private static int currentId = 0;      //Current ID
   private static int numberOfPlayers = 0;//Current number of players

   //Constructor with tag specified
   //Id becomes next in line
   //Ranking is set to last
   //Rating starts at 1000
   public Player(String tag) {
      this.playerTag = tag;
      this.idNumber = currentId;
      currentId++;
      numberOfPlayers++;
      this.ranking = numberOfPlayers;
      this.rating = 1000;
   }
   
   //Default constructor
   //sets tag to "defaultPlayer"
   public Player() {
      this("defaultPlayer");
   }
   
   //Getter & Setter Methods
   public String getPlayerTag() {
      return playerTag;
   }
   
   public int getIdNumber() {
      return idNumber;
   }
   
   public double getRating() {
      return rating;
   }
   
   public void setRating(double newRating) {
      this.rating = newRating;
   }
   
   public int getRanking() {
      return ranking;
   }
   
   public void getStats() {
      System.out.println(playerTag + " (ID: "+ idNumber + "):");
      System.out.println("Rank: " + ranking + " of " + numberOfPlayers + " Rating: " + rating);
      return;
   }
}