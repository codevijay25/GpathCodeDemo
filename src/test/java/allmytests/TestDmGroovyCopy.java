package allmytests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestDmGroovyCopy
{
    //end point  ->  http://localhost:3000/NBA

    @Test
    public void exampleOne()
    {
        //first element in an array/list

       Response response = given().when().get(" http://localhost:3000/NBA");

       String result =response.path("teams[0].name");

        System.out.println("The result is-> "+result);




    }

    @Test
    public void exampleTwo()
    {
        //last element in an array/list

        Response response = given().when().get(" http://localhost:3000/NBA");

        //String result =response.path("teams[-1].name");
        //String result =response.path("teams[-2].name");  //Second Last element

        //System.out.println("The result is-> "+result);


    }


    @Test
    public void exampleThree()
    {
        //get all the values of a particular element in an array/list

        Response response = given().when().get(" http://localhost:3000/NBA");

        //I want to get all the team names and extract it into a list?

        ArrayList<String> names =response.path("teams.name");

        System.out.println("The result is-> "+ names);


    }

    @Test
    public void exampleFour()
    {
        //Extract Multiple Maps of Objects

        Response response = given().when().get(" http://localhost:3000/NBA");

        //I want to get all the data of each team into a map ?

        ArrayList<Map<String,?>> data =response.path("teams");

        System.out.println("Result"+data);

        System.out.println("first team's name:  "+ data.get(0).get("name"));




    }

    @Test
    public void exampleFive() {

        //Extract Single Value with Find method of groovy


        Response response = given().when().get(" http://localhost:3000/NBA");

       Map<String,?>  result =  response.path("teams.find { it.name == 'Miami Heat'}");

        System.out.println("The result is "+result);



        //We examine the “teams” node and using GPath JSON, we iterate over every element until we find the one that has the name.
        // Once we find it, all the data for that team (name, location etc) is returned as a map.

        //Scenario:
        //you just want a specific element of the matched element

        String location = response.path("teams.find { it.id == 30}.location");

        //System.out.println("The result "+location);



    }

   @Test
    public void exampleSix()
    {
        //Extract List of Values with FindAll

        //scenario - I want to extract a list of the team names that has score greater than 200

        Response response = given().when().get(" http://localhost:3000/NBA");

        List<String> qualifiedTeam = response.path("teams.findAll { it.score > 200 }.name");

        System.out.println("The teams are "+qualifiedTeam);



    }

    @Test
    public void exampleSeven()
    {
        //Extract Single Value with Max value

        Response response = given().when().get(" http://localhost:3000/NBA");

       String maxScore =  response.path("teams.max { it.score }.name");

        System.out.println("Max score team "+maxScore);


    }


    public void exampleEight()
    {
        //Extract Single Value with Min value

        Response response = given().when().get(" http://localhost:3000/NBA");

        String minScore =  response.path("teams.min { it.score }.name");

        System.out.println("Min score team "+minScore);
    }


    @Test
    public void exampleNine()
    {
        //Collect a list of values and sum() those values
        Response response = given().when().get(" http://localhost:3000/NBA");

       int sumv =  response.path("teams.collect { it.score }.sum() ");

        System.out.println("Total score is "+sumv);
    }







}
