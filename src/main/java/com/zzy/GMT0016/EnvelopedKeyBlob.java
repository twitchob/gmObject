package com.zzy.GMT0016;

import lombok.*;

import java.io.Serializable;

/**
 * @author zzypersonally@gmail.com
 * @description 加密密钥对保护结构, 用来在KM->CA->RA之间传递加密密钥对 符合0016规范 6.4.10
 * @since 2023/10/8 10:19
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EnvelopedKeyBlob implements Serializable {

    private int version;                                  //版本号 默认为1
    private int ulSymmAlgID;                              //对称算法标识,限定ECB模式 默认为1
    private int ulBits;                                   //对称密钥位长度  用该密钥加密私钥 默认为128
    private byte[] cbEncryptedPriKey = new byte[64];      //加密密钥对私钥的密文 使用对称算法加密
    private ECCPublicKeyBlob pubKey;                      //加密密钥对公钥
    private ECCCipherBlob cipherBlob;                     //用公钥加密过的对称密钥密文


//    //构造
//    public EnvelopedKeyBlob(byte[] cbEncryptedPriKey, ECCPublicKeyBlob pubKey, ECCCipherBlob cipherBlob) {
//        this.version = 1;
//        this.ulSymmAlgID = 1;
//        this.ulBits = 128;
//        this.cbEncryptedPriKey = cbEncryptedPriKey;
//        this.pubKey = pubKey;
//        this.cipherBlob = cipherBlob;
//    }
//
//    //encode 编码
//    public byte[] encode() {
//        byte[] version = BytesUtils.int2Bytes(this.version);
//        byte[] ulSymmAlgID = BytesUtils.int2Bytes(this.ulSymmAlgID);
//        byte[] ulBits = BytesUtils.int2Bytes(this.ulBits);
//        byte[] cbEncryptedPriKey = this.cbEncryptedPriKey;
//        byte[] pubKey = this.pubKey.encode();
//        byte[] cipherBlob = this.cipherBlob.encode();
//        byte[] result = new byte[4 + 4 + 4 + cbEncryptedPriKey.length + pubKey.length + cipherBlob.length];
//        System.arraycopy(version, 0, result, 0, 4);
//        System.arraycopy(ulSymmAlgID, 0, result, 4, 4);
//        System.arraycopy(ulBits, 0, result, 8, 4);
//        System.arraycopy(cbEncryptedPriKey, 0, result, 12, cbEncryptedPriKey.length);
//        System.arraycopy(pubKey, 0, result, 12 + cbEncryptedPriKey.length, pubKey.length);
//        System.arraycopy(cipherBlob, 0, result, 12 + cbEncryptedPriKey.length + pubKey.length, cipherBlob.length);
//        return result;
//    }
//
//    //decode 解码
//    public static EnvelopedKeyBlob decode(byte[] bytes) {
//        byte[] version = new byte[4];
//        byte[] ulSymmAlgID = new byte[4];
//        byte[] ulBits = new byte[4];
//        byte[] cbEncryptedPriKey;
//        byte[] pubKey;
//        byte[] cipherBlob;
//        System.arraycopy(bytes, 0, version, 0, 4);
//        System.arraycopy(bytes, 4, ulSymmAlgID, 0, 4);
//        System.arraycopy(bytes, 8, ulBits, 0, 4);
//        cbEncryptedPriKey = new byte[bytes.length - 12 - 64 - 132];
//        pubKey = new byte[64];
//        cipherBlob = new byte[132];
//        System.arraycopy(bytes, 12, cbEncryptedPriKey, 0, cbEncryptedPriKey.length);
//        System.arraycopy(bytes, 12 + cbEncryptedPriKey.length, pubKey, 0, pubKey.length);
//        System.arraycopy(bytes, 12 + cbEncryptedPriKey.length + pubKey.length, cipherBlob, 0, cipherBlob.length);
//        return new EnvelopedKeyBlob(BytesUtils.bytes2Int(version), BytesUtils.bytes2Int(ulSymmAlgID), BytesUtils.bytes2Int(ulBits), cbEncryptedPriKey, ECCPublicKeyBlob.decode(pubKey), ECCCipherBlob.decode(cipherBlob));
//    }


}
