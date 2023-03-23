package allmytests;



import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

public class TestDm
{


    @Test
    public void firstOption()
    {
        System.out.println("First Option");

        Response response = given().when().get("https://reqres.in/api/users?page=2");

        String result = response.path("data[0].email");

        System.out.println("result -> "+result);

    }

    @Test
    public void secondOption()
    {
        System.out.println("Second Option");

        Response response = given().when().get("https://reqres.in/api/users?page=2");

        JsonPath jsonPath = new JsonPath(response.asString());

       String result=  jsonPath.get("data[1].email");

        System.out.println("result is ->"+result);

    }


    @Test
    public void thirdOption()
    {
        System.out.println("Third Option");

        Response response = given().when().get("https://reqres.in/api/users?page=2");

        String result = JsonPath.from(response.asString()).get("data[3].email");


        System.out.println("result is ->"+result);

    }

    @Test
    public void lastOption()
    {
        System.out.println("Last Option");

        String result = given().when().get("https://reqres.in/api/users?page=2").path("data[4].email");

        // String result = response.path("data[4].email");




       System.out.println("result is ->"+result);

    }


}
