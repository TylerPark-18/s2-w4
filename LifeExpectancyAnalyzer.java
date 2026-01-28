import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Demonstrates reading data from a CSV file using Scanner
 * Creates an ArrayList of Country objects
 * Week 4: ArrayList + Scanner + File I/O
 */
public class LifeExpectancyAnalyzer {
    private ArrayList<Country> countries;

    /**
     * Constructor initializes an empty ArrayList
     */
    public LifeExpectancyAnalyzer() {
        countries = new ArrayList<Country>();
    }

    /**
     * Reads country data from a CSV file and populates the ArrayList
     * CSV format: country,region,population,income_group,life_expectancy_2010,life_expectancy_2015,life_expectancy_2020
     * 
     * @param filename path to the CSV file
     * @throws IOFoundException if the file is not found
     */
    public void readFromFile(String filename)throws IOException  {
        Scanner s = new Scanner(new File(filename));
        s.nextLine();
        while(s.hasNextLine()){
            String line = s.nextLine();
            String [] items = line.split(",");
            Country temp = new Country(items [0], items[1],Integer.parseInt(items[2]),items[3],Double.parseDouble(items[4]),Double.parseDouble(items[5]),Double.parseDouble(items[6]));
            countries.add(temp);
        }
        System.out.println(countries.size());
        s.close();
    }

    /**
     * Display all countries in the list
     */
    public void displayAllCountries() {
        for(Country s : countries){
            System.out.println(s.getName());
        }
    }

    /**
     * Display countries from a specific region
     * @param region the region to filter by
     */
    public void displayByRegion(String region) {
        for(Country s: countries){
            if(s.getRegion().equals(region))
                System.out.println(s.getName());
        }
    }

    /**
     * Find the country with the highest life expectancy in 2020
     * @return the Country with the highest life expectancy, or null if list is empty
     */
    public Country findHighestLifeExpectancy() {
        if(countries.size()== 0){
            return null;
        }
       Country highest = countries.get(0);
       for(Country s : countries){
            if(s.getLifeExpectancy2020()> highest.getLifeExpectancy2020()){
                highest = s;
            }
       }
       return highest;
    }

    /**
     * Find the country with the lowest life expectancy in 2020
     * @return the Country with the lowest life expectancy, or null if list is empty
     */
    public Country findLowestLifeExpectancy() {
        if(countries.size()== 0){
            return null;
        }
       Country lowest = countries.get(0);
       for(Country s : countries){
            if(s.getLifeExpectancy2020()< lowest.getLifeExpectancy2020()){
                lowest = s;
            }
       }
       return lowest;
    }

    /**
     * Calculate the average life expectancy across all countries
     * @return average life expectancy in 2020, or 0 if list is empty
     */
    public double calculateAverageLifeExpectancy() {
        if(countries.size()==0){
            return 0;
        }
        Double i = 0.0;
        for(Country s : countries){
            i = i+ s.getLifeExpectancy2020();
        }
        return i;
    }

    /**
     * Count how many countries are in a specific income group
     * @param incomeGroup the income group to count
     * @return number of countries in that income group
     */
    public int countByIncomeGroup(String incomeGroup) {
        int i = 0;
        for(Country s: countries){
            if(s.getIncomeGroup().equals(incomeGroup)){
                i ++;
            }
        }
        return i;
    }

    /**
     * Find the country with the most improvement in life expectancy (2010 to 2020)
     * @return the Country with the largest improvement, or null if list is empty
     */
    public Country findMostImprovement() {
        Country i = countries.get(0);
        for(Country s: countries){
            if(s.getChange2010To2020()> i.getChange2010To2020())
                i = s;
        }
        return i;
    }

    /**
     * Display summary statistics
     */
    public void displayStatistics() {
        for(Country s: countries){
            System.out.println(s.getName() + ", " + s.getRegion() + ", " + s.getPopulation() + ", " + s.getIncomeGroup() + ",  " + s.getLifeExpectancy2010() + ", " + s.getLifeExpectancy2015() + ", " + s.getLifeExpectancy2020());
        }
    }

}
