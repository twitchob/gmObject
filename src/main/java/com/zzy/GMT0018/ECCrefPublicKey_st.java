package com.zzy.GMT0018;

import lombok.*;

import java.io.Serializable;

/**
 * @author zzypersonally@gmail.com
 * @description GM0018规范 5.5 ECC公钥结构 Page5
 * @since 2023/10/8 16:18
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ECCrefPublicKey_st implements Serializable {
    private static final int ECCref_MAX_BITS = 512;
    private static final int ECCref_MAX_LEN = (ECCref_MAX_BITS + 7) / 8;

    private int bits;    //模长
    private byte[] x = new byte[ECCref_MAX_LEN];   //公钥x坐标
    private byte[] y = new byte[ECCref_MAX_LEN];   //公钥y坐标

}
