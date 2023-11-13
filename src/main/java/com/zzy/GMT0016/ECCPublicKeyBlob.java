package com.zzy.GM0016;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/**
 * @author zhangzhongyuan@szanfu.cn
 * @description GM0016规范 6.4.5 ECC公钥结构
 * @since 2023/10/8 11:21
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ECCPublicKeyBlob implements Serializable {
    private static final int ECC_MAX_XCOORDINATE_BITS = 512;
    private static final int ECC_MAX_YCOORDINATE_BITS = 512;
    private int bitLen;    //模长
    private byte[] x = new byte[ECC_MAX_XCOORDINATE_BITS / 8];   //公钥x坐标
    private byte[] y = new byte[ECC_MAX_YCOORDINATE_BITS / 8];   //公钥y坐标

//    //encode 编码
//    public byte[] encode() {
//        byte[] bitLen = BytesUtils.int2Bytes(this.bitLen);
//        byte[] x = this.x;
//        byte[] y = this.y;
//        byte[] result = new byte[4 + x.length + y.length];
//        System.arraycopy(bitLen, 0, result, 0, 4);
//        System.arraycopy(x, 0, result, 4, x.length);
//        System.arraycopy(y, 0, result, 4 + x.length, y.length);
//        return result;
//    }
//    //decode 解码
//    public static ECCPublicKeyBlob decode(byte[] bytes) {
//        byte[] bitLen = new byte[4];
//        byte[] x;
//        byte[] y;
//        System.arraycopy(bytes, 0, bitLen, 0, 4);
//        x = new byte[(bytes.length - 4) / 2];
//        y = new byte[(bytes.length - 4) / 2];
//        System.arraycopy(bytes, 4, x, 0, x.length);
//        System.arraycopy(bytes, 4 + x.length, y, 0, y.length);
//        return new ECCPublicKeyBlob(BytesUtils.bytes2Int(bitLen), x, y);
//    }

}
