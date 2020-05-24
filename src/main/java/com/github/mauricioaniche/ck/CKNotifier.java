package com.github.mauricioaniche.ck;

public interface CKNotifier {
	void notify(CKClassResult result);
	void notifyError(String sourceFilePath, Exception e);
}
