/**
 * ProcSMS_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.hrym.rpc.auth.service.sms;

public interface ProcSMS_PortType extends java.rmi.Remote {
    public java.lang.String sendSMS(com.hrym.rpc.auth.service.sms.SMS sms) throws java.rmi.RemoteException;
    public java.lang.String sendSMSEX(com.hrym.rpc.auth.service.sms.SMSEX sms) throws java.rmi.RemoteException;
    public java.lang.String sendAdvertisement(com.hrym.rpc.auth.service.sms.SMS sms) throws java.rmi.RemoteException;
}
