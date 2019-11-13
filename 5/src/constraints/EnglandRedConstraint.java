package constraints;

import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class EnglandRedConstraint implements Constraint {

	@Override
	public List<Variable> getScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSatisfiedWith(Assignment arg0) {
		
		return false;
	}

}
