package com.github.mauricioaniche.ck;

public interface CKNotifier {
	void notify(CKClassResult result);
	default void notifyError(String sourceFilePath, Exception e) {}
}
