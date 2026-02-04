import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Analyzer for U.S. state renewable electricity data using ArrayList + Scanner + File I/O.
 * CSV expected: Location,TotalGenTWh,PercentRenewable,RenewableGenTWh,PercentOfUSRenewable,CO2MtPerTWh
 */
public class StateRenewableAnalyzer {
    private ArrayList<StateRenewable> states;

    /**
     * Constructor initializes an empty ArrayList.
     */
    public StateRenewableAnalyzer() {
        states = new ArrayList<StateRenewable>();
    }

    /**
     * Reads state data from a CSV file and populates the ArrayList.
     * Assumes the first line is a header.
     * @param filename path to the CSV file
     * @throws IOException if the file is not found
     */
    public void readFromFile(String filename) throws IOException {
        File f = new File(filename);
        try(Scanner scanner = new Scanner(f)){
            if(scanner.hasNextLine()){
                scanner.nextLine();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String [] items = line.split(",");
            try{
            StateRenewable temp = new StateRenewable(items[0], 
            Double.parseDouble(items[1]),
            Double.parseDouble(items[2]),
            Double.parseDouble(items[3]),
            Double.parseDouble(items[4]),
            Double.parseDouble(items[5]));
            
            states.add(temp);
            
            }catch(NumberFormatException nfe){
                continue;
            }  
        }
        }
        }
    }

    /**
     * Display all states in the list.
     */
    public void displayAllStates() {
    for(StateRenewable c : states){
            System.out.println(c.getName());
        }
    }

    /**
     * Display states at or above a renewable percent threshold.
     * @param threshold minimum percent renewable to include
     * @return ArrayList of StateRenewable objects meeting the threshold
     */
    public ArrayList<StateRenewable> displayAbovePercent(double threshold) {
        ArrayList<StateRenewable> temp = new ArrayList<StateRenewable>();
        for(StateRenewable c : states){
            if(c.isAboveRenewableThreshold(threshold)){
                temp.add(c);
            }
        }
        return temp;
    }

    /**
     * Find the state with the highest renewable percent.
     * @return StateRenewable with highest percent, or null if list is empty
     */
    public StateRenewable findHighestPercentRenewable() {
        if(states.size()!=0){
        StateRenewable max = states.get(0);
        for(StateRenewable c: states){
            if(c.getPercentRenewable() > max.getPercentRenewable()){
                max = c;
            }
        }
        return max;
        }
        return null;
    }

    /**
     * Find the state with the lowest renewable percent.
     * @return StateRenewable with lowest percent, or null if list is empty
     */
    public StateRenewable findLowestPercentRenewable() {
                if(states.size()!=0){
          StateRenewable min = states.get(0);
        for(StateRenewable c: states){
            if(c.getPercentRenewable() < min.getPercentRenewable()){
                min = c;
            }
        }
        return min;
    }
    return null;
    }

    /**
     * Calculate the average renewable percent across all states.
     * @return average percent, or 0 if list is empty
     */
    public double calculateAveragePercentRenewable() {
        if(states.size()!=0){
            double totalPercentRenewable = 0;
        for(StateRenewable c: states){
            totalPercentRenewable += c.getPercentRenewable();
        }
        return totalPercentRenewable/states.size();
        }
        return 0;
    }

    /**
     * Calculate total renewable generation (TWh) across all states.
     * @return sum of renewableGenTWh values
     */
    public double totalRenewableGenTWh() {
                double total = 0;
       for(StateRenewable c: states){
           total += c.getRenewableGenTWh();
       }
       return total;
    }

    /**
     * Find the state with the highest renewable generation (TWh).
     * @return StateRenewable with highest renewableGenTWh, or null if list is empty
     */
    public StateRenewable findHighestRenewableGen() {
                 if(states.size()!=0){
        StateRenewable max = states.get(0);
        for(StateRenewable c: states){
            if(c.getRenewableGenTWh() > max.getRenewableGenTWh()){
                max = c;
            }
        }
        return max;
        }
        return null;
    }

    /**
     * Display summary statistics.
     */
    public void displayStatistics() {
            for(StateRenewable c: states){
            System.out.println(c);
        }
    }

    /**
     * Helper method to get total number of states (for testing).
     * @return size of the ArrayList
     */
    public int getTotalStates() {
        return states.size();
    }
}
