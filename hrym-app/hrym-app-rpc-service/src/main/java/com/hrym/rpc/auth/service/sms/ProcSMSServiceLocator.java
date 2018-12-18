/**
 * ProcSMSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.hrym.rpc.auth.service.sms;

public class ProcSMSServiceLocator extends org.apache.axis.client.Service implements com.hrym.rpc.auth.service.sms.ProcSMSService {

    public ProcSMSServiceLocator() {
    }


    public ProcSMSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProcSMSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for procSMS
    private java.lang.String procSMS_address = "http://sfsms.int.sfdc.com.cn/sfsms/services/procSMS";

    public java.lang.String getprocSMSAddress() {
        return procSMS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String procSMSWSDDServiceName = "procSMS";

    public java.lang.String getprocSMSWSDDServiceName() {
        return procSMSWSDDServiceName;
    }

    public void setprocSMSWSDDServiceName(java.lang.String name) {
        procSMSWSDDServiceName = name;
    }

    public com.hrym.rpc.auth.service.sms.ProcSMS_PortType getprocSMS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(procSMS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getprocSMS(endpoint);
    }

    public com.hrym.rpc.auth.service.sms.ProcSMS_PortType getprocSMS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.hrym.rpc.auth.service.sms.ProcSMSSoapBindingStub _stub = new com.hrym.rpc.auth.service.sms.ProcSMSSoapBindingStub(portAddress, this);
            _stub.setPortName(getprocSMSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setprocSMSEndpointAddress(java.lang.String address) {
        procSMS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.hrym.rpc.auth.service.sms.ProcSMS_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.hrym.rpc.auth.service.sms.ProcSMSSoapBindingStub _stub = new com.hrym.rpc.auth.service.sms.ProcSMSSoapBindingStub(new java.net.URL(procSMS_address), this);
                _stub.setPortName(getprocSMSWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("procSMS".equals(inputPortName)) {
            return getprocSMS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http:///sfsms/services/procSMS", "procSMSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http:///sfsms/services/procSMS", "procSMS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("procSMS".equals(portName)) {
            setprocSMSEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
