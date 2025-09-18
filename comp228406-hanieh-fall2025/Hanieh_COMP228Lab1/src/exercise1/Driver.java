package exercise1;

public class Driver {
    public static void main(String[] args) {
        Singers singer1 = new Singers();
        //Default values display
        System.out.println("**Default value of singer1**");
        System.out.println("Singer Id:" + singer1.getSingerId());
        System.out.println("Singer name:" + singer1.getSingerName());
        System.out.println("Singer address:" + singer1.getSingerAddress());
        System.out.println("Date of birth:" + singer1.getDateOfBirth());
        System.out.println("Number of albums published:" + singer1.getNumberOfAlbumsPublished());

        //Set all the values
        singer1.setAllValues(101, "The Weeknd", "Canada, Toronto", "02-16-1990", 6);
        System.out.println("\n**Display value of singer1**");
        System.out.println("Singer Id:" + singer1.getSingerId());
        System.out.println("Singer name:" + singer1.getSingerName());
        System.out.println("Singer address:" + singer1.getSingerAddress());
        System.out.println("Date of birth:" + singer1.getDateOfBirth());
        System.out.println("Number of albums published:" + singer1.getNumberOfAlbumsPublished());

        //Change individual value
        singer1.setSingerId(102);
        singer1.setSingerName("Giveon");
        singer1.setSingerAddress("USA, California");
        singer1.setDateOfBirth("02-21-1995");
        singer1.setNumberOfAlbumsPublished(2);

        //Display current values
        System.out.println("\n**Display updated value of singer1**");
        System.out.println("Singer Id:" + singer1.getSingerId());
        System.out.println("Singer name:" + singer1.getSingerName());
        System.out.println("Singer address:" + singer1.getSingerAddress());
        System.out.println("Date of birth:" + singer1.getDateOfBirth());
        System.out.println("Number of albums published:" + singer1.getNumberOfAlbumsPublished());


    }
}