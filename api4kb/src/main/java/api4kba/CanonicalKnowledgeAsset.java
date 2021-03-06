package api4kba;


import api4kbj.FocusedLanguageEnvironment;
import api4kbj.KnowledgeExpression;

public abstract class CanonicalKnowledgeAsset extends AbstractKnowledgeAsset {

	public CanonicalKnowledgeAsset(
			final FocusedLanguageEnvironment environment,
			final KnowledgeExpression canonicalExpression) {
		super(environment);
		this.canonicalExpression = canonicalExpression;
	}

	private KnowledgeExpression canonicalExpression;

	@Override
	public KnowledgeExpression canonicalExpression() {
		return canonicalExpression;
	}

}
