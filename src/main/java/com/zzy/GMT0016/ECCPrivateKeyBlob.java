package com.zzy.GM0016;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description GM0016规范 6.4.6 ECC私钥结构
 * @since 2023/10/8 11:22
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ECCPrivateKeyBlob implements Serializable {
    private static final int ECC_MAX_MODULUS_BITS_LEN = 512;
    private int bitLen;    //模长
    private byte[] privateKey = new byte[ECC_MAX_MODULUS_BITS_LEN / 8];  //私钥

//    //encode 编码
//    public byte[] encode() {
//        byte[] bitLen = BytesUtils.int2Bytes(this.bitLen);
//        byte[] privateKey = this.privateKey;
//        byte[] result = new byte[4 + privateKey.length];
//        System.arraycopy(bitLen, 0, result, 0, 4);
//        System.arraycopy(privateKey, 0, result, 4, privateKey.length);
//        return result;
//    }
//    //decode 解码
//    public static ECCPrivateKeyBlob decode(byte[] bytes) {
//        byte[] bitLen = new byte[4];
//        byte[] privateKey;
//        System.arraycopy(bytes, 0, bitLen, 0, 4);
//        privateKey = new byte[bytes.length - 4];
//        System.arraycopy(bytes, 4, privateKey, 0, privateKey.length);
//        return new ECCPrivateKeyBlob(BytesUtils.bytes2Int(bitLen), privateKey);
//    }
}
