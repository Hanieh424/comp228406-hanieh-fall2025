package exercise1;
//Instance Variables
public class Singers {
    private int singerId;
    private String singerName;
    private String singerAddress;
    private String dateOfBirth;
    private int numberOfAlbumsPublished;

//No Argument Constructors
    public Singers() {

        this.singerId = 0;
        this.singerName = "default";
        this.singerAddress = "default";
        this.dateOfBirth = "default";
        this.numberOfAlbumsPublished= 0;


    }
//Constructor With Arguments
    public Singers(int singerId, String singerName, String singerAddress, String dateOfBirth, int numberOfAlbumsPublished) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.singerAddress = singerAddress;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbumsPublished = numberOfAlbumsPublished;
    }
//Setters For Individual Variables
    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }
    public void setSingerName(String singerName){
        this.singerName = singerName;
    }
    public void setSingerAddress(String singerAddress){
        this.singerAddress = singerAddress;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setNumberOfAlbumsPublished(int numberOfAlbumsPublished){
        this.numberOfAlbumsPublished = numberOfAlbumsPublished;
    }

//Setter for all variables
    public void setAllValues(int singerId, String singerName, String singerAddress, String dateOfBirth, int numberOfAlbumsPublished){
        this.singerId = singerId;
        this.singerName = singerName;
        this.singerAddress = singerAddress;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbumsPublished = numberOfAlbumsPublished;
    }
//Getters
    public int getSingerId(){
        return singerId;
    }
    public String getSingerName(){
        return singerName;
    }
    public String getSingerAddress(){
        return singerAddress;
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    public int getNumberOfAlbumsPublished(){
        return numberOfAlbumsPublished;
    }
}