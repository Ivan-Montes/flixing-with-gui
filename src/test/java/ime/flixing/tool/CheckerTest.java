package ime.flixing.tool;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CheckerTest {

	@Test
	void checker_checkDigits_ReturnBool() {
		
		assertAll(
				()->Assertions.assertFalse( Checker.checkDigits("Hombre de bien") ),
				()->Assertions.assertTrue( Checker.checkDigits("22") )
				);
	}
	
	@Test
	void checker_checkFlixTitle_ReturnBool() {
		
		assertAll(
				()->Assertions.assertFalse( Checker.checkFlixTitle("Title%") ),
				()->Assertions.assertFalse( Checker.checkFlixTitle("34345434565434543454345432123456789876543456789876543221") ),
				()->Assertions.assertTrue( Checker.checkFlixTitle("A carta cabal") ),
				()->Assertions.assertFalse( Checker.checkFlixTitle("") )
				);
	}
	

	@Test
	void checker_checkName_ReturnBool() {
		
		assertAll(
				()->Assertions.assertFalse( Checker.checkName("Title%") ),
				()->Assertions.assertFalse( Checker.checkName("34345434565434543454345432123456789876543456789876543221") ),
				()->Assertions.assertTrue( Checker.checkName("A carta cabal") ),
				()->Assertions.assertFalse( Checker.checkName("34345434565434543454345432123456789876543456789876543221%") )
				);
	}

	@Test
	void checker_checkDescription_ReturnBool() {
		
		assertAll(
				()->Assertions.assertFalse( Checker.checkDescription("Title%") ),
				()->Assertions.assertFalse( Checker.checkDescription("34345434565434543454345432123456789876543456789876543221" +
																	"34345434565434543454345432123456789876543456789876543221") ),
				()->Assertions.assertTrue( Checker.checkDescription("A carta cabal") ),
				()->Assertions.assertFalse( Checker.checkDescription("") )
				);
	}

	@Test
	void checker_checkSurname_ReturnBool() {
		
		assertAll(
				()->Assertions.assertFalse( Checker.checkSurname("Title%") ),
				()->Assertions.assertFalse( Checker.checkSurname("34345434565434543454345432123456789876543456789876543221") ),
				()->Assertions.assertTrue( Checker.checkSurname("A carta cabal") ),
				()->Assertions.assertFalse( Checker.checkSurname("") )
				);
	}

}
