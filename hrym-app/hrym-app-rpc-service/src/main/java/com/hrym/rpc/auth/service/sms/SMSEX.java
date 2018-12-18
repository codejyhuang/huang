/**
 * SMSEX.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.hrym.rpc.auth.service.sms;

public class SMSEX  implements java.io.Serializable {
    private java.lang.String SP;

    private java.lang.String checkword;

    private java.lang.String mobileno;

    private java.lang.String msg;

    private java.lang.String msgId;

    private java.lang.String msgtype;

    private java.lang.String resno;

    private java.lang.String systemId;

    public SMSEX() {
    }

    public SMSEX(
           java.lang.String SP,
           java.lang.String checkword,
           java.lang.String mobileno,
           java.lang.String msg,
           java.lang.String msgId,
           java.lang.String msgtype,
           java.lang.String resno,
           java.lang.String systemId) {
           this.SP = SP;
           this.checkword = checkword;
           this.mobileno = mobileno;
           this.msg = msg;
           this.msgId = msgId;
           this.msgtype = msgtype;
           this.resno = resno;
           this.systemId = systemId;
    }


    /**
     * Gets the SP value for this SMSEX.
     * 
     * @return SP
     */
    public java.lang.String getSP() {
        return SP;
    }


    /**
     * Sets the SP value for this SMSEX.
     * 
     * @param SP
     */
    public void setSP(java.lang.String SP) {
        this.SP = SP;
    }


    /**
     * Gets the checkword value for this SMSEX.
     * 
     * @return checkword
     */
    public java.lang.String getCheckword() {
        return checkword;
    }


    /**
     * Sets the checkword value for this SMSEX.
     * 
     * @param checkword
     */
    public void setCheckword(java.lang.String checkword) {
        this.checkword = checkword;
    }


    /**
     * Gets the mobileno value for this SMSEX.
     * 
     * @return mobileno
     */
    public java.lang.String getMobileno() {
        return mobileno;
    }


    /**
     * Sets the mobileno value for this SMSEX.
     * 
     * @param mobileno
     */
    public void setMobileno(java.lang.String mobileno) {
        this.mobileno = mobileno;
    }


    /**
     * Gets the msg value for this SMSEX.
     * 
     * @return msg
     */
    public java.lang.String getMsg() {
        return msg;
    }


    /**
     * Sets the msg value for this SMSEX.
     * 
     * @param msg
     */
    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }


    /**
     * Gets the msgId value for this SMSEX.
     * 
     * @return msgId
     */
    public java.lang.String getMsgId() {
        return msgId;
    }


    /**
     * Sets the msgId value for this SMSEX.
     * 
     * @param msgId
     */
    public void setMsgId(java.lang.String msgId) {
        this.msgId = msgId;
    }


    /**
     * Gets the msgtype value for this SMSEX.
     * 
     * @return msgtype
     */
    public java.lang.String getMsgtype() {
        return msgtype;
    }


    /**
     * Sets the msgtype value for this SMSEX.
     * 
     * @param msgtype
     */
    public void setMsgtype(java.lang.String msgtype) {
        this.msgtype = msgtype;
    }


    /**
     * Gets the resno value for this SMSEX.
     * 
     * @return resno
     */
    public java.lang.String getResno() {
        return resno;
    }


    /**
     * Sets the resno value for this SMSEX.
     * 
     * @param resno
     */
    public void setResno(java.lang.String resno) {
        this.resno = resno;
    }


    /**
     * Gets the systemId value for this SMSEX.
     * 
     * @return systemId
     */
    public java.lang.String getSystemId() {
        return systemId;
    }


    /**
     * Sets the systemId value for this SMSEX.
     * 
     * @param systemId
     */
    public void setSystemId(java.lang.String systemId) {
        this.systemId = systemId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SMSEX)) return false;
        SMSEX other = (SMSEX) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SP==null && other.getSP()==null) || 
             (this.SP!=null &&
              this.SP.equals(other.getSP()))) &&
            ((this.checkword==null && other.getCheckword()==null) || 
             (this.checkword!=null &&
              this.checkword.equals(other.getCheckword()))) &&
            ((this.mobileno==null && other.getMobileno()==null) || 
             (this.mobileno!=null &&
              this.mobileno.equals(other.getMobileno()))) &&
            ((this.msg==null && other.getMsg()==null) || 
             (this.msg!=null &&
              this.msg.equals(other.getMsg()))) &&
            ((this.msgId==null && other.getMsgId()==null) || 
             (this.msgId!=null &&
              this.msgId.equals(other.getMsgId()))) &&
            ((this.msgtype==null && other.getMsgtype()==null) || 
             (this.msgtype!=null &&
              this.msgtype.equals(other.getMsgtype()))) &&
            ((this.resno==null && other.getResno()==null) || 
             (this.resno!=null &&
              this.resno.equals(other.getResno()))) &&
            ((this.systemId==null && other.getSystemId()==null) || 
             (this.systemId!=null &&
              this.systemId.equals(other.getSystemId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getSP() != null) {
            _hashCode += getSP().hashCode();
        }
        if (getCheckword() != null) {
            _hashCode += getCheckword().hashCode();
        }
        if (getMobileno() != null) {
            _hashCode += getMobileno().hashCode();
        }
        if (getMsg() != null) {
            _hashCode += getMsg().hashCode();
        }
        if (getMsgId() != null) {
            _hashCode += getMsgId().hashCode();
        }
        if (getMsgtype() != null) {
            _hashCode += getMsgtype().hashCode();
        }
        if (getResno() != null) {
            _hashCode += getResno().hashCode();
        }
        if (getSystemId() != null) {
            _hashCode += getSystemId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SMSEX.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bean.dao.sms.sf", "SMSEX"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "checkword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobileno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mobileno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msgId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgtype");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msgtype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "systemId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
