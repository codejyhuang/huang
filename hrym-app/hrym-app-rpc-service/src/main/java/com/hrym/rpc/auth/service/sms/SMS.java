/**
 * SMS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.hrym.rpc.auth.service.sms;

public class SMS  implements java.io.Serializable {
    private java.lang.String checkword;

    private java.lang.String mobileno;

    private java.lang.String msg;

    private java.lang.String msgId;

    private java.lang.String msgtype;

    private java.lang.String resno;

    private java.lang.String waybillNo;

    public SMS() {
    }

    public SMS(
           java.lang.String checkword,
           java.lang.String mobileno,
           java.lang.String msg,
           java.lang.String msgId,
           java.lang.String msgtype,
           java.lang.String resno,
           java.lang.String waybillNo) {
           this.checkword = checkword;
           this.mobileno = mobileno;
           this.msg = msg;
           this.msgId = msgId;
           this.msgtype = msgtype;
           this.resno = resno;
           this.waybillNo = waybillNo;
    }


    /**
     * Gets the checkword value for this SMS.
     * 
     * @return checkword
     */
    public java.lang.String getCheckword() {
        return checkword;
    }


    /**
     * Sets the checkword value for this SMS.
     * 
     * @param checkword
     */
    public void setCheckword(java.lang.String checkword) {
        this.checkword = checkword;
    }


    /**
     * Gets the mobileno value for this SMS.
     * 
     * @return mobileno
     */
    public java.lang.String getMobileno() {
        return mobileno;
    }


    /**
     * Sets the mobileno value for this SMS.
     * 
     * @param mobileno
     */
    public void setMobileno(java.lang.String mobileno) {
        this.mobileno = mobileno;
    }


    /**
     * Gets the msg value for this SMS.
     * 
     * @return msg
     */
    public java.lang.String getMsg() {
        return msg;
    }


    /**
     * Sets the msg value for this SMS.
     * 
     * @param msg
     */
    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }


    /**
     * Gets the msgId value for this SMS.
     * 
     * @return msgId
     */
    public java.lang.String getMsgId() {
        return msgId;
    }


    /**
     * Sets the msgId value for this SMS.
     * 
     * @param msgId
     */
    public void setMsgId(java.lang.String msgId) {
        this.msgId = msgId;
    }


    /**
     * Gets the msgtype value for this SMS.
     * 
     * @return msgtype
     */
    public java.lang.String getMsgtype() {
        return msgtype;
    }


    /**
     * Sets the msgtype value for this SMS.
     * 
     * @param msgtype
     */
    public void setMsgtype(java.lang.String msgtype) {
        this.msgtype = msgtype;
    }


    /**
     * Gets the resno value for this SMS.
     * 
     * @return resno
     */
    public java.lang.String getResno() {
        return resno;
    }


    /**
     * Sets the resno value for this SMS.
     * 
     * @param resno
     */
    public void setResno(java.lang.String resno) {
        this.resno = resno;
    }


    /**
     * Gets the waybillNo value for this SMS.
     * 
     * @return waybillNo
     */
    public java.lang.String getWaybillNo() {
        return waybillNo;
    }


    /**
     * Sets the waybillNo value for this SMS.
     * 
     * @param waybillNo
     */
    public void setWaybillNo(java.lang.String waybillNo) {
        this.waybillNo = waybillNo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SMS)) return false;
        SMS other = (SMS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
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
            ((this.waybillNo==null && other.getWaybillNo()==null) || 
             (this.waybillNo!=null &&
              this.waybillNo.equals(other.getWaybillNo())));
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
        if (getWaybillNo() != null) {
            _hashCode += getWaybillNo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SMS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bean.dao.sms.sf", "SMS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("waybillNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "waybillNo"));
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
