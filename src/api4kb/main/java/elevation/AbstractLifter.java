package elevation;

import api4kb.AbstractKnowledgeAsset;
import api4kb.AbstractKnowledgeEncoding;
import api4kb.AbstractKnowledgeExpression;
import api4kb.AbstractKnowledgeItem;
import api4kb.AbstractKnowledgeManifestationG;
import api4kb.EnvironmentIncompatibleException;
import api4kb.ImmutableEnvironment;
import api4kb.KnowledgeResource;

public abstract class AbstractLifter implements Lifter {
	
	public AbstractKnowledgeAsset conceptualize(KnowledgeResource kr) throws EnvironmentIncompatibleException{
		return conceptualize(kr, kr.getDefaultEnvironment());
	}

	@Override
	public abstract <T> AbstractKnowledgeAsset conceptualize(
			AbstractKnowledgeManifestationG<T> kr, ImmutableEnvironment e) throws EnvironmentIncompatibleException;

	@Override
	public abstract <T, S> AbstractKnowledgeAsset conceptualize(
			AbstractKnowledgeEncoding<T, S> kr, ImmutableEnvironment e) throws EnvironmentIncompatibleException;

	@Override
	public abstract <T, S, R> AbstractKnowledgeAsset conceptualize(
			AbstractKnowledgeItem<T, S, R> kr, ImmutableEnvironment e) throws EnvironmentIncompatibleException;

	@Override
	public abstract <T> AbstractKnowledgeExpression parse(
			AbstractKnowledgeManifestationG<T> kr);

	@Override
	public abstract <T, S> AbstractKnowledgeExpression parse(
			AbstractKnowledgeEncoding<T, S> kr);

	@Override
	public abstract <T, S, R> AbstractKnowledgeExpression parse(
			AbstractKnowledgeItem<T, S, R> kr);

	@Override
	public abstract <T, S> AbstractKnowledgeManifestationG<T> decode(
			AbstractKnowledgeEncoding<T, S> kr);

	@Override
	public abstract <T, S, R> AbstractKnowledgeManifestationG<T> decode(
			AbstractKnowledgeItem<T, S, R> kr);

	@Override
	public abstract <T, S, R> AbstractKnowledgeEncoding<T, S> prototype(
			AbstractKnowledgeItem<T, S, R> kr);

}