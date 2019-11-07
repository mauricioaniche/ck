package com.firefly.net.tcp;

import com.firefly.net.Decoder;
import com.firefly.net.Handler;
import com.firefly.net.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

/**
 * @author Pengtao Qiu
 */
abstract public class AbstractSimpleHandler implements Handler {

	protected static final Logger log = LoggerFactory.getLogger("firefly-system");

	static final Decoder decoder = (ByteBuffer buf, Session session) -> {
		Object o = session.getAttachment();
		if (o != null) {
			TcpConnectionImpl c = (TcpConnectionImpl) o;
			if (c.buffer != null) {
				c.buffer.call(buf);
			}
		}
	};

	static final Decoder sslDecoder = (ByteBuffer buf, Session session) -> {
		Object o = session.getAttachment();
		if (o != null && o instanceof SecureTcpConnectionImpl) {
			SecureTcpConnectionImpl c = (SecureTcpConnectionImpl) o;
			ByteBuffer plaintext = c.secureSession.read(buf);
			if (plaintext != null && c.secureSession.isHandshakeFinished()) {
				if (c.buffer != null) {
					c.buffer.call(plaintext);
				}
			}
		}
	};


	@Override
	public void sessionClosed(Session session) throws Throwable {
		Object o = session.getAttachment();
		if (o != null && o instanceof AbstractTcpConnection) {
			AbstractTcpConnection c = (AbstractTcpConnection) o;
			c.notifyClose();
		}
		if (o != null && o instanceof SecureTcpConnectionImpl) {
			SecureTcpConnectionImpl c = (SecureTcpConnectionImpl) o;
			c.secureSession.close();
		}
	}

	@Override
	public void messageReceived(Session session, Object message) {
	}

	@Override
	public void exceptionCaught(Session session, Throwable t) throws Throwable {
		log.error("tcp handler exception", t);
		Object o = session.getAttachment();
		if (o != null && o instanceof AbstractTcpConnection) {
			AbstractTcpConnection c = (AbstractTcpConnection) o;
			c.notifyException(t);
		}
	}

}