package api4kbj;

public interface StructuredKnowledgeEncoding extends KnowledgeEncoding,
		StructuredKnowledgeResource<KnowledgeEncoding> {

	// getter for formats
	Iterable<? extends KRRFormat> formats();

}