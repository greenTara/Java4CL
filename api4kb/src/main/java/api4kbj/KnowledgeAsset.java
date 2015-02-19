package api4kbj;

import elevation.Lowerable;

/**
 * Interface for knowledge assets, which are lowerable knowledge resources in a
 * focused environment at the {@link KnowledgeSourceLevel.ASSET} abstraction
 * level.
 * 
 * @author taraathan
 *
 */
public interface KnowledgeAsset extends KnowledgeResource, Lowerable {

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.ASSET;
	}

	/**
	 * Return the environment of the asset.
	 * 
	 * @return the environment of the asset
	 */
	FocusedImmutableLanguageEnvironment environment();

	/**
	 * Returns the canonical expression (i.e. expression in the focus language
	 * of the environment of the asset) of the asset.
	 * 
	 * @return the canonical expression of the asset
	 */
	KnowledgeExpression canonicalExpression();

	public default boolean accordingTo(
			FocusedImmutableLanguageEnvironment environment) {
		return environment.contains(environment());
	}

	public default boolean conceptualizes(KnowledgeExpression e) {
		if (e.equals(canonicalExpression())) {
			return true;
		}
		// TODO implement test based on equivalence relation that defines the
		// asset
		return false;
	}

}