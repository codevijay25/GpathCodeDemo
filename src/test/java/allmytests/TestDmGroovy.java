package allmytests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestDmGroovy
{
    //end point  ->  http://localhost:3000/NBA

    @Test
    public void exampleOne()
    {
        //first element in an array/list

       Response response = given().when().get(" http://localhost:3000/NBA");

    String result = response.path("teams[0].name");

        System.out.println("The result is-> "+result);




    }

    @Test
    public void exampleTwo()
    {
        //last element in an array/list

        Response response = given().when().get(" http://localhost:3000/NBA");

        //String result = response.path("teams[-1].name");

        String result = response.path("teams[-2].name");



        System.out.println("The result is-> "+result);


    }


    @Test
    public void exampleThree()
    {
        //get all the values of a particular element in an array/list

        Response response = given().when().get(" http://localhost:3000/NBA");

        //I want to get all the team names and extract it into a list?

        ArrayList<String> names = response.path("teams.name");



        System.out.println("The result is-> "+ names);


    }

    @Test
    public void exampleFour()
    {
        //Extract Multiple Maps of Objects

        Response response = given().when().get(" http://localhost:3000/NBA");

        //I want to get all the data of each team into a map ?


       ArrayList<HashMap<String,?>> data =  response.path("teams");



        System.out.println("Result->"+data.get(0).get("location"));






    }

    @Test
    public void exampleFive() {

        //Extract Single Value with Find method of groovy
        //Scenario:
        //You just want a specific element of the matched element


        Response response = given().when().get(" http://localhost:3000/NBA");

        String location = response.path("teams.find { it.name == 'Atlanta Hawks' }.location");

        //We examine the “teams” node and using GPath JSON, we iterate over every element until we find the one that has the id.
        // Once we find it, all the data for that team (name, location etc) is returned as a map.




        System.out.println("The result is "+location);









    }

   @Test
    public void exampleSix()
    {
        //Extract List of Values with FindAll

        //scenario - I want to extract a list of the team names that has score greater than 200

        Response response = given().when().get(" http://localhost:3000/NBA");


        ArrayList<String> data = response.path("teams.findAll { it.score > 200 }.name");



        System.out.println("The teams are "+data);



    }









    @Test
    public void exampleSeven()
    {
        //Extract Single Value with Max value

        Response response = given().when().get(" http://localhost:3000/NBA");


        HashMap<String,?> maxScore  = response.path("teams.max { it.score }");



       System.out.println("Max score team "+maxScore);


    }

    @Test
    public void exampleEight()
    {
        //Extract Single Value with Min value

        Response response = given().when().get(" http://localhost:3000/NBA");

        HashMap<String,?> minScore  = response.path("teams.min { it.score }");



        System.out.println("Min score team "+minScore);
    }


    @Test
    public void exampleNine()
    {
        //Collect a list of values and sum those values
        Response response = given().when().get(" http://localhost:3000/NBA");


         int totalScore= response.path("teams.collect { it.score }.sum()");



       System.out.println("Total score is "+totalScore);
    }







}
