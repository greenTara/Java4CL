package api4kbj;

public interface StructuredKnowledgeAsset extends KnowledgeAsset,
		StructuredKnowledgeResource<KnowledgeAsset> {

	@Override
	StructuredKnowledgeExpression canonicalExpression();

	default// getter for canonical expression components as iterable
	Iterable<KnowledgeExpression> canonicalExpressions() {
		return canonicalExpression().components();
	}

}