package cn.pursol.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.function.SQLFunctionTemplate;

/**
 * hibernate中使用mysql的正则查询（hql中使用regexp）
 * @author 林晓东
 *
 */
public class MySQL5Dialect extends org.hibernate.dialect.MySQL5Dialect {
	public MySQL5Dialect() {
		super();
		registerFunction( "regexp", new SQLFunctionTemplate(Hibernate.BOOLEAN, "?1 REGEXP ?2") );
		registerFunction("convert", new SQLFunctionTemplate(Hibernate.STRING, "convert(?1 using ?2)") );
	}
}
