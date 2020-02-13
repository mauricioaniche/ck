package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

import java.util.Stack;

public class NumberOfMaxNestedBlock extends ASTVisitor implements ClassLevelMetric, MethodLevelMetric {

	private int current = 0;
	private int max = 0;
	private Stack<ASTNode> currentNode = new Stack<>();
	private Stack<Boolean> blocks = new Stack<>();
	private Stack<Boolean> nodes = new Stack<>();

	@Override
	public boolean visit(Block node) {

		// we always do a +1 if we see a block, with the exception of
		// a switch case, as we do the +1 in the block.
		// note that blocks might not always exist, and that's why we check
		// for their existence at every node later on...
		// if they do not exist, we +1 in the node.
		if(currentNode.empty() || !(currentNode.peek() instanceof SwitchCase)) {
			plusOne();
			blocks.push(true);
		} else {
			blocks.push(false);
		}

		currentNode.push(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(ForStatement node) {
		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

		return super.visit(node);
	}

	@Override
	public boolean visit(EnhancedForStatement node) {

		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

		return super.visit(node);
	}

	@Override
	public boolean visit(DoStatement node) {
		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

		return super.visit(node);
	}

	@Override
	public boolean visit(WhileStatement node) {
		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}



		return super.visit(node);
	}

	@Override
	public boolean visit(SwitchStatement node) {

		currentNode.push(node);
		nodes.push(true);
		plusOne();

		return super.visit(node);
	}

	@Override
	public boolean visit(SwitchCase node) {
		currentNode.push(node);
		return super.visit(node);
	}


	@Override
	public boolean visit(CatchClause node) {

		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getBody());
		if(!containsBlock) {
			plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}



		return super.visit(node);
	}

	public boolean visit(IfStatement node) {

		currentNode.push(node);

		boolean containsBlock = containsBlock(node.getThenStatement());
		if(!containsBlock) {
			plusOne();
			nodes.push(true);
		} else {
			nodes.push(false);
		}

		return super.visit(node);
	}


	@Override
	public void endVisit(Block node) {
		Boolean pop = blocks.pop();
		if(pop)
			current--;

		currentNode.pop();
	}

	@Override
	public void endVisit(IfStatement node) {
		popBlock();
	}

	private boolean containsBlock(Statement body) {
		return (body instanceof Block);
	}


	private void plusOne() {
		current++;
		max = Math.max(current, max);
	}

	private void popBlock() {
		Boolean pop = nodes.pop();
		if(pop)
			current--;
	}

	@Override
	public void endVisit(CatchClause node) {
		popBlock();
	}

	@Override
	public void endVisit(WhileStatement node) {
		popBlock();
	}

	@Override
	public void endVisit(DoStatement node) {
		popBlock();
	}

	@Override
	public void endVisit(EnhancedForStatement node) {
		popBlock();
	}

	@Override
	public void endVisit(ForStatement node) {
		popBlock();
	}

	@Override
	public void endVisit(SwitchStatement node) {
		popBlock();
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setMaxNestedBlocks(max - 1); // -1 because the method block is considered a block.
	}

	@Override
	public void setResult(CKClassResult result) {
		result.setMaxNestedBlocks(max - 1);
	}
}
