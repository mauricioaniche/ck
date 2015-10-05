package br.aniche.ck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import br.aniche.ck.metric.DIT;
import br.aniche.ck.metric.Metric;

public class CK {

	private HashSet<CalculatedCK> results;

	public CK() {
		this.results = new HashSet<CalculatedCK>();
	}
	
	public Set<CalculatedCK> parseAll(String path) {

		Storage storage = generateASTs(path);
		
		for(String file : storage.keys()) {
			calculateMetricsIn(file, storage);
		}
		
		return results;
	}

	private Storage generateASTs(String path) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		
		Map<?, ?> options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_8, options);
		parser.setCompilerOptions(options);

		String[] srcDirs = FileUtils.getAllDirs(path);
		String[] javaFiles = FileUtils.getAllJavaFiles(path);

		parser.setEnvironment(null, srcDirs, null, true);
		
		Storage storage = new Storage();
		parser.createASTs(javaFiles, null, new String[0], storage, null);
		return storage;
	}

	private void calculateMetricsIn(String file, Storage storage) {
		try {
			CompilationUnit cu = storage.get(file);
			
			CalculatedCK result = new CalculatedCK(file);
			
			for(Metric visitor : metrics()) {
				visitor.execute(cu, result);
			}
			
			results.add(result);
		} catch(Exception e) {
			// just ignore... sorry!
			// later on: log
		}
	}

	private List<Metric> metrics() {
		return Arrays.asList(new DIT());
	}
	
}
