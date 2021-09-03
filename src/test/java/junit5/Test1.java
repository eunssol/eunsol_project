package junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test1 {
	
	//모든 테스트 전에 실행이 되는 어노테이션, 반드시 static이여야함
	@BeforeAll
	static void beforeAll() {
		System.out.println("BeforeAll");
	}

	
	// 각 테스트 전 실행 여기는 static ㄴㄴ
	@BeforeEach 
	void beforeEach() {
		System.out.println("BeforeEach");
	}
	
	// 각 테스트 후 실행이 되는
	@AfterEach
	void afterEach() {
		System.out.println("AfterEach");
	}
	
	//모든 테스트 후 실행되는 static으로 써야해
	@AfterAll
	static void afterAll() {
		System.out.println("AfterAll");
	}
	
	//테스트
	@Test
	void test() {
		System.out.println("Test");
	}
	
	//테스트2
	@Test
	void test2() {
		System.out.println("Test2");
	}
	
	
	
}
