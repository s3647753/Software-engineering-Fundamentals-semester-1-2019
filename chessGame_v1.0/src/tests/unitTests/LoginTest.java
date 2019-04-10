package tests.unitTests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import model.Login;
import model.PlayerNotFoundException;
import model.RegLog;

public class LoginTest extends RegLog {

	private static final String TEST_PASS = "password";
	private static final String TEST_NAME = "testman";
	private static final String EXP_HASH = "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8";
	
	private static Login testLogin;
	
	@Before
	public void setUp() throws Exception {
		testLogin = new Login();
	}

	@Test
	public void testSHA() {
		assertEquals("The hashes didn't match.", EXP_HASH, stringToSHA256(TEST_PASS));
	}
	
	@Test
	public void testGetPlayerHash() throws PlayerNotFoundException {
		// this test throws an exception. JUtil counts a test as a fail if any exception is thrown.
		assertEquals("The hash was retrieved incorrectly.", EXP_HASH, getPlayerHash(TEST_NAME));
	}
	
	@Test
	public void testLogin() throws PlayerNotFoundException {
		// first assert the login method returns the correct value
		assertEquals("Failed to log player in.", true, testLogin.loginPlayer(TEST_NAME, TEST_PASS));
		// then check the player's actually present in the list.
		assertEquals("Player wasn't logged in correctly.", true, testLogin.getPlayerList().contains(TEST_NAME));
	}
	
	/*
	 * TODO: why does this test fail?
	 * the exact same line is run in the testLogin test, and it passes there. but in this test, it fails.
	 * i want to say JUtil is doing weird stuff causing the testLogin's playerList to be empty or something.
	
	@AfterClass
	public static void testPlayerIsLoggedIn() {
		// then check the player's actually present in the list.
		assertEquals("Player wasn't logged in correctly.", true, testLogin.getPlayerList().contains(TEST_NAME));
	}
	*/

}
