/**
 * ProcSMSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.hrym.rpc.auth.service.sms;

public interface ProcSMSService extends javax.xml.rpc.Service {
    public java.lang.String getprocSMSAddress();

    public com.hrym.rpc.auth.service.sms.ProcSMS_PortType getprocSMS() throws javax.xml.rpc.ServiceException;

    public com.hrym.rpc.auth.service.sms.ProcSMS_PortType getprocSMS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
