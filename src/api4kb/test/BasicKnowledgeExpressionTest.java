import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.runner.RunWith;

import api4kba.AbstractBasicKnowledgeExpression;
import api4kba.AbstractKRRLanguage;
import api4kbc.API4KB;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLogic;
import api4kbj.KnowledgeExpression;

@RunWith(Parameterized.class)
public class BasicKnowledgeExpressionTest {
	public BasicKnowledgeExpression expression;

	public BasicKnowledgeExpressionTest(BasicKnowledgeExpression expression) {
		this.expression = expression;
	}

	@Test
	public void expressionAndItsLanguageSatisfyUsesLanguageRelation() {
		assertTrue(API4KB.usesLanguage(expression, expression.language()));
	}

	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		KRRLogic logicA = new KRRLogic() {

			@Override
			public String name() {
				return "Logic A";
			}
		};
		KRRLogic logicB = new KRRLogic() {

			@Override
			public String name() {
				return "Logic B";
			}
		};
		BasicKnowledgeExpression expression0 = new AbstractBasicKnowledgeExpression(
				new AbstractKRRLanguage("Language One", logicA) {

					@Override
					public Class<? extends KnowledgeExpression> asClass() {
						return KnowledgeExpression.class;
					}
				}) {
		};
		BasicKnowledgeExpression expression1 = new AbstractBasicKnowledgeExpression(
				new AbstractKRRLanguage("Language Two", logicA) {

					@Override
					public Class<? extends KnowledgeExpression> asClass() {
						return KnowledgeExpression.class;
					}
				}) {
		};
		return Arrays
				.asList(new Object[][] { { expression0 }, { expression1 } });
	}
}
