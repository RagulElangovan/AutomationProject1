package ragulProject1;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import testComponents.BaseTest;

public class DemoTest extends BaseTest {

	@Test(dataProvider = "Data", dataProviderClass = BaseTest.class, enabled = false)
	public void demo(HashMap<String, String> input) {

		System.out.println(input.get("email"));
		System.out.println(input.get("password"));
		System.out.println(input.get("product"));

	}

	public static void main(String[] args) throws IOException {

		String string = getDataFromJson().get(0).get("product");
		System.out.println(string);

	}

}
