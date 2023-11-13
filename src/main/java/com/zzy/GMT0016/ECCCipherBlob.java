package com.zzy.GMT0016;

import lombok.*;

import java.io.Serializable;


/**
 * @author zzypersonally@gmail.com
 * @description GM0016规范 6.4.7 ECC密文结构
 * @since 2023/10/8 11:24
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ECCCipherBlob implements Serializable {
    private byte[] xCoordinate; //公钥x坐标
    private byte[] yCoordinate; //公钥y坐标
    private byte[] hash;    //明文的hash值
    private int cipherLen; //密文长度
    private byte[] cipherData; //密文数据
}
