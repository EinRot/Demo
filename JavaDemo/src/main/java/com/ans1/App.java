package com.ans1;

import java.io.IOException;
import java.util.Date;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.util.encoders.Base64;

public class App {
	
	/**
	 * 构建asn.1
	 */
	public static void buildStudent(){
		Integer id = 1;
    	String name = "buejee";
    	Integer age = 18;
    	Time createDate = new Time(new Date());
    	try {
    		Student student = new Student(new ASN1Integer(id),
    				new DERUTF8String(name),
    				new ASN1Integer(age),
    				createDate);
    		String data = Base64.toBase64String(student.getEncoded());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析asn.1
	 */
	public static void resolveStudent(){
		byte[] data = Base64.decode("MB0CAQEMBmJ1ZWplZQIBEhcNMjAwNTE1MTUxNDAzWg==");
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		try {
			while((primitive=ais.readObject())!=null){
				System.out.println("sequence->"+primitive);
				if(primitive instanceof ASN1Sequence){
					ASN1Sequence sequence = (ASN1Sequence)primitive;
					ASN1SequenceParser parser = sequence.parser();
					ASN1Encodable encodable = null;
					while((encodable=parser.readObject())!=null){
						primitive = encodable.toASN1Primitive();
						System.out.println("prop->"+primitive);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
    public static void main( String[] args ){
    	buildStudent();
    	resolveStudent();
    }
}