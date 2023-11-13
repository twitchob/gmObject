package com.zzy.GMT0018;

import lombok.*;

import java.io.Serializable;

/**
 * @author zzypersonally@gmail.com
 * @description GM0018规范 5.5 ECC私钥结构 Page5
 * @since 2023/10/8 16:24
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ECCrefPrivateKey_st implements Serializable {
    private static final int ECCref_MAX_BITS = 512;
    private static final int ECCref_MAX_LEN = (ECCref_MAX_BITS + 7) / 8;

    private int bits;    //模长,单位为bit,表示实际有效的bit位数 通常情况下,K为64字节,实际有效的为后32字节,前面32字节为填充字节(0),这种情况下,bits = 256
    private byte[] K= new byte[ECCref_MAX_LEN];   //私钥 0018规范中的K 其他规范中有的叫D
}
