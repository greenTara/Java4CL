package cl2;

import api4kba.AbstractKRRDialect;

public abstract class CLDialect extends AbstractKRRDialect {

	public CLDialect(String name) {
		super(name, CL.LANG);
	}

}
