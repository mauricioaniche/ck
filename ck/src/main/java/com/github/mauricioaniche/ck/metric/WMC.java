package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.*;

import java.util.*;

public class WMC implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	protected int cc = 0;
	// this stack helps us in knowing whether we could evaluate InfixExpressions or not
	// if we count the conditions directly in the branch node (e.g., if, for, ...), then
	// do not need to (re-)count the Infix agains
	// this is needed for some inline conditions, e.g., boolean x = a > 10;
	private LinkedList<ASTNode> stack = new LinkedList<>();

	@Override
	public void visit(MethodDeclaration node) {

		// plus 1 for the method itself
		increaseCc();
	}

    @Override
    public void visit(ForStatement node) {
	    increaseCCFromExpression(node.getExpression());

    	stack.push(node);
    }

	@Override
    public void visit(EnhancedForStatement node) {
	    increaseCCFromExpression(node.getExpression());

	    stack.push(node);
    }
    
    @Override
    public void visit(ConditionalExpression node) {

		increaseCCFromExpression(node.getExpression());

		stack.push(node);
    }
    
    @Override
    public void visit(DoStatement node) {
	    increaseCCFromExpression(node.getExpression());

	    stack.push(node);
    }

    @Override
    public void visit(WhileStatement node) {
	    increaseCCFromExpression(node.getExpression());

	    stack.push(node);
    }
    
    @Override
    public void visit(SwitchCase node) {

		if(!node.isDefault()) {
			increaseCCFromExpression(node.getExpression());
		}

	    stack.push(node);
    }

	@Override
	public void visit(InfixExpression node) {

		if(stack.isEmpty()) {
			Set<InfixExpression.Operator> operatorsToConsider = new HashSet<InfixExpression.Operator>() {{
				add(InfixExpression.Operator.LESS);
				add(InfixExpression.Operator.GREATER);
				add(InfixExpression.Operator.LESS_EQUALS);
				add(InfixExpression.Operator.GREATER_EQUALS);
				add(InfixExpression.Operator.EQUALS);
				add(InfixExpression.Operator.NOT_EQUALS);
			}};

			if (operatorsToConsider.contains(node.getOperator()))
				increaseCc();
		}
	}

    @Override
    public void visit(Initializer node) {
    	increaseCc();
    }


    @Override
    public void visit(CatchClause node) {
    	increaseCc();
    }

    public void visit(IfStatement node) {

	    increaseCCFromExpression(node.getExpression());
	    stack.push(node);
    }

	@Override
	public void endVisit(ForStatement node) {
		stack.pop();
	}

	@Override
	public void endVisit(EnhancedForStatement node) {
		stack.pop();
	}

	@Override
	public void endVisit(ConditionalExpression node) {
		stack.pop();
	}

	@Override
	public void endVisit(DoStatement node) {
		stack.pop();
	}

	@Override
	public void endVisit(WhileStatement node) {
		stack.pop();
	}

	@Override
	public void endVisit(SwitchCase node) {
		stack.pop();
	}

	@Override
	public void endVisit(IfStatement node) {
		stack.pop();
	}

	@Override
	public void endVisit(MethodDeclaration node) {
		stack.clear();
	}

	private int increaseCCFromExpression(Expression expression) {
		if(expression==null) {
			increaseCc();
			return 0;
		}

		if(!containsIfTenary(expression)) {
			increaseCc();
		}

		String expr = expression.toString().replace("&&", "&").replace("||", "|");
		int ands = StringUtils.countMatches(expr, "&");
		int ors = StringUtils.countMatches(expr, "|");

		increaseCc(ands + ors);
		return ands + ors;
	}


	private boolean containsIfTenary(Expression expression) {
		if(expression instanceof ParenthesizedExpression) {
			ParenthesizedExpression x = (ParenthesizedExpression) expression;
			return containsIfTenary(x.getExpression());
		} else if(expression instanceof InfixExpression) {
			InfixExpression x = (InfixExpression) expression;
			return containsIfTenary(x.getLeftOperand()) || containsIfTenary(x.getRightOperand());
		} else if (expression instanceof ConditionalExpression) {
			return true;
		}

		return false;

	}

	private void increaseCc() {
    	increaseCc(1);
    }

    protected void increaseCc(int qtd) {
    	cc += qtd;
    }

	@Override
	public void setResult(CKClassResult result) {
		result.setWmc(cc);
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setWmc(cc);
	}
}
