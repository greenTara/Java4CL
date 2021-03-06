import static org.junit.Assert.*;
import hashenvironment.HashFocusedKRRLanguageEnvironment;

import org.junit.Test;

import cl2.CL;
import cl2.CLCommentExpression;

public class CLTest {

	@Test
	public void testLang() {
		assertEquals(
				"The name of the Common Logic language should be 'Common Logic'.",
				"Common Logic 2", CL.LANG.name());
	}

	public void testNamespace() {
		assertEquals(
				"The Common Logic URI should be the URI of the Flyweight Namespace for use in XCL2 dom4j instances.",
				CL.URI_XCL2, CL.NS_XCL2.getURI());
	}

	@Test
	public void testEnv() {
		assertTrue(
				"The Common Logic default (singleton) environment should contain the CL language.",
				CL.CL_DEFAULT_ENVIRONMENT.containsMember(CL.LANG));
		assertEquals(
				"The Common Logic language should be the focus language of the default CL environment.",
				CL.LANG, CL.CL_DEFAULT_ENVIRONMENT.focusMember().value());
		assertEquals("Equality of environments should be based on fields",
				CL.CL_DEFAULT_ENVIRONMENT,
				new HashFocusedKRRLanguageEnvironment(CL.LANG));
		CLCommentExpression expression = new CLCommentExpression("");
		assertTrue(
				"A Common Logic expression should be compatible with the Common Logic default environment",
				CL.CL_DEFAULT_ENVIRONMENT.isCompatibleWith(expression));
		assertFalse(
				"null should not be compatible with the Common Logic default environment",
				CL.CL_DEFAULT_ENVIRONMENT.isCompatibleWith(null));
		assertFalse(
				"null should not be compatible with the Common Logic default environment",
				CL.CL_DEFAULT_ENVIRONMENT.isCompatibleWithClass(null));

	}
}
