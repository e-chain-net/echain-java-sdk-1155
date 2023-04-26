package com.echain.test.sdk;

import com.echain.account.Account;
import com.echain.sdk.EChainSDK;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.fisco.bcos.sdk.jni.common.JniException;
import org.fisco.bcos.sdk.jni.utilities.tx.TxPair;

import java.math.BigInteger;

public class TestSign {
    public static String contractAddress = "0xdcdecd228f59a234287fece68ad8fb94f016b124";
    public static Account owner = new Account("0xdb222aaaefb28a8a2b1533c6b098b819a80665b6","500a9faf63d80563207702295b9bf9f5dc98956fd253d81b8ba062f69b5cf6b1");
    public static Account user1 = new Account("0x95a1a99be965777d8b0e42fe5ec1c161f6c3a5da","20af5ca9552563576673abda1af0540ff6c72ea631b1de8b11296c94167a6b06");
    public static Account user2 = new Account("0xf53baf7526a2c8aec2f185ed48e94316e29e9e95","42e548a753fe86d0937372b24ae472559966929fb3f8d0672376849d23f6a43d");
    public static EChainSDK sdk = new EChainSDK();
    public static void main(String args[]) throws JniException {
        BigInteger tokenId = BigInteger.valueOf(1001);
        BigInteger amount = BigInteger.valueOf(2);

        long blockNumber = 41;

        TxPair mintRes = sdk.signMint(user1.getAddress(),tokenId,amount,contractAddress,owner.getPrivateKey(),blockNumber);
        System.out.println("Mint txHash:" + mintRes.getTxHash());
        System.out.println("Mint signed:" + mintRes.getSignedTx());

        TxPair transferRes = sdk.signTransferFrom(user1.getAddress(),user2.getAddress(),tokenId,amount,contractAddress,user1.getPrivateKey(),blockNumber);
        System.out.println("Transfer txHash:" + transferRes.getTxHash());
        System.out.println("Transfer signed:" + transferRes.getSignedTx());

        TxPair burnRes = sdk.signBurn(user2.getAddress(),tokenId,amount,contractAddress,user2.getPrivateKey(),blockNumber);
        System.out.println("Burn txHash:" + burnRes.getTxHash());
        System.out.println("Burn signed:" + burnRes.getSignedTx());
    }
}
