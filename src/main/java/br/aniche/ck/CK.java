package br.aniche.ck;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import br.aniche.ck.metric.CBO;
import br.aniche.ck.metric.ClassInfo;
import br.aniche.ck.metric.DIT;
import br.aniche.ck.metric.LCOM;
import br.aniche.ck.metric.Metric;
import br.aniche.ck.metric.NOC;
import br.aniche.ck.metric.RFC;
import br.aniche.ck.metric.WMC;

public class CK {

	private CKReport report;

	public CK() {
		this.report = new CKReport();
	}
	
	public CKReport calculate(String path) {

		Storage storage = generateASTs(path);
		createEmptyResults(storage);
		
		for(String file : storage.keys()) {
			calculateMetricsIn(file, storage);
		}
		
		return report;
	}

	private void createEmptyResults(Storage storage) {
		for(String file : storage.keys()) {
			
			CompilationUnit cu = storage.get(file);
			ClassInfo info = new ClassInfo();
			cu.accept(info);
			
			report.add(new CKNumber(file, info.getClassName()));
			
		}
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
			CKNumber result = report.get(file);
			
			for(Metric visitor : metrics()) {
				visitor.execute(cu, report);
				visitor.setResult(result);
			}
			
		} catch(Exception e) {
			// just ignore... sorry!
			// later on: log
			e.printStackTrace(System.err);
		}
	}

	private List<Metric> metrics() {
		return Arrays.asList(new DIT(), new NOC(), new WMC(), new CBO(), new LCOM(), new RFC());
	}
	
}
