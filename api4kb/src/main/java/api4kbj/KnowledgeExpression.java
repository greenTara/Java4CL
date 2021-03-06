package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeExpression extends KnowledgeResource, Liftable,
		Lowerable {

	//EquivalenceRelation ID = new EquivalenceRelation();

	@Override
	default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.EXPRESSION;
	}

	boolean usesLanguage(final KRRLanguage language);

}
