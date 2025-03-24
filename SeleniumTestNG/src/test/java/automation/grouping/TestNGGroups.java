package automation.grouping;

import org.testng.annotations.Test;

public class TestNGGroups {
	
	@Test(groups = {"smoke"})
	public void test1() {
		System.out.println("This is test 1");
	}
	
	
	@Test(groups = {"smoke", "sanity"})
	public void test2() {
		System.out.println("This is test 2");
	}

	
	@Test(groups = {"sanity"})
	public void test3() {
		System.out.println("This is test 3");
	}
	
	@Test
	public void test4() {
		System.out.println("This is test 4");
	}


}
