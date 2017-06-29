package br.mpmt.mp.app.dao;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

@Transaction
@Interceptor
public class TransacionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource
	private UserTransaction utx;
	
	@AroundInvoke
	public Object beginTransactionIfNotActive(InvocationContext ic) throws Exception {
		boolean newTransaction = false;
		
		if (utx.getStatus() != Status.STATUS_ACTIVE) {
			utx.begin();
			newTransaction = true;
		}
		
		Object retVal = null;
		
		try {
			retVal = ic.proceed();
			
			if (newTransaction)
				utx.commit();
		} catch (Exception e) {
			if (newTransaction)
				utx.rollback();
			
			throw e;
		}
		
		return retVal;
	}
}
