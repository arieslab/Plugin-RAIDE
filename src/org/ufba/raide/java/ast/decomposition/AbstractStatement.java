package org.ufba.raide.java.ast.decomposition;

import java.util.List;

import org.eclipse.jdt.core.dom.Statement;
import org.ufba.raide.java.ast.ASTInformation;
import org.ufba.raide.java.ast.ASTInformationGenerator;

public abstract class AbstractStatement extends AbstractMethodFragment {

	private ASTInformation statement;
	private StatementType type;
	
    public AbstractStatement(Statement statement, StatementType type, AbstractMethodFragment parent) {
    	super(parent);
    	this.type = type;
    	this.statement = ASTInformationGenerator.generateASTInformation(statement);
    }

    public Statement getStatement() {
    	return (Statement)this.statement.recoverASTNode();
    }

	public StatementType getType() {
		return type;
	}

	public int getNestingDepth() {
		AbstractStatement parent = (AbstractStatement) this.getParent();
		int depth = 0;
		while (parent != null) {
			if (!parent.getType().equals(StatementType.BLOCK)) {
				depth++;
			}
			parent = (AbstractStatement) parent.getParent();
		}
		return depth;
	}

	public abstract List<String> stringRepresentation();
}
