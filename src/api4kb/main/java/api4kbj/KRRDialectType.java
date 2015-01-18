package api4kbj;

public interface KRRDialectType<T> extends ClassWrapper<Object> {

	String name();

	@Override
	Class<? extends T> asClass();

	KRRDialect dialect();

	default KRRLanguage language() {
		return dialect().language();
	}


}
