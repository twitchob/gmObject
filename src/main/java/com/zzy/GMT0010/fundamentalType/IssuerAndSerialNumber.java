//package com.af.asn1.GMT0010.fundamentalType;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import org.bouncycastle.asn1.*;
//
//@Getter
//@Setter
//@AllArgsConstructor
////6.7 IssuerAndSerialNumber
//public class IssuerAndSerialNumber extends ASN1Object {
//    ASN1Integer version;
//    ASN1Integer serialNumber;
//
//
//    @Override
//    public ASN1Primitive toASN1Primitive() {
//        ASN1EncodableVector v = new ASN1EncodableVector(5);
//        v.add(version);
//        v.add(serialNumber);
//        return new DERSequence(v);
//    }
//}
