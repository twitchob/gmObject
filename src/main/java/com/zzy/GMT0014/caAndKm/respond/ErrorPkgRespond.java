package com.zzy.GMT0014.caAndKm.respond;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bouncycastle.asn1.*;

/**
 * @author zzypersonally@gmail.com
 * @description 错误包，处理密钥服务请求出错时，KM使用本包响应申请者
 * @since 2023/10/10 12:02
 */
@Getter
@AllArgsConstructor
public class ErrorPkgRespond extends ASN1Object {
    private ASN1Integer errNo;
    private DEROctetString errDesc;

    public ErrorPkgRespond(ASN1Sequence instance) {
        this.errNo = ASN1Integer.getInstance(instance.getObjectAt(0));
        if (instance.size() > 1) {
            this.errDesc = (DEROctetString) DEROctetString.getInstance(instance.getObjectAt(1));
        }
    }
    public static ErrorPkgRespond getInstance(Object obj) {
        if (obj instanceof ErrorPkgRespond) {
            return (ErrorPkgRespond) obj;
        } else if (obj != null) {
            return new ErrorPkgRespond(ASN1Sequence.getInstance(obj));
        } else {
            throw new IllegalArgumentException("obj is null");
        }
    }
    @Override
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.errNo);
        if (this.errDesc != null) {
            v.add(new DERTaggedObject(true, 0, this.errDesc));
        }
        return new DERSequence(v);
    }
}
