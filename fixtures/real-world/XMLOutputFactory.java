/*
 **
 ** Licensed to the Apache Software Foundation (ASF) under one
 ** or more contributor license agreements.  See the NOTICE file
 ** distributed with this work for additional information
 ** regarding copyright ownership.  The ASF licenses this file
 ** to you under the Apache License, Version 2.0 (the
 ** "License"); you may not use this file except in compliance
 ** with the License.  You may obtain a copy of the License at
 **
 **  http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing,
 ** software distributed under the License is distributed on an
 ** "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 ** KIND, either express or implied.  See the License for the
 ** specific language governing permissions and limitations
 ** under the License.
 */
package javax.xml.stream;

public abstract class XMLOutputFactory {
	public static final String IS_REPAIRING_NAMESPACES = "javax.xml.stream.isRepairingNamespaces";

	protected XMLOutputFactory() { }

	public static XMLOutputFactory newInstance()
			throws FactoryConfigurationError {
		return (XMLOutputFactory) FactoryLocator.locate("javax.xml.stream.XMLOutputFactory",
				new String[] { "com.sun.xml.internal.stream.XMLOutputFactoryImpl", "com.ibm.xml.xlxp.api.stax.XMLOutputFactoryImpl" });
	}

	public static XMLInputFactory newInstance(String factoryId,
	                                          java.lang.ClassLoader classLoader) throws FactoryConfigurationError {
		return (XMLInputFactory) FactoryLocator.locate(factoryId,
				new String[] { "com.sun.xml.internal.stream.XMLOutputFactoryImpl", "com.ibm.xml.xlxp.api.stax.XMLOutputFactoryImpl" },
				classLoader);
	}

	public abstract XMLStreamWriter createXMLStreamWriter(java.io.Writer stream)
			throws XMLStreamException;

	public abstract XMLStreamWriter createXMLStreamWriter(
			java.io.OutputStream stream) throws XMLStreamException;

	public abstract XMLStreamWriter createXMLStreamWriter(
			java.io.OutputStream stream, String encoding)
			throws XMLStreamException;

	public abstract XMLStreamWriter createXMLStreamWriter(
			javax.xml.transform.Result result) throws XMLStreamException;

	public abstract XMLEventWriter createXMLEventWriter(
			javax.xml.transform.Result result) throws XMLStreamException;

	public abstract XMLEventWriter createXMLEventWriter(
			java.io.OutputStream stream) throws XMLStreamException;

	public abstract XMLEventWriter createXMLEventWriter(
			java.io.OutputStream stream, String encoding)
			throws XMLStreamException;

	public abstract XMLEventWriter createXMLEventWriter(java.io.Writer stream)
			throws XMLStreamException;

	public abstract void setProperty(String name, Object value)
			throws IllegalArgumentException;

	public abstract Object getProperty(String name)
			throws IllegalArgumentException;

	public abstract boolean isPropertySupported(String name);
}
