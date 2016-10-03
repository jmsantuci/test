package jms.network.rules;

import java.util.List;


public interface RuleExecuter {

	public Object execute(Object object);
	public <T> List<T> execute(List<T> objects);
	public void setGlobal(String name, Object value);
}
