package tests.unitTests;

/**
 * assortment of JUnit tests to verify that Login functions work correctly.
 * 
 * @author Shaun Davis
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.PlayerLoggedInException;
import model.PlayerNotFoundException;
import model.RegisterLogin;
import model.WrongPassException;

public class LoginTest {

	private static final String TEST_PASS = "password";
	private static final String TEST_NAME = "testman";
	private static final String EXP_HASH = "291a752915ac205f04d5fb28e2767fad45eed740acd9f3b0eb716f49a1952c5d";

	private static RegisterLogin testLogin;

	@Before
	public void setUp() throws Exception {
		testLogin = new RegisterLogin();
	}

	@Test
	public void testSHA() {
		assertEquals("The hashes didn't match.", EXP_HASH, testLogin.stringToSHA256(TEST_PASS, TEST_NAME));
	}

	@Test
	public void testGetPlayerHash() throws PlayerNotFoundException {
		// this test throws an exception. JUtil counts a test as a fail if any exception
		// is thrown.
		assertEquals("The hash was retrieved incorrectly.", EXP_HASH, testLogin.getPlayerHash(TEST_NAME));
	}

	@Test(expected = PlayerNotFoundException.class)
	public void testPlayerNotFound() throws PlayerNotFoundException, WrongPassException, PlayerLoggedInException {
		testLogin.loginPlayer("me", "not a password");
	}

	@Test(expected = WrongPassException.class)
	public void testWrongPass() throws PlayerNotFoundException, WrongPassException, PlayerLoggedInException {
		testLogin.loginPlayer(TEST_NAME, "the wrong password!");
	}

	@Test
	public void testLogin() throws PlayerNotFoundException, WrongPassException, PlayerLoggedInException {
		testLogin.loginPlayer(TEST_NAME, TEST_PASS);
		assertEquals("The player wasn't logged in correctly.", true, testLogin.getPlayerList().contains(TEST_NAME));
	}
	
	@Test(expected = PlayerLoggedInException.class)
	public void testLoggedIn() throws PlayerNotFoundException, WrongPassException, PlayerLoggedInException {
		testLogin.loginPlayer(TEST_NAME, TEST_PASS);
		testLogin.loginPlayer(TEST_NAME, TEST_PASS);
	}

}
